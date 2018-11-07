package viewer.android.gaiaproject.eu.gaiaviewer;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Locale;

public class ServiceUtils {


    public static void sendIntent(final ContextWrapper thisService, final String action, final String extraKey, final Serializable extraValue) {
        if (thisService != null) {
            final Intent i = new Intent();
            i.setAction(action);
            i.putExtra(extraKey, extraValue);
            thisService.sendBroadcast(i);
        }
    }

    public static void sendIntent(final Service thisService, final String action, final String extraKey, final Location extraValue) {
        if (thisService != null) {
            final Intent i = new Intent();
            i.setAction(action);
            i.putExtra(extraKey, extraValue);
            thisService.sendBroadcast(i);
        }
    }

    public static void sendIntent(final Service thisService, final String action, final String extraKey, final Location extraValue, final String extraKey1, final Serializable extraValue1) {
        if (thisService != null) {
            final Intent i = new Intent();
            i.setAction(action);
            i.putExtra(extraKey, extraValue);
            i.putExtra(extraKey1, extraValue1);
            thisService.sendBroadcast(i);
        }
    }

    public static void sendIntent(Activity activity, final String action, final String extraKey, final String extraValue) {
        if (activity != null) {
            final Intent i = new Intent();
            i.setAction(action);
            i.putExtra(extraKey, extraValue);
            activity.sendBroadcast(i);
        }
    }

    public static void sendIntent(Context context, final String action, final String extraKey, final String extraValue,boolean UPAT) {
        if (context != null) {
            final Intent i = new Intent();
            i.setAction(action);
            i.putExtra(extraKey, extraValue);
            context.sendBroadcast(i);
        }
    }

    public static void sendIntent(Activity activity, final String action, final String extraKey, final Serializable extraValue) {
        if (activity != null) {
            final Intent i = new Intent();
            i.setAction(action);
            i.putExtra(extraKey, extraValue);
            activity.sendBroadcast(i);
        }
    }

    public static void sendIntent(Service activity, final String action, final String extraKey, final Serializable extraValue) {
        if (activity != null) {
            final Intent i = new Intent();
            i.setAction(action);
            i.putExtra(extraKey, extraValue);
            activity.sendBroadcast(i);
        }
    }

    public static void sendIntent(Activity activity, final String action) {
        if (activity != null) {
            final Intent i = new Intent();
            i.setAction(action);
            activity.sendBroadcast(i);
        }
    }



    public static void sendIntent(final Service thisService, final String action) {
        if (thisService != null) {
            final Intent i = new Intent();
            i.setAction(action);
            thisService.sendBroadcast(i);
        }
    }

    public static boolean isNetworkAvailable(final Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    public static void showLongToast(final Context context, final String s, Object... args) {
        final Toast toast = Toast.makeText(context, String.format(Locale.getDefault(), s, args), Toast.LENGTH_LONG);
        toast.show();
    }

    public static void showLongToast(final String s, Object... args) {
        Toast.makeText(
                App.getInstance().getApplicationContext(),
                String.format(Locale.getDefault(), s, args),
                Toast.LENGTH_LONG
        ).show();
    }

    public static void showLongToast(final Context context, final int id, Object... args) {
        final Toast toast = Toast.makeText(context, context.getResources().getString(id, args), Toast.LENGTH_LONG);
        toast.show();
    }

    public static void showShortToast(final Context context, final String s, Object... args) {
        final Toast toast = Toast.makeText(context, String.format(Locale.getDefault(), s, args), Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void showShortToast(final Context context, final int id, Object... args) {
        final Toast toast = Toast.makeText(context, context.getResources().getString(id, args), Toast.LENGTH_SHORT);
        toast.show();
    }

    public static boolean isMyServiceRunning(Context ctx, Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

}
