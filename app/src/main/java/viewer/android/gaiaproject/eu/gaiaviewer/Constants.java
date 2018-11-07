package viewer.android.gaiaproject.eu.gaiaviewer;

public class Constants {

    public static final int SERVICE_MANAGE_NOTIFICATION_ID = 643945;

    public static final String RESYNC_DATA_ACTION = "app.gamecar.sparkworks.data.SYNC";
    public static final String LOG_SYNC = "app.gamecar.sparkworks.data.sync.LOG";
    public static final String SYNC_COMPLETED = "app.gamecar.sparkworks.data.sync.COMPLETED";

    public static final String CONNECT_STATUS = "app.gamecar.sparkworks.net.gamecar.connect.STATUS";

    public static final String LOCATION_EXTRA = "location";
    public static final String FILENAME_EXTRA = "filename";
    public static final String ACCELEROMETER_EXTRA = "accelerometer";
    public static final String HEARTRATE_EXTRA = "heartrate";
    public static final String OBD_EXTRA = "obd";
    public static final String OBD_SCAN_PERIOD_EXTRA = "obd_interval";
//    public static final String OBD_SELECTED_ADDRESS = "obd_selected_address";
//    public static final String MIBAND_SELECTED_ADDRESS = "miband_selected_address";
//    public static final String POLAR_SELECTED_ADDRESS = "polar_selected_address";
    public static final String GEAR_EXTRA = "gear";
    public static final String MIBAND_EXTRA = "miband";
    public static final String POLAR_EXTRA = "polar";
//    public static final String GEARS_EXTRA = "cGears";
//    public static final String USER_ID_EXTRA = "userIdentifier";
//    public static final String LOGGER_MESSAGE_EXTRA = "loggerMessage";
//    public static final String PIDS_EXTRA = "availablePids";
//    public static final String PIDS_EXTRA_1 = "availablePids1";
//    public static final String PIDS_EXTRA_2 = "availablePids2";
//    public static final String PIDS_EXTRA_3 = "availablePids3";
//    public static final String COUNT_EXTRA = "count";
//    public static final String TIME_EXTRA = "time";
//    public static final String SERVICE_EXTRA = "serviceName";
//    public static final String WS_EXTRA = "ws_measurement";
//    public static final String ACTIVITY_EXTRA = "activity";
    public static final String TEXT_EXTRA = "text";
//    public static final String LOGGED_DATALINE_EXTRA = "loggedDataLine";
//    public static final String RISK_LEVEL_EXTRA = "riskLevel";

    public static final String TRACE_FILE_NAME_FORMAT = "yyyy-MM-dd'T'HH-mm-ss";
    public static final String TRACE_FILE_XML_DATE = "dd-MM-yyyy";
    public static final String TRACE_FILE_XML_TIME = "HH:mm";
    public static final String TRACE_FILE_EXTENSION_CSV = ".csv";
    public static final String TRACE_FILE_EXTENSION_XML = ".xml";

    static final String OATH2_TOKEN_URL2 = "https://sso.sparkworks.net/aa/oauth/token?username={username}&password={password}&client_id=GamECARDataLogger&client_secret=a001c77f-db5e-4159-9364-9a2136063c12&grant_type=password"; // since Sep 08
    static final String OATH2_CHECK_TOKEN_URL = "https://sso.sparkworks.net/aa/oauth/check_token"; // since Sep 08
    static final String OAUTH2_BASIC = "R2FtRUNBUkRhdGFMb2dnZXI6YTAwMWM3N2YtZGI1ZS00MTU5LTkzNjQtOWEyMTM2MDYzYzEy"; // since Sep 08
    public static final String ACCOUNTS_PREF_NAME = "gamecar_accounts";
    public static final String SHRD_PREF_SERVICES_NAME = "gaiaViewerPrefsSettings";

