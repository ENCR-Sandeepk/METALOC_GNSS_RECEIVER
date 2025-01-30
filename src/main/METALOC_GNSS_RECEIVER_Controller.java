/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import comm.Client_1;
import comm.TCP_IP_Communication;
import graph.FixedScatterGraph;
import graph.RunningScatterGraph;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tool.Call_Dialog;
import tool.Constant;
import tool.FTP_Tool;
import tool.Status;
import tool.Tool;
import tool.Validate_IP_Address;
import tool.Variable;

/**
 *
 * @author sandeepk
 */
public class METALOC_GNSS_RECEIVER_Controller implements Initializable {

    private static METALOC_GNSS_RECEIVER_Controller instance;
    Call_Dialog objCall_Dialog = new Call_Dialog();
    @FXML
    private TextField tf_data_storage_location;
    @FXML
    private TextField tf_ip;
    @FXML
    private Button btn_start_ip_server;
    @FXML
    private TextField tf_port;
    @FXML
    private Button btn_edit_ftp_settings;
    @FXML
    private ComboBox cbb_max_rec_per_file;
    @FXML
    private ComboBox cbb_upload_file_action;
    @FXML
    private ComboBox cbb_date_time_format;
    @FXML
    private Button btn_edit_storage_settings;
    @FXML
    private Button btn_browse_storage_location;
    @FXML
    private Button btn_show_rover_over_map;
    @FXML
    private Button btn_select_parameter;
    @FXML
    private TableColumn<Status, String> date_time;
    @FXML
    private TableColumn<Status, String> rover;
    @FXML
    private TableColumn<Status, String> base;
    @FXML
    private TableColumn<Status, String> gnss_time;
    @FXML
    private TableColumn<Status, String> rms;
    @FXML
    private TableColumn<Status, String> lat;
    @FXML
    private TableColumn<Status, String> lon;
    @FXML
    private TableColumn<Status, String> east;
    @FXML
    private TableColumn<Status, String> north;
    @FXML
    private TableColumn<Status, String> alti;
    @FXML
    private TableColumn<Status, String> ratio;
    @FXML
    private TableColumn<Status, String> un;
    @FXML
    private TableColumn<Status, String> gps;
    @FXML
    private TableColumn<Status, String> beidou;
    @FXML
    private TableColumn<Status, String> glonass;
    @FXML
    private TableColumn<Status, String> galileo;
    @FXML
    private TableView<Status> table_view;
    @FXML
    private CheckBox cb_update_table;
    @FXML
    private Button btn_show_rover_over_graph;
    @FXML
    private Label lbl_ver;
    @FXML
    private Button btn_about;
    @FXML
    private CheckBox cb_auto_start_app;
    @FXML
    private ComboBox cbb_received_data_interval;
    @FXML
    private ComboBox cbb_moving_avg_filter;
    @FXML
    private Button btn_edit_scan_settings;
    @FXML
    private TextField tf_scan_start_time;
    @FXML
    private Button btn_scan_start_time;
    @FXML
    private Label lbl_start_time;
    @FXML
    private Button btn_configure_gnss;

    boolean is_error_ocurred = false;
    boolean is_5_sec = false;
    boolean pressedMouse = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Config Folder path Selection
        Variable.isConfigFileFolderDone = true;

        try {
            lbl_ver.setText(Constant.VERSION);
        } catch (Exception e) {
        }

        try {
            cb_auto_start_app.setVisible(false);
        } catch (Exception e) {
        }

        if (Tool.checkConfigFile()) {
        } else {
            Variable.isConfigFileFolderDone = false;
            objCall_Dialog.showSelectConfigFilePathDialog();
        }
        if (Variable.isConfigFileFolderDone) {
        } else {
            System.exit(0);
        }
        Tool.readConfigFile();
        Tool.create_data_folder();
        if (!Tool.create_data_folder(Variable.storage_location)) {
            Variable.isConfigFileFolderDone = false;
            objCall_Dialog.showSelectConfigFilePathDialog();
        }
        if (Variable.isConfigFileFolderDone) {
        } else {
            System.exit(0);
        }

        Tool.read_ip_port();
        Tool.read_ftp();
        Tool.read_storage();
        Tool.read_parameter();
        Tool.read_reporting_data();
        Tool.read_auto_start_data();
        Tool.read_sampling_setting();

        //    Received Data Interval
        try {
            cbb_received_data_interval.getItems().add("1 Second");
//            cbb_received_data_interval.getItems().add("2 Second");
//            cbb_received_data_interval.getItems().add("5 Second");
//            cbb_received_data_interval.getItems().add("10 Second");
//            cbb_received_data_interval.getItems().add("15 Second");
//            cbb_received_data_interval.getItems().add("30 Second");
//            cbb_received_data_interval.getItems().add("1 Minute");
//            cbb_received_data_interval.getItems().add("5 Minute");
            cbb_received_data_interval.setValue("" + Variable.received_data_interval);

        } catch (Exception e) {
        }

        //    Fixed Avg Filter  
        try {
//            cbb_fixed_avg_filter.getItems().add("1 Minute");
//            cbb_fixed_avg_filter.getItems().add("5 Minute");
//            cbb_fixed_avg_filter.getItems().add("15 Minute");
//            cbb_fixed_avg_filter.getItems().add("30 Minute");
//            cbb_fixed_avg_filter.getItems().add("1 Hour");
//            cbb_fixed_avg_filter.setValue("" + Variable.fixed_avg_filter);
        } catch (Exception e) {
        }

