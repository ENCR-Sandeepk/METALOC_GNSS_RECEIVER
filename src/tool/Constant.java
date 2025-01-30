/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

/**
 *
 * @author sandeepk
 */
public class Constant {

    final public static String VERSION = "v-0.H";
    final public static String COPYRIGHT = "© 2024 Encardio-rite Electronics Pvt. Ltd. India";

    // RECEIVED DATA INTERVAL (SECONDS)
    public static int RDI_1_SEC = 1;
    public static int RDI_2_SEC = 2;
    public static int RDI_5_SEC = 5;
    public static int RDI_10_SEC = 10;
    public static int RDI_15_SEC = 15;
    public static int RDI_30_SEC = 30;
    public static int RDI_1_MIN = 60;
    public static int RDI_5_MIN = 300;

    // FIXED AVG FILTER OPTIONS (SECONDS)
    public static int FAF_1_MIN = 60;
    public static int FAF_5_MIN = 300;
    public static int FAF_15_MIN = 900;
    public static int FAF_30_MIN = 1800;
    public static int FAF_60_MIN = 3600;

    // MAF STRATEGY  (AVG BUCKET COUNT)
    public static int S_1_MIN_10 = 1;
    public static int S_2_MIN_30 = 3;
    public static int S_3_HOUR_1 = 6;
    public static int S_4_HOUR_2 = 12;
    public static int S_5_HOUR_3 = 18;
    public static int S_6_HOUR_4 = 24;
    public static int S_7_HOUR_6 = 36;
    public static int S_8_HOUR_8 = 48;
    public static int S_9_HOUR_12 = 72;
    public static int S_10_HOUR_24 = 144;
    public static int S_11_HOUR_48 = 288;

    // MAF STRATEGY  (AVG BUCKET COUNT)
    public static int DATA_REPORTING_INTERVAL_1_SEC = 1;
    public static int DATA_REPORTING_INTERVAL_2_SEC = 2;
    public static int DATA_REPORTING_INTERVAL_5_SEC = 5;
    public static int DATA_REPORTING_INTERVAL_10_SEC = 10;
    public static int DATA_REPORTING_INTERVAL_15_SEC = 15;
    public static int DATA_REPORTING_INTERVAL_30_SEC = 30;
    public static int DATA_REPORTING_INTERVAL_1_MIN = 60;
    public static int DATA_REPORTING_INTERVAL_5_MIN = 5 * 60;
    public static int DATA_REPORTING_INTERVAL_10_MIN = 10 * 60;
    public static int DATA_REPORTING_INTERVAL_15_MIN = 15 * 60;
    public static int DATA_REPORTING_INTERVAL_30_MIN = 30 * 60;
    public static int DATA_REPORTING_INTERVAL_1_HOUR = 3600;
    public static int DATA_REPORTING_INTERVAL_2_HOUR = 2 * 3600;
    public static int DATA_REPORTING_INTERVAL_3_HOUR = 3 * 3600;
    public static int DATA_REPORTING_INTERVAL_6_HOUR = 6 * 3600;
    public static int DATA_REPORTING_INTERVAL_12_HOUR = 12 * 3600;
    public static int DATA_REPORTING_INTERVAL_24_HOUR = 24 * 3600;

    // 
    public static int FTP_TIMEOUT = 10 * 60 * 1000;

    public static boolean DEBUG = false;
    final public static int WAIT_FOR_REPLY = 1000;

    final public static String COMPANY_FOLDER = "/EncardioRite";
    final public static String ROOT_FOLDER = "/GNSS";
    final public static String THEME_FOLDER = "/THEME";
    final public static String LAST_DATA_FOLDER = "/LDF";
    final public static String FTP_FLAG_FOLDER = "/FTP_FLAG";
    final public static String STATIC_CSV_FOLDER = "/STATIC_CSV";
    final public static String DYNAMIC_CSV_FOLDER = "/DYNAMIC_CSV";
    final public static String CSV_TO_UPLOAD_FOLDER = "/FILES_FOR_UPLOAD";
    final public static String CONFIG_FILE = "/config.txt";
    final public static String THEME_FILE = "/theme.txt";
    final public static String FTP_FLAG_FILE = "/ftp_flag.txt";
    final public static String FTP_FILE = "/ftp.txt";
    final public static String STORAGE_FILE = "/storage.txt";
    final public static String SAMPLING_FILE = "/sampling.txt";
    final public static String IP_PORT_FILE = "/ip_port.txt";
    final public static String PARAMETER_FILE = "/parameter.txt";
    final public static String BASE_LOCATION_FILE = "/base_location.txt";
    final public static String INITIAL_READING_FOLDER = "/INITIAL_READINGS";
    final public static String REPORTING_DATA_TYPE_FILE = "/reporting_data_type.txt";
    final public static String AUTO_START_FILE = "/auto_start.txt";

    public static int MAX_ROVER = 100;
    public static int MAX_FAF = 600;
    public static int MAX_MAF = 288;
    public static int MAX_PARAMETER = 3;
    public static int MAX_GRAPH_DATA_POINT = 3600;

    public static int ROVER_ID = 0;
    public static int BASE_ID = 1;
    public static int GNSS_TIME = 2;
    public static int RMS_VALUE = 3;
    public static int LATTITUDE = 4;
    public static int LONGITUDE = 5;
    public static int dEASTING = 6;
    public static int dNORTHING = 7;
    public static int dALTITUDE = 8;
    public static int RATIO_FACTOR = 9;
    public static int UNKNOWN = 10;
    public static int GPS = 11;
    public static int BEIDOU = 12;
    public static int GLONASS = 13;
    public static int GALILEO = 14;

    public static int EAST = 0;
    public static int NORTH = 1;
    public static int ALTITUDE = 2;

}
