package viewer.android.gaiaproject.eu.gaiaviewer;

import android.content.ContextWrapper;

import com.pixplicity.easyprefs.library.Prefs;

import androidx.multidex.MultiDexApplication;
import static viewer.android.gaiaproject.eu.gaiaviewer.Constants.SHRD_PREF_SERVICES_NAME;

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

        // initialize the Prefs class
        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(SHRD_PREF_SERVICES_NAME)
                .setUseDefaultSharedPreference(true)
                .build();

    }



}