        //      Moving Avg Filter  
        try {
//            cbb_moving_avg_filter.getItems().add("Strategy - 1");
//            cbb_moving_avg_filter.getItems().add("Strategy - 2");
//            cbb_moving_avg_filter.getItems().add("Strategy - 3");
//            cbb_moving_avg_filter.getItems().add("Strategy - 4");
//            cbb_moving_avg_filter.getItems().add("Strategy - 5");

            cbb_moving_avg_filter.getItems().add("Strategy - 1");
            cbb_moving_avg_filter.getItems().add("Strategy - 2");
            cbb_moving_avg_filter.getItems().add("Strategy - 3");
            cbb_moving_avg_filter.getItems().add("Strategy - 4");
            cbb_moving_avg_filter.getItems().add("Strategy - 5");
            cbb_moving_avg_filter.getItems().add("Strategy - 6");
            cbb_moving_avg_filter.getItems().add("Strategy - 7");
            cbb_moving_avg_filter.getItems().add("Strategy - 8");
            cbb_moving_avg_filter.getItems().add("Strategy - 9");
            cbb_moving_avg_filter.getItems().add("Strategy - 10");
            cbb_moving_avg_filter.getItems().add("Strategy - 11");

            cbb_moving_avg_filter.setValue("" + Variable.moving_avg);
        } catch (Exception e) {
        }

        //  Scan Start Time  
        try {
            tf_scan_start_time.setText(Variable.scan_start_time.substring(8));
        } catch (Exception e) {
        }
        //  Max Record Per File
        try {
            cbb_max_rec_per_file.getItems().add("1 Minute");
            cbb_max_rec_per_file.getItems().add("2 Minute");
            cbb_max_rec_per_file.getItems().add("5 Minute");
            cbb_max_rec_per_file.getItems().add("10 Minute");
            cbb_max_rec_per_file.getItems().add("15 Minute");
            cbb_max_rec_per_file.getItems().add("20 Minute");
            cbb_max_rec_per_file.getItems().add("30 Minute");
            cbb_max_rec_per_file.getItems().add("60 Minute");
            cbb_max_rec_per_file.setValue("" + Variable.max_rec_per_file);
        } catch (Exception e) {
        }

        //  Average
//        try {
//
//            cbb_avg_value.getItems().add("1");
//            cbb_avg_value.getItems().add("5");
//            cbb_avg_value.getItems().add("10");
//            cbb_avg_value.getItems().add("25");
//            cbb_avg_value.getItems().add("50");
//            cbb_avg_value.getItems().add("100");
//            cbb_avg_value.setValue("" + Variable.avg_value);
//        } catch (Exception e) {
//        }
        // File Upload Action
        try {
            cbb_upload_file_action.getItems().add("Archive");
            cbb_upload_file_action.getItems().add("Delete");
            cbb_upload_file_action.setValue("" + Variable.file_upload_action);
        } catch (Exception e) {
        }
        //  Date Time Foramt
        try {
            cbb_date_time_format.getItems().add("yyyy/MM/dd HH:mm:ss");
            cbb_date_time_format.getItems().add("yyyy-MM-dd HH:mm:ss");
//            cbb_date_time_format.getItems().add("yyyy/MM/dd HH:mm:ss.SSS");
//            cbb_date_time_format.getItems().add("yyyy-MM-dd HH:mm:ss.SSS");

            cbb_date_time_format.getItems().add("dd/MM/yyyy HH:mm:ss");
            cbb_date_time_format.getItems().add("dd-MM-yyyy HH:mm:ss");
//            cbb_date_time_format.getItems().add("dd/MM/yyyy HH:mm:ss.SSS");
//            cbb_date_time_format.getItems().add("dd-MM-yyyy HH:mm:ss.SSS");

            cbb_date_time_format.getItems().add("yyyy/MMM/dd HH:mm:ss");
            cbb_date_time_format.getItems().add("yyyy-MMM-dd HH:mm:ss");
//            cbb_date_time_format.getItems().add("yyyy/MMM/dd HH:mm:ss.SSS");
//            cbb_date_time_format.getItems().add("yyyy-MMM-dd HH:mm:ss.SSS");

            cbb_date_time_format.getItems().add("dd/MMM/yyyy HH:mm:ss");
            cbb_date_time_format.getItems().add("dd-MMM-yyyy HH:mm:ss");
//            cbb_date_time_format.getItems().add("dd/MMM/yyyy HH:mm:ss.SSS");
//            cbb_date_time_format.getItems().add("dd-MMM-yyyy HH:mm:ss.SSS");

            cbb_date_time_format.setValue("" + Variable.date_time_formate);
        } catch (Exception e) {
        }

        load_theme();
        load_data();

        try {
            if (Variable.ftp_upload_enableS.equalsIgnoreCase("true")) {
                String files[] = Tool.serachAllFilesInADirectory(Variable.storage_location + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.STATIC_CSV_FOLDER, ".csv");
                Tool.move_files(Variable.storage_location + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.STATIC_CSV_FOLDER,
                        Variable.storage_location + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.CSV_TO_UPLOAD_FOLDER, files);
            }

        } catch (Exception e) {
        }
        try {
            if (Variable.ftp_upload_enableD.equalsIgnoreCase("true")) {
                String files[] = Tool.serachAllFilesInADirectory(Variable.storage_location + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.DYNAMIC_CSV_FOLDER, ".csv");
                Tool.move_files(Variable.storage_location + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.DYNAMIC_CSV_FOLDER,
                        Variable.storage_location + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.CSV_TO_UPLOAD_FOLDER, files);
            }
        } catch (Exception e) {
        }

