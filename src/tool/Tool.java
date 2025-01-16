/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.CharacterIterator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.apache.commons.io.FileDeleteStrategy;

/**
 *
 * @author sandeepk
 */
public class Tool {

    public static boolean create_data_folder() {
        try {
            String path = backslashReplace(System.getProperty("user.home"));
            File dir = new File(path + Constant.COMPANY_FOLDER);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            dir = new File(path + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            dir = new File(path + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.THEME_FOLDER);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            dir = new File(path + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.INITIAL_READING_FOLDER);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            dir = new File(path + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.LAST_DATA_FOLDER);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean create_data_folder(String path) {
        try {
            File dir = new File(path + Constant.COMPANY_FOLDER);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            dir = new File(path + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            dir = new File(path + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.STATIC_CSV_FOLDER);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            dir = new File(path + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.DYNAMIC_CSV_FOLDER);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            dir = new File(path + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.CSV_TO_UPLOAD_FOLDER);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkConfigFile() {
        String path = backslashReplace(System.getProperty("user.home"));
        path = path + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.CONFIG_FILE;
        File fis = new File(path);
        return fis.exists();
    }

    public static void readConfigFile() {
        String configFilePath = backslashReplace(System.getProperty("user.home"));
        configFilePath = configFilePath + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.CONFIG_FILE;
        File file = new File(configFilePath);
        if (file.exists()) {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(configFilePath));
                int temp = 1;
                String tempStr = "";
                while ((tempStr = br.readLine()) != null) {
                    switch (temp) {
                        case 1:
                            Variable.storage_location = tempStr.trim();
                            break;
                    }
                    temp++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(Tool.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void save_ip_port(String ip, String port) {
        String path = backslashReplace(System.getProperty("user.home"));
        FileWriter fw;
        try {
            File file1 = new File(path + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.IP_PORT_FILE);
            file1.createNewFile();
            fw = new FileWriter(file1);
            PrintWriter pw = new PrintWriter(fw);
            // Write to file for the first row
            pw.println(ip);
            pw.println(port);
            // Flush the output to the file
            pw.flush();
            // Close the Print Writer
            pw.close();
            // Close the File Writer
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
            new Call_Dialog().showDialog("Exception in createConfigFile !!", "", "OK", "");
        }
    }

    public static void read_ip_port() {
        String configFilePath = backslashReplace(System.getProperty("user.home"));
        configFilePath = configFilePath + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.IP_PORT_FILE;

        File file = new File(configFilePath);

        if (file.exists()) {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(configFilePath));
                int temp = 1;
                String tempStr = "";

                while ((tempStr = br.readLine()) != null) {

                    switch (temp) {
                        case 1:
                            Variable.ip = tempStr.trim();
                            break;
                        case 2:
                            Variable.port = tempStr.trim();
                            break;
                    }
                    temp++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(Tool.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void save_base_location(String latitude, String longitude) {
        String path = backslashReplace(System.getProperty("user.home"));
        FileWriter fw;
        try {
            File file1 = new File(path + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.BASE_LOCATION_FILE);
            file1.createNewFile();
            fw = new FileWriter(file1);
            PrintWriter pw = new PrintWriter(fw);
            // Write to file for the first row
            pw.println(latitude);
            pw.println(longitude);
            // Flush the output to the file
            pw.flush();
            // Close the Print Writer
            pw.close();
            // Close the File Writer
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
            new Call_Dialog().showDialog("Exception in createConfigFile !!", "", "OK", "");
        }
    }

    public static void read_base_location() {
        String configFilePath = backslashReplace(System.getProperty("user.home"));
        configFilePath = configFilePath + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.BASE_LOCATION_FILE;

        File file = new File(configFilePath);

        if (file.exists()) {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(configFilePath));
                int temp = 1;
                String tempStr = "";

                while ((tempStr = br.readLine()) != null) {

                    switch (temp) {
                        case 1:
                            Variable.baae_latitude = tempStr.trim();
                            break;
                        case 2:
                            Variable.baae_longitude = tempStr.trim();
                            break;
                    }
                    temp++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(Tool.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void save_parameter(String param_1, String param_2, String param_3, String param_4, String param_5, String param_6, String param_7, String param_8, String param_9, String param_10, String param_11, String param_12, String param_13, String param_14, String param_15, String base_reading_duration) {
        String path = backslashReplace(System.getProperty("user.home"));
        FileWriter fw;
        try {
            File file1 = new File(path + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.PARAMETER_FILE);
            file1.createNewFile();
            fw = new FileWriter(file1);
            PrintWriter pw = new PrintWriter(fw);
            // Write to file for the first row
            pw.println(param_1);
            pw.println(param_2);
            pw.println(param_3);
            pw.println(param_4);
            pw.println(param_5);
            pw.println(param_6);
            pw.println(param_7);
            pw.println(param_8);
            pw.println(param_9);
            pw.println(param_10);
            pw.println(param_11);
            pw.println(param_12);
            pw.println(param_13);
            pw.println(param_14);
            pw.println(param_15);
            pw.println(base_reading_duration);

            // Flush the output to the file
            pw.flush();
            // Close the Print Writer
            pw.close();
            // Close the File Writer
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
            new Call_Dialog().showDialog("Exception in createConfigFile !!", "", "OK", "");
        }
    }

    public static void read_parameter() {
        String configFilePath = backslashReplace(System.getProperty("user.home"));
        configFilePath = configFilePath + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.PARAMETER_FILE;

        File file = new File(configFilePath);

        if (file.exists()) {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(configFilePath));
                int temp = 1;
                String tempStr = "";

                while ((tempStr = br.readLine()) != null) {

                    switch (temp) {
                        case 1:
                            Variable.ROVER_ID_1 = tempStr.trim();
                            break;
                        case 2:
                            Variable.BASE_ID_2 = tempStr.trim();
                            break;
                        case 3:
                            Variable.GNSS_TIME_3 = tempStr.trim();
                            break;
                        case 4:
                            Variable.RMS_VALUE_4 = tempStr.trim();
                            break;
                        case 5:
                            Variable.LATTITUDE_5 = tempStr.trim();
                            break;
                        case 6:
                            Variable.LONGITUDE_6 = tempStr.trim();
                            break;
                        case 7:
                            Variable.dEASTING_7 = tempStr.trim();
                            break;
                        case 8:
                            Variable.dNORTHING_8 = tempStr.trim();
                            break;
                        case 9:
                            Variable.dALTITUDE_9 = tempStr.trim();
                            break;
                        case 10:
                            Variable.RATIO_FACTOR_10 = tempStr.trim();
                            break;
                        case 11:
                            Variable.UNKNOWN_11 = tempStr.trim();
                            break;
                        case 12:
                            Variable.GPS_12 = tempStr.trim();
                            break;
                        case 13:
                            Variable.BEIDOU_13 = tempStr.trim();
                            break;
                        case 14:
                            Variable.GLONASS_14 = tempStr.trim();
                            break;
                        case 15:
                            Variable.GALILEO_15 = tempStr.trim();
                            break;
                        case 16:
                            Variable.base_reading_duration = tempStr.trim();
                            break;
                    }
                    temp++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(Tool.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void save_reporting_data(String is_deviation, String header_in_csv_file) {
        String path = backslashReplace(System.getProperty("user.home"));
        FileWriter fw;
        try {
            File file1 = new File(path + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.REPORTING_DATA_TYPE_FILE);
            file1.createNewFile();
            fw = new FileWriter(file1);
            PrintWriter pw = new PrintWriter(fw);
            // Write to file for the first row
            pw.println(is_deviation);
            pw.println(header_in_csv_file);

            // Flush the output to the file
            pw.flush();
            // Close the Print Writer
            pw.close();
            // Close the File Writer
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
            new Call_Dialog().showDialog("Exception in save_reporting_data !!", "", "OK", "");
        }
    }

    public static void read_reporting_data() {
        String configFilePath = backslashReplace(System.getProperty("user.home"));
        configFilePath = configFilePath + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.REPORTING_DATA_TYPE_FILE;

        File file = new File(configFilePath);

        if (file.exists()) {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(configFilePath));
                int temp = 1;
                String tempStr = "";

                while ((tempStr = br.readLine()) != null) {

                    switch (temp) {
                        case 1:
                            Variable.is_deviation_data_reporting = tempStr.trim().equalsIgnoreCase("true");
                            break;
                        case 2:
                            Variable.header_in_csv_file = tempStr.trim().equalsIgnoreCase("true");
                            break;
                    }
                    temp++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(Tool.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void save_auto_start_data(boolean is_app_running, boolean is_auto_start, boolean closing_status) {
        String path = backslashReplace(System.getProperty("user.home"));
        FileWriter fw;
        try {
            File file1 = new File(path + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.AUTO_START_FILE);
            file1.createNewFile();
            fw = new FileWriter(file1);
            PrintWriter pw = new PrintWriter(fw);
            // Write to file for the first row
            pw.println(is_app_running);
            pw.println(is_auto_start);
            pw.println(closing_status);
            // Flush the output to the file
            pw.flush();
            // Close the Print Writer
            pw.close();
            // Close the File Writer
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
            new Call_Dialog().showDialog("Exception in save_reporting_data !!", "", "OK", "");
        }
    }

    public static void read_auto_start_data() {
        String configFilePath = backslashReplace(System.getProperty("user.home"));
        configFilePath = configFilePath + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.AUTO_START_FILE;

        File file = new File(configFilePath);

        if (file.exists()) {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(configFilePath));
                int temp = 1;
                String tempStr = "";

                while ((tempStr = br.readLine()) != null) {

                    switch (temp) {
                        case 1:
                            Variable.is_app_running = tempStr.trim().equalsIgnoreCase("true");
                            break;
                        case 2:
                            Variable.is_auto_start = tempStr.trim().equalsIgnoreCase("true");
                            break;
                        case 3:
                            Variable.is_app_close_by_user = tempStr.trim().equalsIgnoreCase("true");
                            break;

                    }
                    temp++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(Tool.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void save_ftp(String url, String port, String user, String pass, String enable, String enableS, String enableD) {
        String path = backslashReplace(System.getProperty("user.home"));
        FileWriter fw;
        try {
            File file1 = new File(path + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.FTP_FILE);
            file1.createNewFile();
            fw = new FileWriter(file1);
            PrintWriter pw = new PrintWriter(fw);
            // Write to file for the first row
            pw.println(url);
            pw.println(port);
            pw.println(user);
            pw.println(pass);
            pw.println(enable);
            pw.println(enableS);
            pw.println(enableD);
            // Flush the output to the file
            pw.flush();
            // Close the Print Writer
            pw.close();
            // Close the File Writer
            fw.close();
        } catch (IOException e) {
            new Call_Dialog().showDialog("Exception in createConfigFile !!", "", "OK", "");
        }
    }

    public static void read_ftp() {

        String configFilePath = backslashReplace(System.getProperty("user.home"));
        configFilePath = configFilePath + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.FTP_FILE;

        File file = new File(configFilePath);

        if (file.exists()) {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(configFilePath));

                int temp = 1;
                String tempStr = "";

                while ((tempStr = br.readLine()) != null) {

                    switch (temp) {
                        case 1:
                            Variable.ftp_url = tempStr.trim();
                            break;
                        case 2:
                            Variable.ftp_port = tempStr.trim();
                            break;
                        case 3:
                            Variable.ftp_user = tempStr.trim();
                            break;
                        case 4:
                            Variable.ftp_password = tempStr.trim();
                            break;
                        case 5:
                            Variable.ftp_upload_enable = tempStr.trim();
                            break;
                        case 6:
                            Variable.ftp_upload_enableS = tempStr.trim();
                            break;
                        case 7:
                            Variable.ftp_upload_enableD = tempStr.trim();
                            break;
                    }
                    temp++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(Tool.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void save_storage(String store_capture_data, String max_rec_per_file, String upload_file_action, String date_time_format) {
        String path = backslashReplace(System.getProperty("user.home"));
        FileWriter fw;
        try {
            File file1 = new File(path + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.STORAGE_FILE);
            file1.createNewFile();
            fw = new FileWriter(file1);
            PrintWriter pw = new PrintWriter(fw);
            // Write to file for the first row
            pw.println(store_capture_data);
            pw.println(max_rec_per_file);
            pw.println(upload_file_action);
            pw.println(date_time_format);
            // Flush the output to the file
            pw.flush();
            // Close the Print Writer
            pw.close();
            // Close the File Writer
            fw.close();
        } catch (IOException e) {
            new Call_Dialog().showDialog("Exception in createConfigFile !!", "", "OK", "");
        }
    }

    public static void read_storage() {

        String configFilePath = backslashReplace(System.getProperty("user.home"));
        configFilePath = configFilePath + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.STORAGE_FILE;

        File file = new File(configFilePath);

        if (file.exists()) {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(configFilePath));

                int temp = 1;
                String tempStr = "";

                while ((tempStr = br.readLine()) != null) {

                    switch (temp) {
                        case 1:
                            Variable.store_capture_data = tempStr.trim();
                            break;
                        case 2:
                            Variable.max_rec_per_file = tempStr.trim();

                            switch (Variable.max_rec_per_file) {
                                case "1 Minute":
                                    Variable.interval_time_stamp = 60000;
                                    break;
                                case "2 Minute":
                                    Variable.interval_time_stamp = 2 * 60000;
                                    break;
                                case "5 Minute":
                                    Variable.interval_time_stamp = 5 * 60000;
                                    break;
                                case "10 Minute":
                                    Variable.interval_time_stamp = 10 * 60000;
                                    break;
                                case "15 Minute":
                                    Variable.interval_time_stamp = 15 * 60000;
                                    break;
                                case "20 Minute":
                                    Variable.interval_time_stamp = 20 * 60000;
                                    break;
                                case "30 Minute":
                                    Variable.interval_time_stamp = 30 * 60000;
                                    break;
                                case "60 Minute":
                                    Variable.interval_time_stamp = 60 * 60000;
                                    break;
                                default:
                                    break;
                            }

                            break;
                        case 3:
                            Variable.file_upload_action = tempStr.trim();
                            break;
                        case 4:
                            Variable.date_time_formate = tempStr.trim();
                            break;

                    }
                    temp++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(Tool.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void save_sampling_setting(String received_data_interval, String moving_avg_filter, String static_scan_interval, String scan_start_time,
            boolean static_scanning_status, boolean dynamic_scanning_status, String dynamic_scan_interval, String dynamic_scan_duration, boolean trigger_scanning_status,
            String trigger_pre_data, String trigger_post_data, String trigger_value_n, String trigger_value_e, String trigger_value_a, String reporting_unit) {
        String path = backslashReplace(System.getProperty("user.home"));
        FileWriter fw;
        try {
            File file1 = new File(path + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.SAMPLING_FILE);
            file1.createNewFile();
            fw = new FileWriter(file1);
            PrintWriter pw = new PrintWriter(fw);
            // Write to file for the first row
            pw.println(received_data_interval);
            pw.println(moving_avg_filter);
            pw.println(static_scan_interval);
            pw.println(scan_start_time);
            pw.println(static_scanning_status);
            pw.println(dynamic_scanning_status);
            pw.println(dynamic_scan_interval);
            pw.println(dynamic_scan_duration);
            pw.println(trigger_scanning_status);
            pw.println(trigger_pre_data);
            pw.println(trigger_post_data);
            pw.println(trigger_value_n);
            pw.println(trigger_value_e);
            pw.println(trigger_value_a);
            pw.println(reporting_unit);

            // Flush the output to the file
            pw.flush();
            // Close the Print Writer
            pw.close();
            // Close the File Writer
            fw.close();
        } catch (IOException e) {
            new Call_Dialog().showDialog("Exception in save_sampling_setting !!", "", "OK", "");
        }
    }

    public static void read_sampling_setting() {
        String configFilePath = backslashReplace(System.getProperty("user.home"));
        configFilePath = configFilePath + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.SAMPLING_FILE;
        File file = new File(configFilePath);
        if (file.exists()) {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(configFilePath));
                int temp = 1;
                String tempStr = "";
                while ((tempStr = br.readLine()) != null) {
                    switch (temp) {
                        case 1:
                            Variable.received_data_interval = tempStr.trim();
                            break;
                        case 2:
                            Variable.moving_avg = tempStr.trim();
                            break;
                        case 3:
                            Variable.static_scan_interval = tempStr.trim();
                            break;
                        case 4:
                            Variable.scan_start_time = tempStr.trim();
                            break;
                        case 5:
                            try {
                                Variable.static_scanning_status = tempStr.trim().equalsIgnoreCase("true");
                            } catch (Exception e) {
                            }
                            break;
                        case 6:
                            try {
                                Variable.dynamic_scanning_status = tempStr.trim().equalsIgnoreCase("true");
                            } catch (Exception e) {
                            }
                            break;
                        case 7:
                            Variable.dynamic_scan_interval = tempStr.trim();
                            Variable.dynamic_scan_interval_sec = Tool.get_data_in_sec(Variable.dynamic_scan_interval);
                            break;
                        case 8:
                            Variable.dynamic_scan_duration = tempStr.trim();
                            Variable.dynamic_scan_duration_sec = Tool.get_data_in_sec(Variable.dynamic_scan_duration);
                            break;
                        case 9:
                            try {
                                Variable.trigger_scanning_status = tempStr.trim().equalsIgnoreCase("true");
                            } catch (Exception e) {
                            }
                            break;
                        case 10:
                            Variable.trigger_pre_data_duration = tempStr.trim();
                            Variable.trigger_pre_data_duration_sec = Tool.get_data_in_sec(Variable.trigger_pre_data_duration);
                            break;
                        case 11:
                            Variable.trigger_post_data_duration = tempStr.trim();
                            Variable.trigger_post_data_duration_sec = Tool.get_data_in_sec(Variable.trigger_post_data_duration);
                            break;
                        case 12:
                            try {
                                Variable.trigger_value_n = Float.parseFloat(tempStr.trim());
                            } catch (Exception e) {
                                Variable.trigger_value_n = 5;
                            }
                            break;
                        case 13:
                            try {
                                Variable.trigger_value_e = Float.parseFloat(tempStr.trim());
                            } catch (Exception e) {
                                Variable.trigger_value_e = 5;
                            }
                            break;
                        case 14:
                            try {
                                Variable.trigger_value_a = Float.parseFloat(tempStr.trim());
                            } catch (Exception e) {
                                Variable.trigger_value_a = 10;
                            }
                            break;
                        case 15:
                            Variable.reporting_unit = tempStr.trim();

                            float conversion_multiplier = Tool.get_conversion_multiplier(Variable.reporting_unit);

                            Variable.trigger_value_e_meter = Variable.trigger_value_e * conversion_multiplier;
                            Variable.trigger_value_n_meter = Variable.trigger_value_n * conversion_multiplier;
                            Variable.trigger_value_a_meter = Variable.trigger_value_a * conversion_multiplier;

                            break;
                    }
                    temp++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(Tool.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void createConfigFile(String file, String data) {
        FileWriter fw;
        try {
            File file1 = new File(file);

            file1.createNewFile();
            fw = new FileWriter(file1);
            PrintWriter pw = new PrintWriter(fw);
            // Write to file for the first row
            pw.println(data);
            // Flush the output to the file
            pw.flush();
            // Close the Print Writer
            pw.close();
            // Close the File Writer
            fw.close();

        } catch (IOException e) {
            new Call_Dialog().showDialog("Exception in createConfigFile !!", "", "OK", "");
        }
    }

    public static void createConfigFile(String ip, String port, String autoLogClearValue, String append_CRLF) {

        String configFilePath = backslashReplace(System.getProperty("user.home"));
        configFilePath = configFilePath + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.CONFIG_FILE;

        FileWriter fw;
        try {
            File file = new File(configFilePath);

            file.createNewFile();
            fw = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fw);
            // Write to file for the first row
            pw.println(ip);
            pw.println(port);
            pw.println(autoLogClearValue);
            pw.println(append_CRLF);
            // Flush the output to the file
            pw.flush();
            // Close the Print Writer
            pw.close();
            // Close the File Writer
            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getCurrentTime(String foramt) {
//        "dd/MM/yyyy HH:mm:ss"
        return new SimpleDateFormat(foramt).format(new Date());
    }

    public static String removeDoubleQuotes(String text) {
        if ((text != null) && text.length() > 0) {
            if ((text.charAt(0) == '"') && (text.charAt(text.length() - 1) == '"')) {
                text = text.substring(1, text.length() - 1).trim();
            }
            return text;
        }
        return text;
    }

    public static String backslashReplace(String myStr) {
        final StringBuilder result = new StringBuilder();
        final StringCharacterIterator iterator = new StringCharacterIterator(myStr);
        char character = iterator.current();
        while (character != CharacterIterator.DONE) {
            if (character == '\\') {
                result.append("/");
            } else {
                result.append(character);
            }
            character = iterator.next();
        }
        return result.toString();
    }

    public static void disableTextField(Component[] comp) {
        for (int x = 0; x < comp.length; x++) {
            if (comp[x] instanceof JPanel) {
                disableTextField(((JPanel) comp[x]).getComponents());
            } else if (comp[x] instanceof JTextField) {
                ((JTextField) comp[x]).setEditable(false);
                return;
            }
        }
    }

    public static String convert_ms_into_date(long ms, String format) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(ms);
            return new SimpleDateFormat(format).format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void save_records(String file_path, String header, String data) {

        File file;
        FileWriter fr = null;
        BufferedWriter br = null;
        PrintWriter pr = null;
        boolean is_new_fie_created = false;
        try {
            file = new File(file_path);
            if (!file.exists()) {
                file.createNewFile();
                is_new_fie_created = true;
            }
            fr = new FileWriter(file, true);
            br = new BufferedWriter(fr);
            pr = new PrintWriter(br);

            if (is_new_fie_created && Variable.header_in_csv_file) {
                pr.println(header);
            }

            pr.println(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                pr.close();
                br.close();
                fr.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static long convert_date_time_into_ms(String time_stamp, String date_time_format) {

        try {
            // default date time format yyyy/MM/dd HH:mm:ss
            return new SimpleDateFormat(date_time_format).parse(time_stamp).getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String get_date_time_stamp(String date_time_format) {
        try {
            // default date time format yyyy/MM/dd HH:mm:ss
            return new SimpleDateFormat(date_time_format).format(new java.util.Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void delete_file(String file) {
        try {
            FileDeleteStrategy.FORCE.delete(new File(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String convertDateTimeFormat(String inputDateTime, String inputFormat, String outputFormat) {
        try {
            DateFormat inputDateFormat = new SimpleDateFormat(inputFormat);
            Date date = inputDateFormat.parse(inputDateTime);
            return new SimpleDateFormat(outputFormat).format(date);
        } catch (Exception e) {
            e.printStackTrace();
            new Call_Dialog().showDialog("Exception in convertDateTimeFormat !!", "", "OK", "");
            return "";

        }
    }

    public static long get_time_into_ms(String time, String format) {
        try {
            return new SimpleDateFormat(format).parse(time).getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String pad(String number, int digit) {
        while (number.length() < digit) {
            number = "0" + number;
        }
        return number;
    }

    public static String[] searchAllFileAndAddIntoCombobox(ComboBox comboBox, String path, String extension, boolean addExtension, boolean addFirstOption, String firstOptionValue, boolean addIntoCombobox) {
        String fileList = "";
        try {

            String temp;
            File[] files = finder(path, extension);

            if (addIntoCombobox) {
                comboBox.getItems().clear();
            }
            if (addIntoCombobox && addFirstOption) {
                comboBox.getItems().add(firstOptionValue);
            }

            for (int i = 0; i < files.length; i++) {
                temp = files[i].getName();

                fileList = fileList + temp + "\n";
                if (addIntoCombobox) {
                    if (addExtension) {
                        comboBox.getItems().add(temp);
                    } else {
                        comboBox.getItems().add(temp.substring(0, temp.length() - 4));
                    }
                }

            }

            try {
                comboBox.getSelectionModel().select(0);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
            new Call_Dialog().showDialog("Exception in searchAllFileAndAddIntoCombobox !!", "", "OK", "");
        }
        return fileList.split("\n");
    }

    public static String[] searchAllFile(String path, String extension) {
        String fileList = "";
        try {

            String temp;
            File[] files = finder(path, extension);

            for (File file : files) {
                temp = file.getName();
                fileList = fileList + temp + "\n";
            }

        } catch (Exception e) {
            e.printStackTrace();
            new Call_Dialog().showDialog("Exception in searchAllFileAndAddIntoCombobox !!", "", "OK", "");
        }
        return fileList.split("\n");
    }

    public static File[] finder(String dirName, final String extension) {

        File dir = new File(dirName);
        return dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String filename) {
                return filename.endsWith(extension);
            }
        });
    }

    public static String[] searchAllFolderInDirectory(String folderPath, ComboBox comboBox, boolean addIntoCombobox, boolean addFirstOption, String firstOptionValue) {
        String dirList = "";
        File folder = new File(folderPath);
        if (folder.isDirectory()) {
            File[] listOfFiles = folder.listFiles();
            for (File file : listOfFiles) {
                if (file.isDirectory()) {

                    dirList = dirList + file.getName() + "\n";
                }
            }
        }
        if (addIntoCombobox) {
            try {
                String loggerList[] = dirList.split("\n");
                comboBox.getItems().clear();

                if (addFirstOption) {
                    comboBox.getItems().add(firstOptionValue);
                }

                for (String loggerList1 : loggerList) {
                    try {
                        if (loggerList1 != null && loggerList1.trim().length() > 0) {
                            comboBox.getItems().add(loggerList1);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        new Call_Dialog().showDialog("Exception in searchAllFilesInDirectory !!", "", "OK", "");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Call_Dialog().showDialog("Exception in searchAllFilesInDirectory !!", "", "OK", "");
            }
        }

        return dirList.split("\n");
    }

    public static boolean validateName(String name) {

        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if ((c >= '0' && c <= '9') || ((c >= 'A' && c <= 'Z')) || ((c >= 'a' && c <= 'z')) || (c == '-') || (c == '_')) {
            } else {
                return false;
            }
        }
        return true;
    }

    public static boolean validateDuplicateFolder(String floder) {
        File dir = new File(floder);
        boolean exists = dir.exists();
        return (!exists);
    }

    public static boolean validateDuplicateFile(String file) {
        File dir = new File(file);
        boolean exists = dir.exists();
        return (!exists);
    }

    public static String[] searchAllFolderInDirectory(String folderPath) {
        String dirList = "";
        File folder = new File(folderPath);
        if (folder.isDirectory()) {
            File[] listOfFiles = folder.listFiles();
            for (File file : listOfFiles) {
                if (file.isDirectory()) {
                    dirList = dirList + file.getName() + "\n";
                }
            }
        }

        return dirList.split("\n");
    }

    public static String[] serachAllFilesInADirectory(String path, String extension) {
        String fileList = "";
        try {
            String temp;
            File[] files = finder(path, extension);
            for (File file : files) {
                temp = file.getName();
                fileList = fileList + temp + "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileList.split("\n");
    }

    public static String[] serachAllFilesInADirectory(String path, String extension, String file_name_contains) {
        String fileList = "";

        String temp;
        File[] files = finder(path, extension);
        for (File file : files) {
            temp = file.getName();
            if (temp.substring(0, file_name_contains.length()).equalsIgnoreCase(file_name_contains)) {
                fileList = fileList + temp + "\n";
            }
        }

        return fileList.split("\n");
    }

    public static boolean validateFTP_URL_AND_Password(String str) {

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ((c >= 48 && c <= 57) || ((c >= 65 && c <= 90)) || ((c >= 97 && c <= 122))) {
            } else {
                return false;
            }
        }
        return true;
    }

    public static void move_file(String source, String destination) {
        try {

            File file = new File(source);

            // renaming the file and moving it to a new location
            if (file.renameTo(new File(destination))) {
                // if file copied successfully then delete the original file
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void move_files(String source, String destination, String files[]) {
        try {
            new Thread() {
                @Override
                public void run() {
                    if ((files != null) && (files.length > 0) && (files[0].trim() != null) && (files[0].trim().length() > 4)) {
                        for (String file : files) {

                            move_file(source + "\\" + file, destination + "\\" + file);
                        }
                    }
                }
            }.start();
        } catch (Exception e) {
        }
    }

    /**
     * The moveToArchive
     *
     * @param filepath String filepath
     */
    public static void moveToArchive(String filepath) {
        try {
            String path = Variable.storage_location + Constant.COMPANY_FOLDER + "/" + Constant.ROOT_FOLDER + "/" + Constant.CSV_TO_UPLOAD_FOLDER + "\\Archive";
            File file1 = new File(path);
            if (file1.exists()) {
            } else {
                file1.mkdirs();
            }
            File selectedFile = new File(filepath);
            selectedFile.renameTo(new File(path + "\\" + selectedFile.getName()));
            writeAndDeleteFile(filepath, (path + "\\" + selectedFile.getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The writeAndDeleteFile
     *
     * @param fileSrc String fileSrc
     * @param fileDest String fileDest
     */
    private static void writeAndDeleteFile(String fileSrc, String fileDest) {
        InputStream inStream = null;
        OutputStream outStream = null;
        try {
            File srcFile = new File(fileSrc);
            File destFile = new File(fileDest);
            inStream = new FileInputStream(srcFile);
            outStream = new FileOutputStream(destFile);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, len);
            }
            inStream.close();

            outStream.close();
            srcFile.delete();
        } catch (Exception e) {
            // e.printStackTrace();
        } finally {
            try {
                inStream.close();
            } catch (Exception e) {
            }
            try {
                outStream.close();
            } catch (Exception e) {
            }
        }
    }

    public static String convert_DMS_to_Degree(String data) {
        try {
            String[] array = data.split(":");

            double degree = Double.parseDouble(array[0]);
            double minute = Double.parseDouble(array[1]);
            double second = Double.parseDouble(array[2]);

            double degrees = degree + (double) minute / 60 + (double) second / 3600;

            return "" + degrees;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String setDecimalDigits(String paraValue_para1, int decimalPlaces) {
        try {
            Float paraValue = Float.parseFloat(paraValue_para1);
            if (0.0000f == paraValue) // xxxx.xxxx
            {
                paraValue_para1 = String.format(Locale.US, "%1." + decimalPlaces + "f", paraValue);
            } else if ((0.0001f <= paraValue) && (paraValue <= 10000.0f)) // xxxx.xxxx
            {
                paraValue_para1 = String.format(Locale.US, "%1." + decimalPlaces + "f", paraValue);
            } else if ((-0.0001f >= paraValue) && (paraValue >= -10000.0f)) // xxxx.xxxx
            {
                paraValue_para1 = String.format(Locale.US, "%1." + decimalPlaces + "f", paraValue);
            } else {
                paraValue_para1 = String.format(Locale.US, "%1." + decimalPlaces + "E", paraValue);
            }
        } catch (NumberFormatException e) {
        }
        return paraValue_para1;
    }

    public static String setDecimalDigitsWithoutE(String paraValue_para1, int decimalPlaces) {
        try {

            Float paraValue = Float.parseFloat(paraValue_para1);
            paraValue_para1 = String.format(Locale.US, "%." + decimalPlaces + "f", paraValue);

        } catch (NumberFormatException e) { 
            decimalPlaces = 2;
            e.printStackTrace();
        }
        return paraValue_para1;
    }

    public static int get_rover_index(String id) {
        try {
            for (int i = 0; i < Constant.MAX_ROVER; i++) {
                if (Variable.index_array[i] == null || Variable.index_array[i].trim().length() < 2) {
                    Variable.index_array[i] = id;
                    return i;
                } else if (Variable.index_array[i].equals(id)) {
                    // Getting Existing Rover Index
                    return i;
                }
            }
            return 100;
        } catch (Exception e) {
            e.printStackTrace();
            return 100;
        }
    }

    public static int get_rover_index(String id, String path, String data[]) {
        try {
            boolean rover_found = false;
            int rover_index = 0;
            if (data != null) {
                for (int i = 0; i < Constant.MAX_ROVER; i++) {
                    if (Variable.index_array[i] == null || Variable.index_array[i].trim().length() < 3) {
                        // Assigning Index for New Rover
                        Variable.index_array[i] = id;
                        rover_found = true;
                        rover_index = i;
                    } else if (Variable.index_array[i].equalsIgnoreCase(id)) {
                        // Getting Existing Rover Index
                        rover_found = true;
                        rover_index = i;
                    }

                    if (rover_found) {
                        if (!Variable.is_first_data[i]) {

                            // Read Base Line (Initial Data)
                            read_base_line_data(path + Constant.INITIAL_READING_FOLDER + "/" + id + ".txt", i);

                            // Read LDF (Last Data Found)
                            read_ldf_data(path + Constant.LAST_DATA_FOLDER + "/" + id, i);

                            if (Variable.base_line_count[i] < Variable.base_line_duration) {
//                                Variable.base_line_count[i] = 0;
                            } else {
                                Variable.faf_array_over[i] = true;
                                Variable.maf_array_over[i] = true;

                            }
                            Variable.is_first_data[i] = true;
                        }
                        return rover_index;
                    }
                }
            }
            return 100;
        } catch (Exception e) {
            return 100;
        }
    }

    public static void createFile(String path, String data, boolean isCreateNewFile) {

        FileWriter fw;
        String fileName = path;

        try {
            File file1 = new File(fileName);

            if (isCreateNewFile) {

                file1.createNewFile();
                fw = new FileWriter(file1);
                PrintWriter pw = new PrintWriter(fw);
                pw.print(data);
                pw.flush();
                pw.close();
                fw.close();

            } else if (!file1.exists()) {
                file1.createNewFile();
                fw = new FileWriter(file1);
                PrintWriter pw = new PrintWriter(fw);
                pw.print(data);
                pw.flush();
                pw.close();
                fw.close();
            }

        } catch (Exception e) {
        }
    }

    public static void create_base_line_data(String path, int index) {

        FileWriter fw;
        String fileName = path;

        try {
            File file1 = new File(fileName);
            if (!file1.exists()) {
                file1.createNewFile();
            }
            fw = new FileWriter(file1);
            PrintWriter pw = new PrintWriter(fw);
            pw.println((Variable.base_line_count[index]));
            pw.println(Variable.base_data[index][0] + "," + Variable.base_data[index][1] + "," + Variable.base_data[index][2]);
            pw.flush();
            pw.close();
            fw.close();

        } catch (Exception e) {
        }
    }

    public static void read_base_line_data(String path, int rover_index) {

        File file = new File(path);

        if (file.exists()) {
            String tempStr = "";
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(path));
                int temp = 1;

                while ((tempStr = br.readLine()) != null) {

                    switch (temp) {
                        case 1:
                            try {
                                Variable.base_line_count[rover_index] = Integer.parseInt(tempStr.trim());
                            } catch (Exception e) {
                            }
                            break;
                        case 2:
                            try {
                                String base[] = tempStr.trim().split(",");
                                Variable.base_data[rover_index][0] = Float.parseFloat(base[0]);
                                Variable.base_data[rover_index][1] = Float.parseFloat(base[1]);
                                Variable.base_data[rover_index][2] = Float.parseFloat(base[2]);
                            } catch (Exception e) {
                            }
                            break;
                    }
                    temp++;
                }
            } catch (Exception e) {
            } finally {
                try {
                    br.close();
                } catch (IOException ex) {

                }
            }
        }

    }

    public static String readFile(String path) {

        File file = new File(path);

        if (file.exists()) {
            String tempStr = "";
            String first_line = "";
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(path));
                int temp = 1;

                while ((tempStr = br.readLine()) != null) {

                    switch (temp) {
                        case 1:
                            first_line = tempStr.trim();
                            break;

                    }
                    temp++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(Tool.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return first_line;
        } else {
            return "";
        }
    }

    public static int get_received_data_interval(int index) {
        try {
            switch (index) {
                case 0:
                    return Constant.RDI_1_SEC;
                case 1:
                    return Constant.RDI_2_SEC;
                case 2:
                    return Constant.RDI_5_SEC;
                case 3:
                    return Constant.RDI_10_SEC;
                case 4:
                    return Constant.RDI_15_SEC;
                case 5:
                    return Constant.RDI_30_SEC;
                case 6:
                    return Constant.RDI_1_MIN;
                case 7:
                    return Constant.RDI_5_MIN;
            }
        } catch (Exception e) {
        }
        return Constant.RDI_1_SEC;
    }

    public static int get_base_line_duration(String base_reading_duration) {
        try {
            if (base_reading_duration.equalsIgnoreCase("1 hour")) {
                return (1 * 60 * 60); // hh*mm*ss
            } else if (base_reading_duration.equalsIgnoreCase("2 hour")) {
                return (2 * 60 * 60); // hh*mm*ss
            } else if (base_reading_duration.equalsIgnoreCase("3 hour")) {
                return (3 * 60 * 60); // hh*mm*ss
            } else if (base_reading_duration.equalsIgnoreCase("4 hour")) {
                return (4 * 60 * 60); // hh*mm*ss
            } else if (base_reading_duration.equalsIgnoreCase("6 hour")) {
                return (6 * 60 * 60); // hh*mm*ss
            } else if (base_reading_duration.equalsIgnoreCase("8 hour")) {
                return (8 * 60 * 60); // hh*mm*ss
            } else if (base_reading_duration.equalsIgnoreCase("12 hour")) {
                return (12 * 60 * 60); // hh*mm*ss
            } else if (base_reading_duration.equalsIgnoreCase("16 hour")) {
                return (16 * 60 * 60); // hh*mm*ss
            } else if (base_reading_duration.equalsIgnoreCase("24 hour")) {
                return (26 * 60 * 60); // hh*mm*ss
            } else if (base_reading_duration.equalsIgnoreCase("36 hour")) {
                return (36 * 60 * 60); // hh*mm*ss
            } else if (base_reading_duration.equalsIgnoreCase("48 hour")) {
                return (48 * 60 * 60); // hh*mm*ss
            } else if (base_reading_duration.equalsIgnoreCase("72 hour")) {
                return (72 * 60 * 60); // hh*mm*ss
            }

        } catch (Exception e) {
        }
        return (48 * 60 * 60); // hh*mm*ss
    }

    public static int get_avg_filter_sec(int index) {
        try {
            switch (index) {
                case 0:
                    return Constant.FAF_1_MIN;
                case 1:
                    return Constant.FAF_5_MIN;
                case 2:
                    return Constant.FAF_15_MIN;
                case 3:
                    return Constant.FAF_30_MIN;
                case 4:
                    return Constant.FAF_60_MIN;
            }
        } catch (Exception e) {
        }
        return Constant.FAF_1_MIN;
    }

    public static int get_maf_no_of_buckets(int index) {
        try {
            switch (index) {
                case 0:
                    return Constant.S_1_MIN_10;
                case 1:
                    return Constant.S_2_MIN_30;
                case 2:
                    return Constant.S_3_HOUR_1;
                case 3:
                    return Constant.S_4_HOUR_2;
                case 4:
                    return Constant.S_5_HOUR_3;
                case 5:
                    return Constant.S_6_HOUR_4;
                case 6:
                    return Constant.S_7_HOUR_6;
                case 7:
                    return Constant.S_8_HOUR_8;
                case 8:
                    return Constant.S_9_HOUR_12;
                case 9:
                    return Constant.S_10_HOUR_24;
                case 10:
                    return Constant.S_11_HOUR_48;
            }
        } catch (Exception e) {
        }
        return Constant.S_1_MIN_10;
    }

    public static int get_data_in_sec(String value) {
        try {
            if (value.equalsIgnoreCase("1 Second")) {
                return Constant.DATA_REPORTING_INTERVAL_1_SEC;
            } else if (value.equalsIgnoreCase("2 Second")) {
                return Constant.DATA_REPORTING_INTERVAL_2_SEC;
            } else if (value.equalsIgnoreCase("5 Second")) {
                return Constant.DATA_REPORTING_INTERVAL_5_SEC;
            } else if (value.equalsIgnoreCase("10 Second")) {
                return Constant.DATA_REPORTING_INTERVAL_10_SEC;
            } else if (value.equalsIgnoreCase("15 Second")) {
                return Constant.DATA_REPORTING_INTERVAL_15_SEC;
            } else if (value.equalsIgnoreCase("30 Second")) {
                return Constant.DATA_REPORTING_INTERVAL_30_SEC;
            } else if (value.equalsIgnoreCase("1 Minute")) {
                return Constant.DATA_REPORTING_INTERVAL_1_MIN;
            } else if (value.equalsIgnoreCase("5 Minute")) {
                return Constant.DATA_REPORTING_INTERVAL_5_MIN;
            } else if (value.equalsIgnoreCase("10 Minute")) {
                return Constant.DATA_REPORTING_INTERVAL_10_MIN;
            } else if (value.equalsIgnoreCase("15 Minute")) {
                return Constant.DATA_REPORTING_INTERVAL_15_MIN;
            } else if (value.equalsIgnoreCase("30 Minute")) {
                return Constant.DATA_REPORTING_INTERVAL_30_MIN;
            } else if (value.equalsIgnoreCase("1 Hour")) {
                return Constant.DATA_REPORTING_INTERVAL_1_HOUR;
            } else if (value.equalsIgnoreCase("2 Hour")) {
                return Constant.DATA_REPORTING_INTERVAL_2_HOUR;
            } else if (value.equalsIgnoreCase("3 Hour")) {
                return Constant.DATA_REPORTING_INTERVAL_3_HOUR;
            } else if (value.equalsIgnoreCase("6 Hour")) {
                return Constant.DATA_REPORTING_INTERVAL_6_HOUR;
            } else if (value.equalsIgnoreCase("12 Hour")) {
                return Constant.DATA_REPORTING_INTERVAL_12_HOUR;
            } else if (value.equalsIgnoreCase("24 Hour")) {
                return Constant.DATA_REPORTING_INTERVAL_24_HOUR;
            }
        } catch (Exception e) {
        }
        return Constant.S_1_MIN_10;
    }

    public static void auto_save_LDF_data() {
        Variable.auto_save_ldf_time_run = true;
        Variable.auto_save_ldf_time = System.currentTimeMillis() + 3600000;

        try {
            new Thread() {
                @Override
                public void run() {
                    while (Variable.auto_save_ldf_time_run) {
                        if ((System.currentTimeMillis() - Variable.auto_save_ldf_time) >= 3600000) {
                            Tool.save_LDF_data();
                            while (Variable.auto_save_ldf_time <= (System.currentTimeMillis())) {
                                Variable.auto_save_ldf_time = Variable.auto_save_ldf_time + 3600000;
                            }
                        }
                        try {
                            Thread.sleep(500);
                        } catch (Exception e) {
                        }
                    }
                }
            }.start();
        } catch (Exception e) {
        }
    }

    public static void save_LDF_data() {
        try {
            String path = backslashReplace(System.getProperty("user.home"));
            path = path + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.LAST_DATA_FOLDER;

            for (int rover = 0; rover < Constant.MAX_ROVER; rover++) {
                try {
                    if (Variable.index_array[rover] != null && Variable.index_array[rover].length() > 3) {
                        // save faf array

                        FileWriter fw;
                        String fileName = path + "/" + Variable.index_array[rover] + "_FAF.csv";

                        try {
                            File faf_file = new File(fileName);

                            faf_file.createNewFile();
                            fw = new FileWriter(faf_file);
                            PrintWriter pw = new PrintWriter(fw);

                            try {
                                pw.println(Variable.faf_array_index[rover] + ","
                                        + Variable.faf_array_over[rover]);
                            } catch (Exception e) {
                            }

                            for (int faf_array_index = 0; faf_array_index < Constant.MAX_FAF; faf_array_index++) {
                                try {

                                    pw.println(Variable.faf_array[rover][faf_array_index][0] + ","
                                            + Variable.faf_array[rover][faf_array_index][1] + ","
                                            + Variable.faf_array[rover][faf_array_index][2]);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            pw.flush();
                            pw.close();
                            fw.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        // save m af array
                        fileName = path + "/" + Variable.index_array[rover] + "_MAF.csv";

                        try {
                            File faf_file = new File(fileName);

                            faf_file.createNewFile();
                            fw = new FileWriter(faf_file);
                            PrintWriter pw = new PrintWriter(fw);

                            try {
                                pw.println(Variable.maf_array_index[rover] + ","
                                        + Variable.maf_array_over[rover]);
                            } catch (Exception e) {
                            }

                            for (int faf_array_index = 0; faf_array_index < Constant.MAX_MAF; faf_array_index++) {
                                try {
                                    pw.println(Variable.maf_array[rover][faf_array_index][0] + ","
                                            + Variable.maf_array[rover][faf_array_index][1] + ","
                                            + Variable.maf_array[rover][faf_array_index][2]);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            pw.flush();
                            pw.close();
                            fw.close();
                        } catch (Exception e) {
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        } catch (Exception e) {
        }
    }

    private static void read_ldf_data(String path, int rover_index) {
        File file = new File(path + "_FAF.csv");
        String data[];

        if (file.exists()) {
            String tempStr;
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(path + "_FAF.csv"));
                int array_index = 0;

                try {
                    tempStr = br.readLine();
                    data = tempStr.split(",");
                    Variable.faf_array_index[rover_index] = Integer.parseInt(data[0]);
                    Variable.faf_array_over[rover_index] = data[1].trim().equalsIgnoreCase("true");
                } catch (Exception e) {
                }

                while ((tempStr = br.readLine()) != null) {
                    try {
                        data = tempStr.split(",");
                        Variable.faf_array[rover_index][array_index][0] = Float.parseFloat(data[0]);
                        Variable.faf_array[rover_index][array_index][1] = Float.parseFloat(data[1]);
                        Variable.faf_array[rover_index][array_index][2] = Float.parseFloat(data[2]);
                        array_index++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

//                Variable.faf_array_index[rover_index] = array_index - 1;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                } catch (IOException ex) {

                }
            }
        }
        file = new File(path + "_MAF.csv");
        if (file.exists()) {
            String tempStr;
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(path + "_MAF.csv"));
                int array_index = 0;

                try {
                    tempStr = br.readLine();
                    data = tempStr.split(",");
                    Variable.maf_array_index[rover_index] = Integer.parseInt(data[0]);
                    Variable.maf_array_over[rover_index] = data[1].trim().equalsIgnoreCase("true");

                } catch (Exception e) {
                }

                while ((tempStr = br.readLine()) != null) {
                    try {
                        data = tempStr.split(",");
                        Variable.maf_array[rover_index][array_index][0] = Float.parseFloat(data[0]);
                        Variable.maf_array[rover_index][array_index][1] = Float.parseFloat(data[1]);
                        Variable.maf_array[rover_index][array_index][2] = Float.parseFloat(data[2]);
                        array_index++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
//                Variable.maf_array_index[rover_index] = array_index - 1;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                } catch (IOException ex) {

                }
            }
        }

    }

    public static void save_pre_trigger_data(int rover_index, int array_index, float data[][][], String rover_id, String base_id) {

        long time = System.currentTimeMillis();

        try {
            new Thread() {
                @Override
                public void run() {
                    long reverse_time = System.currentTimeMillis();
                    int index = array_index;

                    for (int i = 0; i < Variable.trigger_pre_data_duration_sec; i++) {

                        Tool.save_records(Variable.storage_location + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.STATIC_CSV_FOLDER + "/"
                                + rover_id + "-" + base_id + "_D_" + Tool.convert_ms_into_date(time, "yyyyMMdd_HHmmss") + ".csv",
                                SaveData.getHeader_dynamic(),
                                Tool.convert_ms_into_date(reverse_time, Variable.date_time_formate) + ","
                                + rover_id + ","
                                + base_id + ","
                                + get_formated_data_for_pre_trigger(Variable.faf_array[rover_index][index - 1][0]) + ","
                                + get_formated_data_for_pre_trigger(Variable.faf_array[rover_index][index - 1][1]) + ","
                                + get_formated_data_for_pre_trigger(Variable.faf_array[rover_index][index - 1][2]));

                        index--;

                        if (index < 0) {
                            index = 599;
                        }

                        reverse_time = reverse_time - 1000;
                    }

                }
            }.start();
        } catch (Exception e) {
        }
    }

    private static String get_formated_data_for_pre_trigger(float data) {
        String result;
        if (Variable.reporting_unit.equalsIgnoreCase("mm")) {
            result = Tool.setDecimalDigitsWithoutE("" + data, 1);
        } else if (Variable.reporting_unit.equalsIgnoreCase("cm")) {
            result = Tool.setDecimalDigitsWithoutE("" + data, 2);
        } else if (Variable.reporting_unit.equalsIgnoreCase("inch")) {
            result = Tool.setDecimalDigitsWithoutE("" + data, 2);
        } else {
            result = Tool.setDecimalDigitsWithoutE("" + data, 4);
        }
        return "" + result;
    }

    public static float get_conversion_multiplier(String reporting_unit) {
        float conversion_multiplier = 1;

        switch (reporting_unit) {
            case "feet":
                conversion_multiplier = 0.3048f;
                break;
            case "mm":
                conversion_multiplier = 0.001f;
                break;
            case "cm":
                conversion_multiplier = 0.01f;
                break;
            case "inch":
                conversion_multiplier = 0.0254f;
                break;
            default:
                break;
        }

        return conversion_multiplier;
    }

}