    public static final String STORED_GCDB_DRIVER = "gcdb_driver";
    public static final String STORED_GCDB_MY_CREW = "gcdb_crew";
    public static final String STORED_GCDB_MY_CREWMEMBERSHIP = "gcdb_my_crewmembership";
    public static final String STORED_GCDB_CREWMEMBERSHIPS = "gcdb_crewmemberships";
    public static final String STORED_GCDB_ALL_CREWS = "gcdb_all_crews";
    public static final String STORED_GCDB_CREW_DRIVERS = "gcdb_crew_vehicles";
//    public static final String STORED_GCDB_VEHICLE = "gcdb_vehicle";
    public static final String STORED_GCDB_VEHICLES = "gcdb_vehicles";
    public static final String STORED_GCDB_SESSIONS = "gcdb_sessions";
    public static final String STORED_GCDB_VEHICLES_VEHICLEMODELS = "gcdb_vehicles_vehicleModels";
//    public static final String STORED_GCDB_VEHICLEMODEL = "gcdb_vehicleModel";
    public static final String STORED_GCDB_VEHICLEMODELS = "gcdb_vehicleModels";
    public static final String STORED_GCDB_BADGES = "gcdb_badges";
    public static final String STORED_GCDB_DRIVER_BADGES = "gcdb_driver_badges";
    public static final String STORED_GCDB_DRIVER_BADGES_BADGES = "gcdb_driver_badges_badges";
    public static final String STORED_GCDB_KNOWLEDGE_CARDS = "gcdb_knowledge_cards";
    public static final String STORED_GCDB_DRIVER_KNOWLEDGE_CARDS = "gcdb_driver_knowledge_cards";
//    public static final String STORED_GCDB_MEDALS = "gcdb_medals";
    public static final String STORED_GCDB_MISSIONS = "gcdb_missions";
    public static final String STORED_GCDB_DRIVER_MISSIONS = "gcdb_driver_missions";
    public static final String STORED_GCDB_DRIVER_MISSIONS_MISSIONS = "gcdb_driver_missions_missions";
    public static final String STORED_GCDB_DRIVER_KNOWLEDGE_CARDS_KNOWLEDGE_CARDS = "gcdb_driver_knowledge_cards_knowledge_cards";
    public static final String STORED_GCDB_RANKING = "gcdb_ranking";
    public static final String STORED_GCDB_FRIENDSHIP = "gcdb_friendship";


    public static final String MIXPANEL_TOKEN = "af3f0caa6d0cb6bd4a301dd2549f1490";

    public static final long OBD_SCAN_PERIOD = 50; // Restarts scanning after 10 ms.
    public static final long MIBAND_SCAN_PERIOD = 5000; // Restarts scanning after 1000 ms.

    public static final int WS_PORT =  7777;
//    public static final int FRAGMENTS_COUNT = 10; //Represent the current count of fragments


    /**
     * UPAT Constants
     */
    //Vehicle Calibration Actions
    public static final String VEHICLE_CALIBRATED = "app.gamecar.sparkworks.net.gamecardatalogger.gamecar_hud.vehiclecalibration.VEHICLE_CALIBRATED";
    //Vehicle Calibration Extras
    public static final int VEHICLE_CALIBRATION_RC = 11;
    public static final String VEHICLE_TIRE_DIAMETER = "Vehicle_tire_diameter";
    public static final String VEHICLE_NUM_OF_GEARS = "Vehicle_num_of_gears";
    public static final String CLUSTER_RESULTS = "Cluster_Results";
    public static final String MIN_ACCELERATION = "Min_Acceleration";
    public static final String MAX_ACCELERATION = "Max_Acceleration";
    public static final String MIN_DECELERATION = "Min_Deceleration";
    public static final String MAX_DECELERATION = "Max_Deceleration";

    //Vehicle Entry Actions
    public static final int VEHICLE_IMAGE_RC = 12;


    //Vehicle Extras
    public static final String IS_VEHICLE_SELECTED = "isVehicleSelected";
    public static final String VEHICLE_SELECTED_NAME = "selectedVehicle";


    //Device types
    public static final int  DEVICE_OBD = 0;
    public static final int  DEVICE_MI_BAND = 1;
    public static final int  DEVICE_POLAR = 2;

