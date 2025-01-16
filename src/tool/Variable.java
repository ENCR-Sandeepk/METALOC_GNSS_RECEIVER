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
public class Variable {

    // Progress Bar
    public static boolean progressBarRunning;
    public static float progress = 0.0f;
    public static int counter = 0;
    public static float prograssBarSteps = 0.0f;
    public static String progress_bar_msg = "";

    public static int faf_filter_size = 0; // number of samples for fixed averaging
    public static int received_data_interval_sec = 1;
    public static int fixed_avg_filter_sec = 600;
    public static int maf_bucket_size = 0; // number of buckets for moving avg
    public static int base_line_duration = 48 * 60 * 60;

    public static String received_data_interval = "1 Second";
    public static String moving_avg = "Strategy - 1";

    public static String static_scan_interval = "1 Second";

    public static String scan_start_time = "00:00";
    public static boolean static_scanning_status = false;
    public static boolean dynamic_scanning_status = false;
    public static String dynamic_scan_interval = "10 Minute";
    public static String dynamic_scan_duration = "10 Second";
    public static boolean trigger_scanning_status = false;
    public static String trigger_pre_data_duration = "10 Second";
    public static String trigger_post_data_duration = "10 Second";

    public static float trigger_value_n = 5;
    public static float trigger_value_e = 5;
    public static float trigger_value_a = 10;

    public static float trigger_value_n_meter = 5;
    public static float trigger_value_e_meter = 5;
    public static float trigger_value_a_meter = 10;

    public static int static_scan_interval_sec = 1;
    public static int dynamic_scan_interval_sec = 600;
    public static int dynamic_scan_duration_sec = 10;
    public static int trigger_pre_data_duration_sec = 10;
    public static int trigger_post_data_duration_sec = 10;

    public static String fixed_avg_filter = "1 Hour";

    public static int post_data_trigger_counter = 10;
    public static int pre_data_trigger_counter = 10;
    public static int max_rec_per_file_dynamic = 10;

    public static boolean[] is_pre_trigger_data_save = new boolean[Constant.MAX_ROVER];
    public static int[] trigger_counter = new int[Constant.MAX_ROVER];
    public static int[] dynamic_scan_counter = new int[Constant.MAX_ROVER];
    public static int[] file_size_counter = new int[Constant.MAX_ROVER];
    public static boolean[] is_event_running = new boolean[Constant.MAX_ROVER];
    public static boolean[] is_dynamic_scan_running = new boolean[Constant.MAX_ROVER];

    public static long[] static_scan_time_in_ms = new long[Constant.MAX_ROVER];
    public static long[] dynamic_scan_time_in_ms = new long[Constant.MAX_ROVER];

    public static float graph_data_array[][][] = new float[Constant.MAX_ROVER][Constant.MAX_GRAPH_DATA_POINT][Constant.MAX_PARAMETER - 1];
    public static int graph_data_array_counter[] = new int[Constant.MAX_ROVER];
    public static boolean graph_data_change[] = new boolean[Constant.MAX_ROVER];
    public static boolean is_first_data[] = new boolean[Constant.MAX_ROVER];

    public static String base_data_filling[] = new String[Constant.MAX_PARAMETER];
//    public static int first_cycle_count[] = new int[Constant.MAX_ROVER];
    public static float faf_array[][][] = new float[Constant.MAX_ROVER][Constant.MAX_FAF][Constant.MAX_PARAMETER];

    public static int faf_array_index[] = new int[Constant.MAX_ROVER];
    public static boolean faf_array_over[] = new boolean[Constant.MAX_ROVER];

    public static int base_line_count[] = new int[Constant.MAX_ROVER];
    public static boolean base_line_readed[] = new boolean[Constant.MAX_ROVER];

    public static float maf_array[][][] = new float[Constant.MAX_ROVER][Constant.MAX_MAF][Constant.MAX_PARAMETER];
    public static int maf_array_index[] = new int[Constant.MAX_ROVER];
    public static boolean maf_array_over[] = new boolean[Constant.MAX_ROVER];

    public static String index_array[] = new String[Constant.MAX_ROVER];

    public static float base_data[][] = new float[Constant.MAX_ROVER][Constant.MAX_PARAMETER];

    public static boolean got_reply = false;
    public static String reply = "";
    public static String static_current_csv_file = "---.csv";
    public static String dynamic_current_csv_file = "---.csv";
    public static String google_map_information = "";

    public static boolean is_deviation_data_reporting = true;
    public static boolean header_in_csv_file = true;

    // Auto Start App Variable
    public static boolean is_app_running = true;
    public static boolean is_auto_start = true;
    public static boolean is_app_close_by_user = true;

    public static String latitude = "";
    public static String longitude = "";

    public static String baae_latitude = "";
    public static String baae_longitude = "";

    public static String ftp_url = "0.0.0.0";
    public static String ftp_port = "";
    public static String ftp_user = "";
    public static String ftp_password = "";
    public static String ftp_upload_enable = "false";
    public static String ftp_upload_enableS = "false";
    public static String ftp_upload_enableD = "false";
    public static boolean is_uploaded = false;
    public static boolean upload_watch = false;
    public static long last_upload_attempt = 0;

    public static boolean fixed_data_point_graph = true;
    public static boolean scatter_with_smooth_line_graph = true;

    public static String ip = "192.168.10.";
    public static String port = "3001";

    public static long static_measurement_file_time_stamp = 0;
    public static long dynamic_measurement_file_time_stamp = 0;
    public static long interval_time_stamp = 60000;

    public static long auto_save_ldf_time = 0;
    public static boolean auto_save_ldf_time_run = false;

    public static String time_picker_hh_mm = "";
    public static String set_time_picker_hh_mm = "";

    // Msg Dialog
    public static boolean is_dialog_confirmed = false;
    public static String dialog_message = "";
    public static String dialog_ok_btn_text = "";
    public static String dialog_cancel_btn_text = "";

    // Storage Location 
    public static boolean isConfigFileFolderDone = false;
    public static String storage_location = "";
    public static String store_capture_data = "true";   // free variable we can use in future. right now we are not using
    public static String max_rec_per_file = "1 Minute";
    public static String file_upload_action = "Archive";
    public static String date_time_formate = "yyyy/MM/dd HH:mm:ss";
    public static boolean avg_type_moving = false;
    public static int avg_value = 1;

    // Theme Color
    public static String theme_selected = "Theme-5";
    public static String theme_color = "90CAF9FF";
    public static String theme_header = "Option-1";

    public static String reporting_unit = "meter";

    public static String base_reading_duration = "48 hour";

    // Header 
    public static String ROVER_ID_1 = "true";
    public static String BASE_ID_2 = "true";
    public static String GNSS_TIME_3 = "true";
    public static String RMS_VALUE_4 = "true";
    public static String LATTITUDE_5 = "true";
    public static String LONGITUDE_6 = "true";
    public static String dEASTING_7 = "true";
    public static String dNORTHING_8 = "true";
    public static String dALTITUDE_9 = "true";
    public static String RATIO_FACTOR_10 = "true";
    public static String UNKNOWN_11 = "true";
    public static String GPS_12 = "true";
    public static String BEIDOU_13 = "true";
    public static String GLONASS_14 = "true";
    public static String GALILEO_15 = "true";

    public static boolean got_reply_1 = false;
    public static String reply_1 = "";

}
