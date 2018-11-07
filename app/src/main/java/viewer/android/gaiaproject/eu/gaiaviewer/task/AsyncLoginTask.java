package viewer.android.gaiaproject.eu.gaiaviewer.task;

import android.content.ContextWrapper;

import com.pixplicity.easyprefs.library.Prefs;

import viewer.android.gaiaproject.eu.gaiaviewer.Communications;

import static viewer.android.gaiaproject.eu.gaiaviewer.Constants.USER;
import static viewer.android.gaiaproject.eu.gaiaviewer.Constants.CODE;

public class AsyncLoginTask extends BaseAsyncRefreshTask<Object, String, String> {

    public AsyncLoginTask(Communications communications, ContextWrapper contextWrapper) {
        super(communications, contextWrapper);
    }

    public AsyncLoginTask(Communications communications, ContextWrapper contextWrapper, Runnable callback) {
        super(communications, contextWrapper, callback);
    }

    @Override
    protected String doInBackground(Object... input) {
        if (Prefs.contains(USER) && Prefs.contains(CODE)) {
            new Communications().login(Prefs.getString(USER, null), Prefs.getString(CODE, null));
        }
        return null;
    }

}