    //Vehicle Fuel Types
//    public static final int UNDEFINED = 0;
    public static final int DIESEL = 1;
    public static final int GASOLINE = 2;
    public static final int CNG = 3;
    public static final int METHANOL = 4;
    public static final int ETHANOL = 5;
    public static final int PROPANE = 6;

    //Vehicle Fuel Types Grams per litre
    public static final float GRAMS_PER_LITRE_DIESEL = 850.8f;
    public static final float GRAMS_PER_LITRE_GASOLINE = 748.9f;
    public static final float GRAMS_PER_LITRE_CNG = 128.2f;
    public static final float GRAMS_PER_LITRE_METHANOL = 786.6f;
    public static final float GRAMS_PER_LITRE_ETHANOL = 789f;
    public static final float GRAMS_PER_LITRE_PROPANE = 493f;

    //Vehicle Fuel Types Air Ratio
    public static final float AIR_RATIO_DIESEL = 14.6f;
    public static final float AIR_RATIO_GASOLINE = 14.7f;
    public static final float AIR_RATIO_CNG = 17.2f;
    public static final float AIR_RATIO_METHANOL = 6.4f;
    public static final float AIR_RATIO_ETHANOL = 9f;
    public static final float AIR_RATIO_PROPANE = 15.5f;

    //OBD Fuel consumption commands
    public static final int INITIAL_COMMAND_LIVES = 5;

    //Gamification Elements Max Values
    public static final int MAX_LEVEL = 50;
    public static final int MAX_CARDS = 50;
    public static final int MAX_MEDALS = 50;
    public static final int MAX_MISSIONS = 50;
    public static final int MAX_LEVEL_XP = 10000;


    //Gamification Preferences Identifiers
    public static final String NUMBER_OF_TRIPS = "numberOfTrips";
    public static final String AVERAGE_SPEED = "averageSpeed";
    public static final String TOTAL_DISTANCE = "totalDistance";
    public static final String TOTAL_TIME = "totalTime";
    public static final String CURRENT_KNOWLEDGE_CARDS_COUNTER = "currentKnowledgeCardsCounter";
    public static final String MISSIONS_AWARDED = "missionsAwarded";


    //Lootbox reward types
    public static final int LP = 0;
    public static final int XP = 1;
    public static final int KNOWLEDGE_CARD = 2;
    public static final int OUTFIT = 3;


    //Trip analysis performance

    // Knowledge Card categories
    public static final String CARD_CATEGORY_DRIVING = "Driving";
    public static final String CARD_CATEGORY_ENGINE = "Engine";
    public static final String CARD_CATEGORY_ACCESSORIES = "Accessories";
    public static final String CARD_CATEGORY_MAINTENANCE = "Maintenance";


    //Notifications Preferences
    public static final String MISSIONS_NOTIFICATIONS = "missionsNotifications";
    public static final String TROPHIES_NOTIFICATIONS = "trophiesNotifications";
    public static final String BADGES_NOTIFICATIONS = "badgesNotifications";
    public static final String DRIVING_CARDS_NOTIFICATIONS = "drivingCardsNotifications";
    public static final String ENGINE_CARDS_NOTIFICATIONS = "engineCardsNotifications";
    public static final String ACCESSORIES_CARDS_NOTIFICATIONS = "accessoriesCardsNotifications";
    public static final String MAINTENANCE_CARDS_NOTIFICATIONS = "maintenanceCardsNotifications";


    //Gender
    public static final int MALE = 1;
    public static final int FEMALE = 2;

    //Vehicle Acceleration and Deceleration default values
    public static final float MINIMUM_ACCELERATION = 0.3f;
    public static final float MAXIMUM_ACCELERATION = 3.0f;
    public static final float MINIMUM_DECELERATION = -0.3f;
    public static final float MAXIMUM_DECELERATION = -3.0f;

    public final static Integer FIRST_YEAR_OBD = 1996;



    //HUD EVENTS
    public static final int HUD_EVENT_BRAKE = 0;
    public static final int HUD_EVENT_SHIFT_UP = 1;


