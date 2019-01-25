package eu.gaiaproject.android.companion;

import android.content.ContextWrapper;

import com.pixplicity.easyprefs.library.Prefs;
import com.splunk.mint.Mint;

import androidx.multidex.MultiDexApplication;
import static eu.gaiaproject.android.companion.util.Constants.SHRD_PREF_SERVICES_NAME;

public class App extends MultiDexApplication {

    private static final String TAG = "App";

    private static App mInstance;

    public static synchronized App getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        Mint.initAndStartSession(getApplicationContext(), "2d901b25");

        // initialize the Prefs class
        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(SHRD_PREF_SERVICES_NAME)
                .setUseDefaultSharedPreference(true)
                .build();

    }



}
