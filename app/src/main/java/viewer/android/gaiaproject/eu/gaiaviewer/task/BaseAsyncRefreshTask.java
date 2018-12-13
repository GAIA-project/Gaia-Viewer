package viewer.android.gaiaproject.eu.gaiaviewer.task;

import android.content.ContextWrapper;
import android.os.AsyncTask;
import viewer.android.gaiaproject.eu.gaiaviewer.util.Communications;

public class BaseAsyncRefreshTask<T,A,F> extends AsyncTask<T, A, F> {
    protected static final String TAG = "AsyncRefreshTask";

    protected final Communications communications;
    protected final ContextWrapper contextWrapper;

    protected Runnable callback = null;

    public BaseAsyncRefreshTask(final Communications communications, final ContextWrapper contextWrapper) {
        this.communications = communications;
        this.contextWrapper = contextWrapper;
    }

    public BaseAsyncRefreshTask(final Communications communications, final ContextWrapper contextWrapper, Runnable callback) {
        this(communications, contextWrapper);
        this.callback = callback;
    }

    public void setCallback(Runnable callback) {
        this.callback = callback;
    }



    @Override
    protected void onPostExecute(F f) {
        if(f != null && callback != null){
        }
        else {
            if (callback != null)
                callback.run();
        }
    }

    @Override
    protected F doInBackground(T... ts) {
        return null;
    }
}