    //HUD Handlers Enums
    public static final int HUD_ACCELERATION = 0;
    public static final int HUD_SPEED = 1;
    public static final int HUD_RPM = 2;
    public static final int HUD_THROTTLE = 3;
    public static final int HUD_GEAR = 4;
    public static final int HUD_SHIFT = 5;
    public static final int HUD_CACCELERATION = 6;
    public static final int HUD_ECOSCORE = 7;
    public static final int HUD_HINTS = 8;
    public static final int HUD_CONSUMPTION_RATIO = 9;
    public static final int HUD_AGGRESSIVENESS = 10;
    public static final int HUD_HEARTRATE = 11;
    public static final int HUD_TRIP_ECOSCORE_BRAKING = 12;
    public static final int HUD_TRIP_ECOSCORE_SHIFT_UP = 13;
    public static final int HUD_TRIP_ECOSCORE_CRUISING = 14;
    public static final int HUD_TRIP_ECOSCORE = 15;
    public static final int HUD_TRIP_AGGRESSIVENESS = 16;
    public static final int HUD_LOCATION = 17;
    public static final int HUD_XP = 18;
    public static final int HUD_LP = 19;
    public static final int HUD_OPTIMAL_SHIFT_UPS = 20;
    public static final int HUD_SMOOTH_BRAKES = 21;
    public static final int HUD_RISK_LEVEL = 22;
//    public static final int HUD_AIR_INTAKE_TEMPERATURE = 23;
//    public static final int HUD_AIR_MANIFOLD_PRESSURE = 24;
//    public static final int HUD_MASS_AIR_FLOW = 25;


    public static final long HUD_MISSIONPANEL_DELAY = 8000;
    //Online Analysis rpm rules
    public static final int OA_SHIFT_UP_RPM_HIGH_BOUND = 2500;
    public static final int OA_SHIFT_UP_RPM_LOW_BOUND = 2000;
    public static final int OA_SHIFT_DOWN_RPM_HIGH_BOUND = 1300;
    public static final int OA_SHIFT_DOWN_RPM_LOW_BOUND = 100;

    //Online Analysis cruising metric
    public static final int CRUISING_SPEED_LOWER_LIMIT = 30;
    public static final int CRUISING_ANGLE_THRESHOLD_1 = 40;
    public static final int CRUISING_ANGLE_THRESHOLD_2 = 25;
    public static final int CRUISING_ANGLE_THRESHOLD_3 = 10;

    //Daily Missions types
    public static final int DM_SHIFT_UPS = 0;
    public static final int DM_BRAKES = 1;

    //Daily Missions Prefs strings
    public static final String DM_TYPE = "DailyMissionType";
    public static final String DM_TARGET = "DailyMissionTarget";
    public static final String DM_DATE = "DailyMissionDate";
    public static final String DM_PROGRESS = "DailyMissionProgress";
    public static final String DM_COMPLETED = "DailyMissionCompleted";
    public static final String DM_SHOWN = "DailyMissionShown";

    //Trip rewards
    public static final int TRIP_REWARD_ABORT = -1;
    public static final int TRIP_REWARD_NONE = 0;
    public static final int TRIP_REWARD_DM = 1;
    public static final int TRIP_REWARD_LOOTBOX = 2;
    public static final int TRIP_REWARD_MISSION = 3;
    public static final int TRIP_REWARD_FILENAME = 4;

    //Trip rewards action
    public static final String TRIP_REWARD_ACTION = "TripRewardAction";

    //Trip rewards extras
    public static final String TRIP_REWARD_EXTRA = "TripRewardExtra";
    public static final String TRIP_REWARD_EXTRA_ECOSCORE = "TripRewardExtraECSOCORE";

    //Trip Review extras
    public static final String TRIP_DATA_ELEMENT_CSV_FILE_EXTRA = "TripDataElementCsvFileExtra";
    public static final String TRIP_DATA_ELEMENT_XML_FILE_EXTRA = "TripDataElementXmlFileExtra";
    public static final String TRIP_DATA_ELEMENT_TRIP_DISTANCE_EXTRA = "TripDataElementTripDistanceExtra";