        try {
            FTP_Tool.start_upload();
        } catch (Exception e) {
        }

        try {
            table_view.setPlaceholder(new Label("No rows to display"));

            date_time.setCellValueFactory(new PropertyValueFactory<>("date_time"));
            rover.setCellValueFactory(new PropertyValueFactory<>("rover"));
            base.setCellValueFactory(new PropertyValueFactory<>("base"));
            gnss_time.setCellValueFactory(new PropertyValueFactory<>("gnss_time"));
            rms.setCellValueFactory(new PropertyValueFactory<>("rms"));
            lat.setCellValueFactory(new PropertyValueFactory<>("lat"));
            lon.setCellValueFactory(new PropertyValueFactory<>("lon"));
            east.setCellValueFactory(new PropertyValueFactory<>("east"));
            north.setCellValueFactory(new PropertyValueFactory<>("north"));
            alti.setCellValueFactory(new PropertyValueFactory<>("alti"));
            ratio.setCellValueFactory(new PropertyValueFactory<>("ratio"));
            un.setCellValueFactory(new PropertyValueFactory<>("un"));
            gps.setCellValueFactory(new PropertyValueFactory<>("gps"));
            beidou.setCellValueFactory(new PropertyValueFactory<>("beidou"));
            glonass.setCellValueFactory(new PropertyValueFactory<>("glonass"));
            galileo.setCellValueFactory(new PropertyValueFactory<>("galileo"));

            date_time.prefWidthProperty().bind(table_view.widthProperty().multiply(0.12));
            rover.prefWidthProperty().bind(table_view.widthProperty().multiply(0.08));
            base.prefWidthProperty().bind(table_view.widthProperty().multiply(0.08));
            gnss_time.prefWidthProperty().bind(table_view.widthProperty().multiply(0.055));
            rms.prefWidthProperty().bind(table_view.widthProperty().multiply(0.04));
            lat.prefWidthProperty().bind(table_view.widthProperty().multiply(0.09));
            lon.prefWidthProperty().bind(table_view.widthProperty().multiply(0.09));
            east.prefWidthProperty().bind(table_view.widthProperty().multiply(0.09));
            north.prefWidthProperty().bind(table_view.widthProperty().multiply(0.09));
            alti.prefWidthProperty().bind(table_view.widthProperty().multiply(0.09));
            ratio.prefWidthProperty().bind(table_view.widthProperty().multiply(0.04));
            un.prefWidthProperty().bind(table_view.widthProperty().multiply(0.03));
            gps.prefWidthProperty().bind(table_view.widthProperty().multiply(0.03));
            beidou.prefWidthProperty().bind(table_view.widthProperty().multiply(0.05));
            glonass.prefWidthProperty().bind(table_view.widthProperty().multiply(0.06));
            galileo.prefWidthProperty().bind(table_view.widthProperty().multiply(0.08));

//            logger_tag.setResizable(false);
//            logger_sn.setResizable(false);
//            logger_status.setResizable(false);
        } catch (Exception e) {
        }

        btn_about.setVisible(false);

        // Auto Start Code
//        if (Variable.is_auto_start && !Variable.is_app_close_by_user && Variable.is_app_running) {
//            try {
//
//                /////////////////////////////////////////////////////
//                Variable.fixed_avg_filter_sec = Tool.get_avg_filter_sec(cbb_fixed_avg_filter.getSelectionModel().getSelectedIndex());
//                Variable.received_data_interval_sec = Tool.get_received_data_interval(cbb_received_data_interval.getSelectionModel().getSelectedIndex());
//                Variable.maf_bucket_size = Tool.get_maf_no_of_buckets(cbb_moving_avg_filter.getSelectionModel().getSelectedIndex());
//                Variable.static_scan_interval_sec = Tool.get_data_in_sec(cbb_reporting_data_interval.getSelectionModel().getSelectedIndex());
//
//                Variable.faf_filter_size = Variable.fixed_avg_filter_sec / Variable.received_data_interval_sec;
//
//                Variable.graph_data_array = new float[Constant.MAX_ROVER][Constant.MAX_FAF][Constant.MAX_PARAMETER - 1];
//                Variable.graph_data_array_counter = new int[Constant.MAX_ROVER];
//                Variable.graph_data_change = new boolean[Constant.MAX_ROVER];
//                Variable.faf_array = new float[Constant.MAX_ROVER][Constant.MAX_FAF][Constant.MAX_PARAMETER];
//                Variable.faf_array_index = new int[Constant.MAX_ROVER];
//                Variable.faf_array_over = new boolean[Constant.MAX_ROVER];
//                Variable.data_reporting_index = new int[Constant.MAX_ROVER];
//                Variable.base_line_count = new int[Constant.MAX_ROVER];
//                Variable.base_line_readed = new boolean[Constant.MAX_ROVER];
//                Variable.maf_array = new float[Constant.MAX_ROVER][Constant.MAX_MAF][Constant.MAX_PARAMETER];
//                Variable.maf_array_index = new int[Constant.MAX_ROVER];
//                Variable.maf_array_over = new boolean[Constant.MAX_ROVER];
//                Variable.index_array = new String[Constant.MAX_ROVER];
//                Variable.base_data = new float[Constant.MAX_ROVER][Constant.MAX_PARAMETER];
//                Variable.is_first_data = new boolean[Constant.MAX_ROVER];
//Variable.first_cycle_count = new int[Constant.MAX_ROVER];
//
//                ////////////////////////////////////////////////////////
//                btn_start_ip_server.setText("Stop IP Server");
//                tf_ip.setDisable(true);
//                tf_port.setDisable(true);
//
//                try {
//                    Thread.sleep(30000);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                Client_1.start_server(tf_ip.getText().trim(), Integer.parseInt(tf_port.getText().trim()));
//                Tool.save_ip_port(tf_ip.getText().trim(), tf_port.getText().trim());
//
//                Tool.save_auto_start_data(true, cb_auto_start_app.isSelected(), false);
//                Tool.read_auto_start_data();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }

    public METALOC_GNSS_RECEIVER_Controller() {
        instance = this;
    }

    public static METALOC_GNSS_RECEIVER_Controller get_Controller() {
        return instance;
    }

    public void update_table(String data[]) {
        try {

            if (cb_update_table.isSelected()) {
                ObservableList<Status> table_data = table_view.getItems();

                boolean data_found = false;

                if (Variable.reporting_unit.equalsIgnoreCase("mm")) {
                    data[Constant.dEASTING] = (Tool.setDecimalDigitsWithoutE(data[Constant.dEASTING], 1) + " " + Variable.reporting_unit);
                    data[Constant.dNORTHING] = (Tool.setDecimalDigitsWithoutE(data[Constant.dNORTHING], 1) + " " + Variable.reporting_unit);
                    data[Constant.dALTITUDE] = (Tool.setDecimalDigitsWithoutE(data[Constant.dALTITUDE], 1) + " " + Variable.reporting_unit);
                } else if (Variable.reporting_unit.equalsIgnoreCase("cm") || Variable.reporting_unit.equalsIgnoreCase("inch")) {
                    data[Constant.dEASTING] = (Tool.setDecimalDigitsWithoutE(data[Constant.dEASTING], 2) + " " + Variable.reporting_unit);
                    data[Constant.dNORTHING] = (Tool.setDecimalDigitsWithoutE(data[Constant.dNORTHING], 2) + " " + Variable.reporting_unit);
                    data[Constant.dALTITUDE] = (Tool.setDecimalDigitsWithoutE(data[Constant.dALTITUDE], 2) + " " + Variable.reporting_unit);
                } else {
                    data[Constant.dEASTING] = (Tool.setDecimalDigitsWithoutE(data[Constant.dEASTING], 4) + " " + Variable.reporting_unit);
                    data[Constant.dNORTHING] = (Tool.setDecimalDigitsWithoutE(data[Constant.dNORTHING], 4) + " " + Variable.reporting_unit);
                    data[Constant.dALTITUDE] = (Tool.setDecimalDigitsWithoutE(data[Constant.dALTITUDE], 4) + " " + Variable.reporting_unit);
                }

                for (Status status : table_data) {
                    if (status.getBase().equalsIgnoreCase(data[Constant.BASE_ID]) && status.getRover().equalsIgnoreCase(data[Constant.ROVER_ID])) {
                        data_found = true;
//                        System.out.println(data[Constant.ROVER_ID]);
                        status.setDate_time(Tool.getCurrentTime(Variable.date_time_formate));

                        status.setGnss_time(data[Constant.GNSS_TIME]);
                        status.setRms(data[Constant.RMS_VALUE]);
                        status.setLat(data[Constant.LATTITUDE]);
                        status.setLon(data[Constant.LONGITUDE]);
                        status.setEast(data[Constant.dEASTING]);
                        status.setNorth(data[Constant.dNORTHING]);
                        status.setAlti(data[Constant.dALTITUDE]);
                        status.setRatio(data[Constant.RATIO_FACTOR]);
                        status.setUn(data[Constant.UNKNOWN]);
                        status.setGps(data[Constant.GPS]);
                        status.setBeidou(data[Constant.BEIDOU]);
                        status.setGlonass(data[Constant.GLONASS]);
                        status.setGalileo(data[Constant.GALILEO]);
                        table_view.setItems(table_data);
                        table_view.refresh();
                        break;
                    }
                }

                if (!data_found) {
                    table_view.getItems().add(new Status(
                            Tool.getCurrentTime(Variable.date_time_formate),
                            data[Constant.ROVER_ID],
                            data[Constant.BASE_ID],
                            data[Constant.GNSS_TIME],
                            data[Constant.RMS_VALUE],
                            data[Constant.LATTITUDE],
                            data[Constant.LONGITUDE],
                            data[Constant.dEASTING],
                            data[Constant.dNORTHING],
                            data[Constant.dALTITUDE],
                            data[Constant.RATIO_FACTOR],
                            data[Constant.UNKNOWN],
                            data[Constant.GPS],
                            data[Constant.BEIDOU],
                            data[Constant.GLONASS],
                            data[Constant.GALILEO]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void action_close_application(ActionEvent event) {
        try {

            objCall_Dialog.showDialog("Do you really want to close the application ?", "", "Yes", "No");
            if (Variable.is_dialog_confirmed) {
                if (btn_start_ip_server.getText().equals("Start IP Server")) {
                    if (Variable.is_dialog_confirmed) {
                        Tool.save_auto_start_data(false, cb_auto_start_app.isSelected(), true);
                        Platform.exit();
                        System.exit(0);
                    }
                } else {
                    objCall_Dialog.showDialog("Please first Stop IP Server !!", "", "OK", "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void action_minimise_application(ActionEvent event) {
        try {
            ((Stage) ((Button) event.getSource()).getScene().getWindow()).setIconified(true);
        } catch (Exception e) {
        }
    }

    @FXML
    private void action_about_us(ActionEvent event) {
        try {
            new Call_Dialog().showAboutUs();
        } catch (Exception e) {
        }
    }

    private void action_change_theme(ActionEvent event) {
        try {
            new Call_Dialog().showChangeTheme();
        } catch (Exception e) {
        }
    }

    @FXML
    private void action_browse_storage_location(ActionEvent event) {
        try {
            objCall_Dialog.showSelectConfigFilePathDialog();
            tf_data_storage_location.setText(Variable.storage_location);
        } catch (Exception e) {
        }
    }

    @FXML
    private void action_btn_start_ip_server(ActionEvent event) {
        try {
            if (btn_edit_scan_settings.getText().equalsIgnoreCase("Edit Setting")) {
                if (btn_edit_storage_settings.getText().equalsIgnoreCase("Edit Setting")) {
                    if (btn_start_ip_server.getText().equals("Start IP Server")) {
                        if (validate_ip_port()) {

                            Tool.auto_save_LDF_data();

                            /////////////////////////////////////////////////////
//                    Variable.fixed_avg_filter_sec = Tool.get_avg_filter_sec(cbb_fixed_avg_filter.getSelectionModel().getSelectedIndex());
                            Variable.received_data_interval_sec = Tool.get_received_data_interval(cbb_received_data_interval.getSelectionModel().getSelectedIndex());
                            Variable.maf_bucket_size = Tool.get_maf_no_of_buckets(cbb_moving_avg_filter.getSelectionModel().getSelectedIndex());

                            Variable.post_data_trigger_counter = Variable.trigger_post_data_duration_sec;
                            Variable.pre_data_trigger_counter = Variable.trigger_pre_data_duration_sec;

                            if (Variable.dynamic_scan_duration_sec > Variable.trigger_post_data_duration_sec) {
                                Variable.max_rec_per_file_dynamic = Variable.trigger_post_data_duration_sec;
                            } else {
                                Variable.max_rec_per_file_dynamic = Variable.dynamic_scan_duration_sec;
                            }

                            Variable.static_scan_interval_sec = Tool.get_data_in_sec(Variable.static_scan_interval);

                            Variable.base_line_duration = Tool.get_base_line_duration(Variable.base_reading_duration);

                            Variable.faf_filter_size = Variable.fixed_avg_filter_sec / Variable.received_data_interval_sec;

                            Variable.graph_data_array = new float[Constant.MAX_ROVER][Constant.MAX_GRAPH_DATA_POINT][Constant.MAX_PARAMETER - 1];
                            Variable.graph_data_array_counter = new int[Constant.MAX_ROVER];
                            Variable.graph_data_change = new boolean[Constant.MAX_ROVER];
                            Variable.faf_array = new float[Constant.MAX_ROVER][Constant.MAX_FAF][Constant.MAX_PARAMETER];
                            Variable.faf_array_index = new int[Constant.MAX_ROVER];
                            Variable.faf_array_over = new boolean[Constant.MAX_ROVER];
                            Variable.base_line_count = new int[Constant.MAX_ROVER];
                            Variable.base_line_readed = new boolean[Constant.MAX_ROVER];
                            Variable.maf_array = new float[Constant.MAX_ROVER][Constant.MAX_MAF][Constant.MAX_PARAMETER];
                            Variable.maf_array_index = new int[Constant.MAX_ROVER];
                            Variable.maf_array_over = new boolean[Constant.MAX_ROVER];
                            Variable.index_array = new String[Constant.MAX_ROVER];
                            Variable.base_data = new float[Constant.MAX_ROVER][Constant.MAX_PARAMETER];
                            Variable.is_first_data = new boolean[Constant.MAX_ROVER];
                            Variable.is_pre_trigger_data_save = new boolean[Constant.MAX_ROVER];
                            Variable.is_event_running = new boolean[Constant.MAX_ROVER];
                            Variable.is_dynamic_scan_running = new boolean[Constant.MAX_ROVER];
                            Variable.dynamic_scan_counter = new int[Constant.MAX_ROVER];
                            Variable.trigger_counter = new int[Constant.MAX_ROVER];

                            btn_start_ip_server.setText("Stop IP Server");
                            tf_ip.setDisable(true);
                            tf_port.setDisable(true);
                            btn_edit_scan_settings.setDisable(true);
                            btn_edit_storage_settings.setDisable(true);
                            btn_configure_gnss.setDisable(true);

                            Client_1.start_server(tf_ip.getText().trim(), Integer.parseInt(tf_port.getText().trim()));
                            Tool.save_ip_port(tf_ip.getText().trim(), tf_port.getText().trim());

                            Tool.save_auto_start_data(true, cb_auto_start_app.isSelected(), false);
                            Tool.read_auto_start_data();

                            // Scan time
                            try {
                                long scan_time = Tool.convert_date_time_into_ms(Variable.scan_start_time, "yyyyMMddHH:mm");
                                Variable.static_scan_time_in_ms[0] = scan_time;
                                Variable.dynamic_scan_time_in_ms[0] = scan_time;

                                while (Variable.static_scan_time_in_ms[0] <= (System.currentTimeMillis())) {
                                    Variable.static_scan_time_in_ms[0] = Variable.static_scan_time_in_ms[0] + 86400000;
                                    Variable.dynamic_scan_time_in_ms[0] = Variable.dynamic_scan_time_in_ms[0] + 86400000;
                                }
                                for (int i = 1; i < Constant.MAX_ROVER; i++) {
                                    Variable.static_scan_time_in_ms[i] = Variable.static_scan_time_in_ms[0];
                                    Variable.dynamic_scan_time_in_ms[i] = Variable.dynamic_scan_time_in_ms[0];
                                }
                                lbl_start_time.setText("System Started Since : " + Tool.get_date_time_stamp("dd-MM-yyyy HH:mm"));
                            } catch (Exception e) {
                            }

                        }
                    } else {
                        Variable.auto_save_ldf_time_run = false;
                        Tool.save_auto_start_data(false, cb_auto_start_app.isSelected(), false);
                        Tool.read_auto_start_data();
                        tf_ip.setDisable(false);
                        tf_port.setDisable(false);
                        btn_edit_scan_settings.setDisable(false);
                        btn_edit_storage_settings.setDisable(false);
                        btn_configure_gnss.setDisable(false);
                        btn_start_ip_server.setText("Start IP Server");
                        lbl_start_time.setText("");
                        Tool.save_LDF_data();
                        Client_1.close_connection();
                    }
                } else {
                    objCall_Dialog.showDialog("Please save setting first !!", "", "OK", "");
                }
            } else {
                objCall_Dialog.showDialog("Please save setting first !!", "", "OK", "");
            }
        } catch (Exception e) {
        }
    }

    @FXML
    private void action_btn_edit_ftp_settings(ActionEvent event) {
        try {

//            if (btn_edit_ftp_settings.getText().equals("Edit Setting")) {
//                btn_edit_ftp_settings.setText("Save Setting");
//
//                enable_disable_ftp(false);
//
//            } else {
//                if (validate_ftp()) {
//
//                    btn_edit_ftp_settings.setText("Edit Setting");
//                    enable_disable_ftp(true);
//
//                    Tool.save_ftp(tf_ftp_ip.getText().trim(), tf_ftp_port.getText().trim(), tf_ftp_user.getText().trim(), pf_ftp_pass.getText().trim(), "" + cb_enable_ftp_upload.isSelected());
//                    Tool.read_ftp();
//                }
//            }
            objCall_Dialog.showFTP_Settings();
        } catch (Exception e) {
        }

    }

    public void load_theme() {
//        ap_content.setStyle("-fx-background-color: #" + Variable.color_back);
//        ap_main.setStyle("-fx-background-color: #" + Variable.color_back);
//        ap_menu.setStyle("-fx-background-color: #" + Variable.color_back);
    }

    public void load_data() {
        try {
            tf_ip.setText(Variable.ip);
            tf_port.setText(Variable.port);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            tf_data_storage_location.setText(Variable.storage_location);

            cbb_max_rec_per_file.setValue("" + Variable.max_rec_per_file);
            cbb_upload_file_action.setValue("" + Variable.file_upload_action);
            cbb_date_time_format.setValue("" + Variable.date_time_formate);

            enable_disable_storage(true);
            enable_disable_sampling_setting(true);
        } catch (Exception e) {
        }

    }

    @FXML
    private void action_btn_edit_scan_settings(ActionEvent event) {
        try {
            if (btn_edit_scan_settings.getText().equals("Edit Setting")) {
                btn_edit_scan_settings.setText("Save Setting");
                enable_disable_sampling_setting(false);
            } else {
                btn_edit_scan_settings.setText("Edit Setting");
                enable_disable_sampling_setting(true);

                Tool.save_sampling_setting(cbb_received_data_interval.getValue().toString(),
                        cbb_moving_avg_filter.getValue().toString(),
                        Variable.static_scan_interval,
                        Tool.get_date_time_stamp("yyyyMMdd") + tf_scan_start_time.getText(),
                        Variable.static_scanning_status, Variable.dynamic_scanning_status, Variable.dynamic_scan_interval,
                        Variable.dynamic_scan_duration, Variable.trigger_scanning_status, Variable.trigger_pre_data_duration,
                        Variable.trigger_post_data_duration, "" + Variable.trigger_value_n, "" + Variable.trigger_value_e, "" + Variable.trigger_value_a, Variable.reporting_unit);

                Tool.read_sampling_setting();
            }
        } catch (Exception e) {
        }
    }

    @FXML
    private void action_btn_edit_storage_settings(ActionEvent event) {
        try {

            if (btn_edit_storage_settings.getText().equals("Edit Setting")) {
                btn_edit_storage_settings.setText("Save Setting");
                enable_disable_storage(false);
            } else {
                btn_edit_storage_settings.setText("Edit Setting");
                enable_disable_storage(true);

                Tool.save_storage(Variable.store_capture_data,
                        cbb_max_rec_per_file.getValue().toString(),
                        cbb_upload_file_action.getValue().toString(),
                        cbb_date_time_format.getValue().toString());
                Tool.read_storage();
            }
        } catch (Exception e) {
        }
    }

    private boolean validate_ip_port() {
        try {
            if (tf_ip.getText() == null || tf_ip.getText().trim().isEmpty()) {
                objCall_Dialog.showDialog("IP Address can't be empty !", "", "OK", "");
                return false;
            } else if (Validate_IP_Address.isValid(tf_ip.getText().trim()) == 0) {
                objCall_Dialog.showDialog("Please enter a valid IP Address !", "", "OK", "");
                return false;
            }
            if (tf_port.getText() == null || tf_port.getText().trim().isEmpty()) {
                objCall_Dialog.showDialog("Port Number can't be empty !", "", "OK", "");
                return false;
            } else {
                try {
                    Integer.parseInt(tf_port.getText());
                } catch (Exception e) {
                    objCall_Dialog.showDialog("Please enter a valid Port Number !", "", "OK", "");
                    return false;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private void enable_disable_storage(boolean disable) {
        try {

            btn_browse_storage_location.setDisable(disable);
            tf_data_storage_location.setDisable(disable);
            cbb_max_rec_per_file.setDisable(disable);
            cbb_upload_file_action.setDisable(disable);
            cbb_date_time_format.setDisable(disable);
            btn_select_parameter.setDisable(disable);
            btn_edit_ftp_settings.setDisable(disable);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void enable_disable_sampling_setting(boolean disable) {
        try {
            cbb_received_data_interval.setDisable(disable);
            cbb_moving_avg_filter.setDisable(disable);
            tf_scan_start_time.setDisable(disable);
            btn_scan_start_time.setDisable(disable);
            btn_configure_gnss.setDisable(disable);
        } catch (Exception e) {
        }
    }

    @FXML
    private void action_btn_show_rover_over_map(ActionEvent event) {
        try {
            ObservableList<Status> table_data = table_view.getItems();
            Variable.google_map_information = "";
            table_data.forEach((status) -> {
                Variable.google_map_information = Variable.google_map_information + ","
                        + status.getRover()
                        + "," + Tool.convert_DMS_to_Degree(status.getLat().replace("N", ""))
                        + "," + Tool.convert_DMS_to_Degree(status.getLon().replace("E", ""));
            });
            System.out.println(Variable.google_map_information);
            if (Variable.google_map_information.length() > 10) {
                objCall_Dialog.show_google_map();
            } else {
                new Call_Dialog().showDialog("No Rover Found", "", "OK", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void action_btn_select_parameter(ActionEvent event) {
        try {
            objCall_Dialog.showSelectParameter();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void action_btn_show_rover_over_graph(ActionEvent event) {
        try {

            if (table_view.getSelectionModel().getSelectedItem() == null) {
                new Call_Dialog().showDialog("Please select Rover", "", "OK", "");
            } else {
                Status status = table_view.getSelectionModel().getSelectedItem();
                String rover = status.getRover() + "_" + status.getBase();
                int rover_index = Tool.get_rover_index(rover);

                objCall_Dialog.showGraphOption();
                if (Variable.is_dialog_confirmed) {
                    if (Variable.fixed_data_point_graph) {

                        new FixedScatterGraph(rover_index, Variable.scatter_with_smooth_line_graph, rover).setVisible(true);
                    } else {

                        new RunningScatterGraph(Variable.scatter_with_smooth_line_graph, rover_index, rover).setVisible(true);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void action_scan_start_time(ActionEvent event) {
        try {
            Variable.set_time_picker_hh_mm = tf_scan_start_time.getText().trim();
            String scan_start_time = objCall_Dialog.showTimePicker_hh_mm();
            if (scan_start_time.trim().length() > 3) {
                tf_scan_start_time.setText(scan_start_time);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void encardio_icon_released(MouseEvent event) {
        pressedMouse = false;
    }

    @FXML
    private void encardio_icon_pressed(MouseEvent event) {

        if (btn_start_ip_server.getText().equals("Start IP Server")) {

            is_5_sec = false;
            pressedMouse = true;
            new Thread() {
                @Override
                public void run() {

                    long curmillies = System.currentTimeMillis();
                    while (pressedMouse) {
                        if ((System.currentTimeMillis() - curmillies) >= 5000) {
                            is_5_sec = true;

                            break;
                        }
                        try {
                            Thread.sleep(50);
                        } catch (Exception e) {
                        }
                    }
                    pressedMouse = false;
                    try {
                        Thread.sleep(50);
                    } catch (Exception e) {
                    }
                }
            }.start();
            while (pressedMouse) {
                try {
                    Thread.sleep(50);
                } catch (Exception e) {
                }
            }
            if (is_5_sec) {
                configure_gnss();
            }
        }

    }

    private void configure_gnss() {
        try {

            objCall_Dialog.showDialog("Do you really want to configure GNSS ?", "", "Yes", "No");

            if (Variable.is_dialog_confirmed) {
                if (validate_ip_port()) {

                    Variable.progress = 0;
                    Variable.progressBarRunning = true;

                    is_error_ocurred = false;

                    new Thread() {
                        @Override
                        public void run() {

                            Variable.progress = 5;
                            try {
                                TCP_IP_Communication.start_server(tf_ip.getText().trim(), Integer.parseInt(tf_port.getText().trim()));
                                Thread.sleep(200);
                                Variable.progress = 10;

                                TCP_IP_Communication.send_command("interfacemode com1 compass compass on\r\n");
                                TCP_IP_Communication.waitforReply();

                                if (Variable.got_reply_1 && Variable.reply_1.trim().equals("Command accepted! Port: COM1.")) {
                                    Thread.sleep(200);
                                    Variable.progress = 16;

                                    TCP_IP_Communication.send_command("unlogall\r\n");
                                    TCP_IP_Communication.waitforReply();

                                    if (Variable.got_reply_1 && Variable.reply_1.trim().equals("Command accepted! Port: COM1.")) {
                                        Thread.sleep(200);
                                        Variable.progress = 22;

                                        TCP_IP_Communication.send_command("log com1 rtcm1005b ontime 1\r\n");
                                        TCP_IP_Communication.waitforReply();

                                        if (Variable.got_reply_1 && Variable.reply_1.trim().equals("Command accepted! Port: COM1.")) {
                                            Thread.sleep(200);
                                            Variable.progress = 28;

                                            TCP_IP_Communication.send_command("log com1 rtcm1007b ontime 1\r\n");
                                            TCP_IP_Communication.waitforReply();

                                            if (Variable.got_reply_1 && Variable.reply_1.trim().equals("Command accepted! Port: COM1.")) {
                                                Thread.sleep(200);
                                                Variable.progress = 34;

                                                TCP_IP_Communication.send_command("log com1 rtcm1033b ontime 1\r\n");
                                                TCP_IP_Communication.waitforReply();

                                                if (Variable.got_reply_1 && Variable.reply_1.trim().equals("Command accepted! Port: COM1.")) {
                                                    Thread.sleep(200);
                                                    Variable.progress = 40;

                                                    TCP_IP_Communication.send_command("log com1 rtcm1074b ontime 1\r\n");
                                                    TCP_IP_Communication.waitforReply();

                                                    if (Variable.got_reply_1 && Variable.reply_1.trim().equals("Command accepted! Port: COM1.")) {
                                                        Thread.sleep(200);
                                                        Variable.progress = 46;

                                                        TCP_IP_Communication.send_command("log com1 rtcm1084b ontime 1\r\n");
                                                        TCP_IP_Communication.waitforReply();

                                                        if (Variable.got_reply_1 && Variable.reply_1.trim().equals("Command accepted! Port: COM1.")) {
                                                            Thread.sleep(200);
                                                            Variable.progress = 52;

                                                            TCP_IP_Communication.send_command("log com1 rtcm1124b ontime 1\r\n");
                                                            TCP_IP_Communication.waitforReply();

                                                            if (Variable.got_reply_1 && Variable.reply_1.trim().equals("Command accepted! Port: COM1.")) {
                                                                Thread.sleep(200);
                                                                Variable.progress = 58;

                                                                TCP_IP_Communication.send_command("log com1 rtcm1042b ontime 1\r\n");
                                                                TCP_IP_Communication.waitforReply();

                                                                if (Variable.got_reply_1 && Variable.reply_1.trim().equals("Command accepted! Port: COM1.")) {
                                                                    Thread.sleep(200);
                                                                    Variable.progress = 64;

                                                                    TCP_IP_Communication.send_command("log com1 rtcm1019b ontime 1\r\n");
                                                                    TCP_IP_Communication.waitforReply();

                                                                    if (Variable.got_reply_1 && Variable.reply_1.trim().equals("Command accepted! Port: COM1.")) {
                                                                        Thread.sleep(200);
                                                                        Variable.progress = 70;

                                                                        TCP_IP_Communication.send_command("log com1 rtcm1020b ontime 1\r\n");
                                                                        TCP_IP_Communication.waitforReply();

                                                                        if (Variable.got_reply_1 && Variable.reply_1.trim().equals("Command accepted! Port: COM1.")) {
                                                                            Thread.sleep(200);
                                                                            Variable.progress = 76;

                                                                            TCP_IP_Communication.send_command("log com1 rtcm1045b ontime 1\r\n");
                                                                            TCP_IP_Communication.waitforReply();

                                                                            if (Variable.got_reply_1 && Variable.reply_1.trim().equals("Command accepted! Port: COM1.")) {
                                                                                Thread.sleep(200);
                                                                                Variable.progress = 82;

                                                                                TCP_IP_Communication.send_command("log com1 rtcm1094b ontime 1\r\n");
                                                                                TCP_IP_Communication.waitforReply();

                                                                                if (Variable.got_reply_1 && Variable.reply_1.trim().equals("Command accepted! Port: COM1.")) {
                                                                                    Thread.sleep(200);
                                                                                    Variable.progress = 88;

                                                                                    TCP_IP_Communication.send_command("saveconfig\r\n\r\n\r\n");
                                                                                    TCP_IP_Communication.waitforReply();

                                                                                    if (Variable.got_reply_1 && Variable.reply_1.trim().equals("Command accepted! Port: COM1.")) {
                                                                                        Variable.progress = 94;
                                                                                        Thread.sleep(200);
                                                                                    } else {
                                                                                        is_error_ocurred = true;
                                                                                    }
                                                                                } else {
                                                                                    is_error_ocurred = true;
                                                                                }
                                                                            } else {
                                                                                is_error_ocurred = true;
                                                                            }
                                                                        } else {
                                                                            is_error_ocurred = true;
                                                                        }
                                                                    } else {
                                                                        is_error_ocurred = true;
                                                                    }
                                                                } else {
                                                                    is_error_ocurred = true;
                                                                }
                                                            } else {
                                                                is_error_ocurred = true;
                                                            }
                                                        } else {
                                                            is_error_ocurred = true;
                                                        }
                                                    } else {
                                                        is_error_ocurred = true;
                                                    }
                                                } else {
                                                    is_error_ocurred = true;
                                                }
                                            } else {
                                                is_error_ocurred = true;
                                            }
                                        } else {
                                            is_error_ocurred = true;
                                        }
                                    } else {
                                        is_error_ocurred = true;
                                    }
                                } else {
                                    is_error_ocurred = true;
                                }

                            } catch (Exception e) {
                            } finally {
                                Variable.progressBarRunning = false;
                                TCP_IP_Communication.close_connection();
                                try {
                                    Thread.sleep(70);
                                } catch (Exception e) {
                                }
                            }

                        }
                    }.start();
                    new Call_Dialog().show_progress_bar("Please wait .... Configuring GNSS !!");

                    if (is_error_ocurred) {
                        objCall_Dialog.showDialog("Unable to configure !! Unknown error ocurred.", "", "OK", "");
                    } else {
                        objCall_Dialog.showDialog("Wow !! Your GNSS is configured.", "", "OK", "");
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    @FXML
    private void action_btn_configure_scan(ActionEvent event) {
        new Call_Dialog().showConfigureScan();
    }

}
