/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comm;

/**
 *
 * @author sandeepk
 */
import java.net.*;
import java.io.*;
import javafx.application.Platform;
import main.METALOC_GNSS_RECEIVER_Controller;
import tool.Constant;
import tool.DataServices;
import tool.SaveData;
import tool.Tool;
import tool.Variable;

/**
 * This program demonstrates a simple TCP/IP socket client that reads input from
 * the user and prints echoed message from the server.
 *
 * @author Sandeep
 */
public class Client_1 {

    static Socket socket = null;
    static OutputStream output;
    static PrintWriter writer;
    static InputStream input;
    static BufferedReader reader;
    static boolean first_time = true;
    static int rover_index = 0;
    static String path;

    public static void send_command(String cmd) {
        try {
            if (Constant.DEBUG) {
                System.out.println("CMD 1 : " + cmd);
            }
            Variable.got_reply = false;
            Variable.reply = "";
            writer.println(cmd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void start_server(String host, int port) {
        path = Tool.backslashReplace(System.getProperty("user.home"));
        new Thread() {
            @Override
            public void run() {
                try {
                    first_time = true;
                    socket = new Socket(host, port);
                    output = socket.getOutputStream();
                    writer = new PrintWriter(output, true);
                    input = socket.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(input));
                    String inputLine;
                    while ((inputLine = reader.readLine()) != null) {
                        try {
                            if (inputLine.trim().length() > 3) {
                                Variable.reply = inputLine.trim();
                                if (Constant.DEBUG) {
//                                    System.out.println("RLY 1  = " + Variable.reply);
                                }
                                try {
                                    if (((Variable.reply.startsWith("##") && Variable.reply.endsWith("**")))) {
                                        Variable.reply = Variable.reply.replace(" ", "");
                                        Variable.reply = Variable.reply.replace("#", "");
                                        Variable.reply = Variable.reply.replace("*", "");

                                        String data_for_static_scan[] = Variable.reply.split(",");
                                        String data_for_dynamic_scan[] = Variable.reply.split(",");

                                        if (data_for_static_scan.length == 15) {

                                            if (first_time) {
                                                first_time = false;
                                                Variable.static_measurement_file_time_stamp = System.currentTimeMillis();
                                            }

                                            if ((System.currentTimeMillis() - Variable.static_measurement_file_time_stamp) >= Variable.interval_time_stamp) {
                                                Variable.static_measurement_file_time_stamp = System.currentTimeMillis();
                                                if (Variable.ftp_upload_enableS.equalsIgnoreCase("true")) {
                                                    String files[] = Tool.serachAllFilesInADirectory(Variable.storage_location + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.STATIC_CSV_FOLDER, ".csv");
                                                    Tool.move_files(Variable.storage_location + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.STATIC_CSV_FOLDER,
                                                            Variable.storage_location + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.CSV_TO_UPLOAD_FOLDER, files);
                                                }
                                            }

                                            Variable.static_current_csv_file = data_for_static_scan[Constant.ROVER_ID] + "_" + data_for_static_scan[Constant.BASE_ID] + "_S_" + Tool.convert_ms_into_date(Variable.static_measurement_file_time_stamp, "yyyyMMdd_HHmmss") + ".csv";
                                            rover_index = Tool.get_rover_index(data_for_static_scan[Constant.ROVER_ID] + "_" + data_for_static_scan[Constant.BASE_ID], path + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER, data_for_static_scan);

//                                            System.out.println("rover_index = "+rover_index);
                                            if (rover_index != 100) {

                                                if (Variable.is_deviation_data_reporting) {

                                                    data_for_dynamic_scan[Constant.dEASTING] = "" + (Float.parseFloat(data_for_dynamic_scan[Constant.dEASTING]) - Variable.base_data[rover_index][0]);
                                                    data_for_dynamic_scan[Constant.dNORTHING] = "" + (Float.parseFloat(data_for_dynamic_scan[Constant.dNORTHING]) - Variable.base_data[rover_index][1]);
                                                    data_for_dynamic_scan[Constant.dALTITUDE] = "" + (Float.parseFloat(data_for_dynamic_scan[Constant.dALTITUDE]) - Variable.base_data[rover_index][2]);

                                                }

                                                if (Variable.trigger_scanning_status && (Variable.base_line_count[rover_index] >= Variable.base_line_duration)) {
                                                    if (Math.abs(Float.parseFloat(data_for_dynamic_scan[Constant.dEASTING])) >= Variable.trigger_value_e_meter) {
                                                        Variable.is_event_running[rover_index] = true;
                                                        Variable.trigger_counter[rover_index] = 0;
                                                         
                                                    }
                                                    if (Math.abs(Float.parseFloat(data_for_dynamic_scan[Constant.dNORTHING])) >= Variable.trigger_value_n_meter) {
                                                        Variable.is_event_running[rover_index] = true;
                                                        Variable.trigger_counter[rover_index] = 0;
                                                        
                                                    }
                                                    if (Math.abs(Float.parseFloat(data_for_dynamic_scan[Constant.dALTITUDE])) >= Variable.trigger_value_a_meter) {
                                                        Variable.is_event_running[rover_index] = true;
                                                        Variable.trigger_counter[rover_index] = 0;
                                                        
                                                    }
                                                }

                                                if (Variable.dynamic_scanning_status && (Variable.dynamic_scan_time_in_ms[rover_index] <= System.currentTimeMillis())) {
                                                    Variable.is_dynamic_scan_running[rover_index] = true;
                                                    Variable.dynamic_scan_counter[rover_index] = 0;
                                                    while (Variable.dynamic_scan_time_in_ms[rover_index] <= (System.currentTimeMillis())) {
                                                        Variable.dynamic_scan_time_in_ms[rover_index] = Variable.dynamic_scan_time_in_ms[rover_index] + Variable.dynamic_scan_interval_sec * 1000;
                                                    }
                                                }

                                                if ((Variable.is_event_running[rover_index] || Variable.is_dynamic_scan_running[rover_index]) && (Variable.base_line_count[rover_index] >= Variable.base_line_duration)) {

                                                    if (!Variable.is_pre_trigger_data_save[rover_index] && Variable.is_event_running[rover_index]) {
                                                        Variable.is_pre_trigger_data_save[rover_index] = true;

                                                        
                                                        Tool.save_pre_trigger_data(rover_index, Variable.faf_array_index[rover_index], Variable.faf_array, data_for_static_scan[Constant.ROVER_ID],
                                                                data_for_static_scan[Constant.BASE_ID]);
                                                    }

                                                    
                                                    Variable.dynamic_current_csv_file = data_for_static_scan[Constant.ROVER_ID] + "_" + data_for_static_scan[Constant.BASE_ID] + "_D_" + Tool.convert_ms_into_date(Variable.static_measurement_file_time_stamp, "yyyyMMdd_HHmmss") + ".csv";

                                                     
                                                    if (Variable.file_size_counter[rover_index] >= Variable.max_rec_per_file_dynamic) {

                                                         
                                                        Variable.file_size_counter[rover_index] = 0;
                                                        Variable.dynamic_measurement_file_time_stamp = System.currentTimeMillis();
                                                        Variable.dynamic_current_csv_file = data_for_static_scan[Constant.ROVER_ID] + "_" + data_for_static_scan[Constant.BASE_ID] + "_D_" + Tool.convert_ms_into_date(Variable.dynamic_measurement_file_time_stamp, "yyyyMMdd_HHmmss") + ".csv";
                                                    }

                                                    Variable.file_size_counter[rover_index]++;

                                                    // Change Unit
                                                    try {
                                                        float conversionFactor = DataServices.getConversionFactor(Variable.reporting_unit);
                                                        data_for_dynamic_scan[Constant.dEASTING] = DataServices.convertUnit(data_for_dynamic_scan[Constant.dEASTING], conversionFactor);
                                                        data_for_dynamic_scan[Constant.dNORTHING] = DataServices.convertUnit(data_for_dynamic_scan[Constant.dNORTHING], conversionFactor);
                                                        data_for_dynamic_scan[Constant.dALTITUDE] = DataServices.convertUnit(data_for_dynamic_scan[Constant.dALTITUDE], conversionFactor);
                                                    } catch (Exception e) {
                                                        System.err.println("Error converting units: " + e.getMessage());
                                                    }

                                                    Tool.save_records(Variable.storage_location + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.DYNAMIC_CSV_FOLDER + "/" + Variable.dynamic_current_csv_file,
                                                            SaveData.getHeader_dynamic(),
                                                            Tool.convert_ms_into_date(System.currentTimeMillis(), Variable.date_time_formate) + DataServices.getData_dynamic(data_for_dynamic_scan, rover_index));

                                                    Variable.trigger_counter[rover_index]++;
                                                    if (Variable.trigger_counter[rover_index] >= Variable.post_data_trigger_counter) {
                                                        Variable.is_event_running[rover_index] = false;
                                                        Variable.is_pre_trigger_data_save[rover_index] = false;
                                                    }

                                                    Variable.dynamic_scan_counter[rover_index]++;
                                                    if (Variable.dynamic_scan_counter[rover_index] >= Variable.dynamic_scan_duration_sec) {
                                                        Variable.is_dynamic_scan_running[rover_index] = false;
                                                    }

                                                } else {
                                                    try {
                                                        if (Variable.ftp_upload_enableD.equalsIgnoreCase("true")) {
                                                            String files[] = Tool.serachAllFilesInADirectory(Variable.storage_location + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.DYNAMIC_CSV_FOLDER, ".csv", data_for_static_scan[Constant.ROVER_ID]);
                                                            if (files.length > 0) {
                                                                Tool.move_files(Variable.storage_location + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.DYNAMIC_CSV_FOLDER,
                                                                        Variable.storage_location + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.CSV_TO_UPLOAD_FOLDER, files);
                                                            }
                                                        }
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }
                                                }

                                                DataServices.fill_faf_and_maf_array(data_for_static_scan[Constant.dEASTING], data_for_static_scan[Constant.dNORTHING], data_for_static_scan[Constant.dALTITUDE], rover_index);

                                                float[] result = DataServices.calculate_final_values(rover_index);
                                                if (result != null) {

                                                    if (Variable.is_deviation_data_reporting) {

                                                        if (Variable.base_line_count[rover_index] <= Variable.base_line_duration) {
                                                            Variable.base_data[rover_index][0] = result[0];
                                                            Variable.base_data[rover_index][1] = result[1];
                                                            Variable.base_data[rover_index][2] = result[2];

//                                                            
                                                            Tool.create_base_line_data(path + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.INITIAL_READING_FOLDER + "/" + data_for_static_scan[Constant.ROVER_ID] + "_" + data_for_static_scan[Constant.BASE_ID] + ".txt", rover_index);
                                                        }

                                                        data_for_static_scan[Constant.dEASTING] = "" + (result[0] - Variable.base_data[rover_index][0]);
                                                        data_for_static_scan[Constant.dNORTHING] = "" + (result[1] - Variable.base_data[rover_index][1]);
                                                        data_for_static_scan[Constant.dALTITUDE] = "" + (result[2] - Variable.base_data[rover_index][2]);

                                                    } else {
                                                        data_for_static_scan[Constant.dEASTING] = "" + result[0];
                                                        data_for_static_scan[Constant.dNORTHING] = "" + result[1];
                                                        data_for_static_scan[Constant.dALTITUDE] = "" + result[2];
                                                    }

                                                    // Change Unit
                                                    try {
                                                        float conversionFactor = DataServices.getConversionFactor(Variable.reporting_unit);
                                                        data_for_static_scan[Constant.dEASTING] = DataServices.convertUnit(data_for_static_scan[Constant.dEASTING], conversionFactor);
                                                        data_for_static_scan[Constant.dNORTHING] = DataServices.convertUnit(data_for_static_scan[Constant.dNORTHING], conversionFactor);
                                                        data_for_static_scan[Constant.dALTITUDE] = DataServices.convertUnit(data_for_static_scan[Constant.dALTITUDE], conversionFactor);
                                                    } catch (Exception e) {
                                                        System.err.println("Error converting units: " + e.getMessage());
                                                    }

                                                    if (Variable.static_scan_time_in_ms[rover_index] <= System.currentTimeMillis()) {
                                                        try {

                                                            Variable.graph_data_array[rover_index][Variable.graph_data_array_counter[rover_index]][0] = Float.parseFloat(data_for_static_scan[Constant.dEASTING]);
                                                            Variable.graph_data_array[rover_index][Variable.graph_data_array_counter[rover_index]][1] = Float.parseFloat(data_for_static_scan[Constant.dNORTHING]);

                                                            Variable.graph_data_array_counter[rover_index]++;
                                                            if (Variable.graph_data_array_counter[rover_index] >= Constant.MAX_GRAPH_DATA_POINT) {
                                                                Variable.graph_data_array_counter[rover_index] = 0;
                                                            }
                                                            Variable.graph_data_change[rover_index] = true;
                                                        } catch (Exception e) {
                                                        }

                                                        if (Variable.static_scanning_status) {
                                                            Tool.save_records(Variable.storage_location + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.STATIC_CSV_FOLDER + "/" + Variable.static_current_csv_file,
                                                                    SaveData.getHeader_static(),
                                                                    Tool.convert_ms_into_date(System.currentTimeMillis(), Variable.date_time_formate) + DataServices.getData_static(data_for_static_scan.clone(), rover_index));
                                                        }
                                                        try {
                                                            Platform.runLater(() -> {
                                                                METALOC_GNSS_RECEIVER_Controller.get_Controller().update_table(data_for_static_scan.clone());
                                                            });

                                                        } catch (Exception e) {
                                                            e.printStackTrace();
                                                        }
                                                        while (Variable.static_scan_time_in_ms[rover_index] <= (System.currentTimeMillis())) {
                                                            Variable.static_scan_time_in_ms[rover_index] = Variable.static_scan_time_in_ms[rover_index] + Variable.static_scan_interval_sec * 1000;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Variable.got_reply = true;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    Variable.auto_save_ldf_time_run = false;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public static void close_connection() {
        try {

            socket.close();
        } catch (Exception e) {
        }
    }

}