    //Trip Review constants
    public static final int ROUTE_SEGMENT_EVENT_NUMBER = 10;

    //Trip Review event types
    public static final int R_BRAKE_EVENT = 0;
    public static final int R_SHIFT_UP_EVENT = 1;
    public static final int R_FULL_STOP_EVENT = 2;
    public static final int R_HIGH_RPM_EVENT = 3;
    public static final int R_CRUISING = 4;

    //Mission Names
    public static final String ICEMAN_NAME = "Iceman";
    public static final String LEGAL_DUDE_NAME = "Legal dude";
    public static final String THE_SAVER = "The saver";
    public static final String SMOOTH_DRIVER = "Smooth driver";
    public static final String THE_CRUISER = "The cruiser";
    public static final String THE_ECO_WARRIOR = "The Eco-Warrior";
    public static final String THE_ECO_DRIVER = "The Eco-Driver";
    public static final String GEAR_SHIFT_MASTER = "Gear Shift master";
    public static final String LIGHT_FOOT = "Light Foot";
    public static final String SMOOTH_BRAKER = "Smooth Braker";

    //Mission Types
    public static final int M_AVG_FUEL_CONSUMPTION = 0;
    public static final int M_MAX_ACCELERATION = 1;
    public static final int M_MAX_SPEED = 2;
    public static final int M_MAX_REVS = 3;
    //Check at the end of trip.
    public static final int M_ECOSCORE = 4;
    public static final int M_ECOSCORE_BRAKING = 5;
    public static final int M_ECOSCORE_CRUISING = 6;
    public static final int M_ECOSCORE_SHIFT_UP = 7;
    public static final int M_AGGRESSIVENESS_SCORE = 8;

    public static final int HUD_REQUEST_CODE = 1928;

    public static final int BT_ENABLE_REQUEST_CODE = 1283;
    public static final int GPS_ENABLE_REQUEST_CODE = 3923;


    //HUD Widget anchor types
    public static final int HW_CENTER_CENTER = 0;
    public static final int HW_RIGHT_CENTER = 1;
    public static final int HW_LEFT_CENTER = 2;
    public static final int HW_CENTER_TOP = 3;
    public static final int HW_RIGHT_TOP = 4;
    public static final int HW_LEFT_TOP = 5;
    public static final int HW_CENTER_BOTTOM = 6;
    public static final int HW_RIGHT_BOTTOM = 7;
    public static final int HW_LEFT_BOTTOM = 8;


    //HUD Widget part IconText LayoutType
    public static final int HW_TOP_BOTTOM = 0;
    public static final int HW_BOTTOM_TOP = 1;
    public static final int HW_LEFT_RIGHT = 2;
    public static final int HW_RIGHT_LEFT = 3;

    //HUD Animations
    public static final int HW_ANIM_DUR = 300;

    //HUD Hints
    public static final int HINT_SHIFT_UP = 0;
    public static final int HINT_BRAKE = 1;
    public static final int HINT_ACCELERATION = 2;
    public static final int HINT_CRUISING = 3;
    public static final long HINT_SPAM_PROTECTION_DURATION = 25000;


    //Fuel Meter
    public static final int FUEL_METER_SPEED_THRESHOLD = 15;

//    // Constants
//    // The authority for the sync adapter's content provider
//    public static final String AUTHORITY = "app.gamecar.sparkworks.net.gamecardatalogger";
//    // An account type, in the form of a domain name
//    public static final String ACCOUNT_TYPE = "gcdb.sparkworks.net";
//    // The account name
//    public static final String ACCOUNT = "gamecaraccount";
    public static final String USER = "username";
    public static final String CODE = "code";

