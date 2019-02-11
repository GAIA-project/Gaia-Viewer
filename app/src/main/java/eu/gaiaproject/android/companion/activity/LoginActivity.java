package eu.gaiaproject.android.companion.activity;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.pixplicity.easyprefs.library.Prefs;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import eu.gaiaproject.android.companion.R;
import eu.gaiaproject.android.companion.aa.SwAccessTokenResponse;
import eu.gaiaproject.android.companion.task.AsyncLoginTask;
import eu.gaiaproject.android.companion.util.Communications;
import eu.gaiaproject.android.companion.util.ServiceUtils;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static eu.gaiaproject.android.companion.util.Constants.ACCOUNTS_PREF_NAME;
import static eu.gaiaproject.android.companion.util.Constants.CODE;
import static eu.gaiaproject.android.companion.util.Constants.RESYNC_DATA_ACTION;
import static eu.gaiaproject.android.companion.util.Constants.USER;

@EActivity(R.layout.activity_login)
public class LoginActivity extends Activity {
    private static final String TAG = "LoginActivity";

    private final String SPARKS_REGISTER_PAGE = "https://sso.sparkworks.net/aa/gc/registration";
    private static final String[] PERMISSIONS = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION};
    private static final int PERMISSION_REQUEST = 1337;

    @ViewById
    LinearLayout progressLayout;
    @ViewById
    LinearLayout loginFormLayout;
    @ViewById
    LinearLayout registrationTermsLayout;
    @ViewById
    EditText loginUsername;
    @ViewById
    EditText loginPassword;
    //private android.os.Handler handler = new android.os.Handler();

    private View.OnClickListener loginCreateAccountListener = v -> {
        //            final Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity_.class);
        //            startActivity(registerIntent);
        //            finish();
        Intent registerIntent = new Intent(Intent.ACTION_VIEW);
        registerIntent.setData(Uri.parse(SPARKS_REGISTER_PAGE));
        startActivity(registerIntent);
        finish();
    };

    @AfterViews
    void afterViews() {
        Log.i(TAG, "afterViews");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(PERMISSIONS, PERMISSION_REQUEST);
        } else {
            checkLogin();
        }
    }

    @Click
    void loginGaiaChallengeLink() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://gaia-challenge.com/"));
        startActivity(browserIntent);
    }
    @Click
    void loginBMALink() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://bms.gaia-project.eu/"));
        startActivity(browserIntent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean granted = true;
        Log.i(TAG, "onRequestPermissionsResult: " + requestCode);
        switch (requestCode) {
            case PERMISSION_REQUEST:
                for (int grantResult : grantResults) {
                    if (grantResult != PERMISSION_GRANTED) {
                        granted = false;
                    }
                }
                break;
        }

        if (granted) {
            checkLogin();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, "onActivityResult: " + requestCode);
        switch (requestCode) {
            case 201:
                checkLogin();
        }
    }

    private void checkLogin() {
        Log.i(TAG, "checkLogin");
        if (ServiceUtils.isNetworkAvailable(getApplicationContext())) {
            if (Prefs.contains(USER) && Prefs.contains(CODE) && Prefs.contains(ACCOUNTS_PREF_NAME)) {
                AsyncLoginTask loginTask = new AsyncLoginTask(new Communications(), this, this::loadMain);
                loginTask.execute();
                //handler.post(checkCodeUpdate);
            } else {
                hideProgress();
            }
        } else {
            String tokenStr = Prefs.getString(ACCOUNTS_PREF_NAME, null);
            if (tokenStr != null) {
                loadMain();
            } else {
                Toast.makeText(getBaseContext(), "Please connect to the internet to sign in", Toast.LENGTH_LONG).show();
                hideProgress();
            }
        }
    }

    private boolean isValidUserCredentials() {
        Log.i(TAG, "isValidUserCredentials");
        boolean isUsernameValid;
        boolean isPasswordValid;

        final String username = loginUsername.getText() == null ? null : loginUsername.getText().toString();
        isUsernameValid = username != null;
        if (isUsernameValid) {
            loginUsername.setError(null);
        } else {
            loginUsername.setError(getString(R.string.err_validation_username_required));
            loginUsername.requestFocus();
        }

        final String password = loginPassword.getText() == null ? null : loginPassword.getText().toString();
        isPasswordValid = password != null;
        if (isPasswordValid) {
            loginPassword.setError(null);
        } else {
            loginPassword.setError(getString(R.string.err_validation_password_required));
            if (isUsernameValid) {
                loginPassword.requestFocus();
            }
        }

        return isUsernameValid && isPasswordValid;
    }

    @Click
    void btnLogin() {
        if (!ServiceUtils.isNetworkAvailable(getApplicationContext())) {
            Toast.makeText(getBaseContext(), "Please connect to the internet to sign in", Toast.LENGTH_LONG).show();
        } else if (isValidUserCredentials()) {
            doLogin(loginUsername.getText().toString(), loginPassword.getText().toString());
        }
    }

    void doLogin(String username, String password) {
        new Thread(() -> {
            showProgress();
            Prefs.putString(USER, username);
            Prefs.putString(CODE, password);
            if (ServiceUtils.isNetworkAvailable(getApplicationContext())) {
                final SwAccessTokenResponse token = new Communications().login(username, password);
                if (token != null) {
                    //add intent here
                    Log.d(TAG, "Asking to resync data");
                    ServiceUtils.sendIntent(this, RESYNC_DATA_ACTION);
                    loadMain();
                } else {
                    Log.w(TAG, "failed to login");
                    hideProgress();
                }
            }
        }).start();
    }

    void loadMain() {
        runOnUiThread(() -> {
            final Intent mainIntent = new Intent(LoginActivity.this, MainActivity_.class);
            startActivity(mainIntent);
            finish();
        });
    }

    void showProgress() {
        runOnUiThread(() -> {
            progressLayout.setVisibility(View.VISIBLE);
            loginFormLayout.setVisibility(View.GONE);
            registrationTermsLayout.setVisibility(View.GONE);
        });
    }

    void hideProgress() {
        runOnUiThread(() -> {
            progressLayout.setVisibility(View.GONE);
            loginFormLayout.setVisibility(View.VISIBLE);
            registrationTermsLayout.setVisibility(View.VISIBLE);
        });
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }
}
