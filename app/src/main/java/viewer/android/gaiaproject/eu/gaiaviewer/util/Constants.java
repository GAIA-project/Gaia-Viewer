package viewer.android.gaiaproject.eu.gaiaviewer.util;

public class Constants {

    public static final int SERVICE_MANAGE_NOTIFICATION_ID = 643945;

    public static final String RESYNC_DATA_ACTION = "viewer.android.gaiaproject.eu.gaiaviewer.SYNC";

    static final String OATH2_TOKEN_URL2 = "https://sso.sparkworks.net/aa/oauth/token?username={username}&password={password}&client_id=GamECARDataLogger&client_secret=a001c77f-db5e-4159-9364-9a2136063c12&grant_type=password"; // since Sep 08
    static final String OATH2_CHECK_TOKEN_URL = "https://sso.sparkworks.net/aa/oauth/check_token"; // since Sep 08
    static final String OAUTH2_BASIC = "R2FtRUNBUkRhdGFMb2dnZXI6YTAwMWM3N2YtZGI1ZS00MTU5LTkzNjQtOWEyMTM2MDYzYzEy"; // since Sep 08
    public static final String ACCOUNTS_PREF_NAME = "gaiaviewer_accounts";
    public static final String SHRD_PREF_SERVICES_NAME = "gaiaViewerPrefsSettings";

    public static final String USER = "username";
    public static final String CODE = "code";
}