    // HUD Settings
    public static final String HS_HINTS_PREF = "HSHintsPref";
    public static final int HS_HINTS_PREF_OFF = 0;
    public static final int HS_HINTS_PREF_ON = 1;
    public static final int HS_HINTS_PREF_ON_SOUND = 2;
    public static final String HS_HUD_ANIM_PREF = "HSHUDAnimPref";
//    public static final int HS_HUD_ANIM_PREF_OFF = 0;
    public static final int HS_HUD_ANIM_PREF_ON = 1;
    public static final int HS_HUD_ANIM_PREF_ON_GPU = 2;
    public static final String HS_HINT_DUR_PREF = "HSHintDurPref";
    public static final String HS_HUD_VOLUME_PREF = "HSHUDVolumePref";
    public static final long ABORT_THRESHOLD = 15000;
    public static final long ECOSCORE_CALCULATION_INTERVAL = 30000;
    public static final long FUEL_CONSUMPTION_CALCULATION_INTERVAL = 3000;
    public static final long HINT_EXPORT_INTERVAL = 1000;
    public static final long AGGRESSIVENESS_SCORE_CALCULATION = 30000;
    public static final long CRUISING_SCORE_CALCULATION = 10000;

    // Game Settings
    public static final String GS_GAME_VOLUME_PREF = "GSGameVolumePref";

    // Risk Settings

    public static final String RS_NULL_VAL_PREF = "RSNullValPref";
    public static final String RS_LOW_VAL_PREF = "RSLowValPref";
    public static final String RS_MEDIUM_VAL_PREF = "RSMediumValPref";
    public static final String RS_HIGH_VAL_PREF = "RSHighValPref";
    public static final String RS_APPLY_PREF = "applyRiskLevelPref";
    public static final String RS_CONSTANT_PREF = "RSConstantPref";

    public static final Double RS_NULL_VAL_DEFAULT = 0.0;
    public static final Double RS_LOW_VAL_DEFAULT = 2.0;
    public static final Double RS_MEDIUM_VAL_DEFAULT = 3.0;
    public static final Double RS_HIGH_VAL_DEFAULT = 5.0;
    public static final Boolean RS_APPLY_DEFAULT = true;
    public static final int RS_CONSTANT_DEFAULT = 0;



    // First Run indicator
    public static final String APP_FIRST_RUN = "AppFirstRunPref";

    //Pending requests types
    public static final int PR_NEW_MISSION = 0;
    public static final int PR_MISSION_ACCOMPLISHED = 1;
    public static final int PR_NEW_KNOWLEDGE_CARD = 2;
    public static final int PR_ANSWERED_KNOWLEDGE_CARD = 3;
    public static final int PR_NEW_BADGE = 4;
    public static final int PR_MISSION_PROGRESS = 5;
    public static final int PR_DELETE_KNOWLEDGE_CARD = 6;
    public static final int PR_ADD_LP = 7;
    public static final int PR_ADD_XP = 8;
    public static final int PR_DRIVER_UPDATE = 9;

    //Pending requests Pref
    public static final String PR_PREF = "pendingRequestPref";
    public static final String PR_IDENTIFIER_PRE = "pendingRequest_";

    //Last Sync time
    public static final String LAST_SYNC_TIME_PREF = "lastSyncTimePref";
    public static final long RESYNC_TIME_LIMIT = 20*60*1000;

    //Crew Statuses
    static final int CREW_STATUS_RECRUIT = 0;
    public static final int CREW_STATUS_MEMBER = 1;
    public static final int CREW_STATUS_CAN_RECRUIT = 2;
    public static final int CREW_STATUS_CAN_DISMISS = 4;
    public static final int CREW_STATUS_CAN_DISBAND = 8;
    public static final int CREW_STATUS_CAN_EDIT = 16;
    public static final int CREW_STATUS_ADMIN = CREW_STATUS_MEMBER | CREW_STATUS_CAN_RECRUIT | CREW_STATUS_CAN_DISMISS | CREW_STATUS_CAN_DISBAND | CREW_STATUS_CAN_EDIT;

    //Level thresholds
    public static final int LT_LOOTBOXES = 5;
    public static final int LT_LEADERBOARDS = 5;
    public static final int LT_ALBUMS = 5;
    public static final int LT_DAILY_MISSIONS = 5;
    public static final int LT_CREATE_CREW = 15;
}
