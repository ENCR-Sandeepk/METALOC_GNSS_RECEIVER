/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialog;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import tool.Call_Dialog;
import tool.Constant;
import tool.Tool;
import static tool.Tool.backslashReplace;
import tool.Variable;

/**
 * FXML Controller class
 *
 * @author sandeepk
 */
public class SelectParameterController implements Initializable {

    Call_Dialog objCall_Dialog = new Call_Dialog();

    @FXML
    Label label_Message;
    @FXML
    private Button btn_cancel;
    @FXML
    private Button btn_ok;
    @FXML
    private CheckBox cb_param_1;
    @FXML
    private CheckBox cb_param_2;
    @FXML
    private CheckBox cb_param_3;
    @FXML
    private CheckBox cb_param_4;
    @FXML
    private CheckBox cb_param_5;
    @FXML
    private CheckBox cb_param_6;
    @FXML
    private CheckBox cb_param_7;
    @FXML
    private CheckBox cb_param_8;
    @FXML
    private CheckBox cb_param_9;
    @FXML
    private CheckBox cb_param_10;
    @FXML
    private CheckBox cb_param_11;
    @FXML
    private CheckBox cb_param_12;
    @FXML
    private CheckBox cb_param_13;
    @FXML
    private CheckBox cb_param_14;
    @FXML
    private CheckBox cb_param_15;
    @FXML
    private Label label_Message1;
    @FXML
    private RadioButton rb_deviation;
    @FXML
    private ToggleGroup reporting_data_type;
    @FXML
    private RadioButton rb_distance;
    @FXML
    private CheckBox cb_reset_base_reading;
    @FXML
    private CheckBox cb_header_in_csv_file;
    @FXML
    private Label label_Message111;
    @FXML
    private ComboBox cb_base_reading;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

//        iv_icon.setImage(new Image("/img/icon.png"));
        try {
            if (Variable.ROVER_ID_1.equalsIgnoreCase("true")) {
                cb_param_1.setSelected(true);
            }
            if (Variable.BASE_ID_2.equalsIgnoreCase("true")) {
                cb_param_2.setSelected(true);
            }
            if (Variable.GNSS_TIME_3.equalsIgnoreCase("true")) {
                cb_param_3.setSelected(true);
            }
            if (Variable.RMS_VALUE_4.equalsIgnoreCase("true")) {
                cb_param_4.setSelected(true);
            }
            if (Variable.LATTITUDE_5.equalsIgnoreCase("true")) {
                cb_param_5.setSelected(true);
            }
            if (Variable.LONGITUDE_6.equalsIgnoreCase("true")) {
                cb_param_6.setSelected(true);
            }
            if (Variable.dEASTING_7.equalsIgnoreCase("true")) {
                cb_param_7.setSelected(true);
            }
            if (Variable.dNORTHING_8.equalsIgnoreCase("true")) {
                cb_param_8.setSelected(true);
            }
            if (Variable.dALTITUDE_9.equalsIgnoreCase("true")) {
                cb_param_9.setSelected(true);
            }
            if (Variable.RATIO_FACTOR_10.equalsIgnoreCase("true")) {
                cb_param_10.setSelected(true);
            }
            if (Variable.UNKNOWN_11.equalsIgnoreCase("true")) {
                cb_param_11.setSelected(true);
            }
            if (Variable.GPS_12.equalsIgnoreCase("true")) {
                cb_param_12.setSelected(true);
            }
            if (Variable.BEIDOU_13.equalsIgnoreCase("true")) {
                cb_param_13.setSelected(true);
            }
            if (Variable.GLONASS_14.equalsIgnoreCase("true")) {
                cb_param_14.setSelected(true);
            }
            if (Variable.GALILEO_15.equalsIgnoreCase("true")) {
                cb_param_15.setSelected(true);
            }

            if (Variable.header_in_csv_file) {
                cb_header_in_csv_file.setSelected(true);
            } else {
                cb_header_in_csv_file.setSelected(false);
            }
        } catch (Exception e) {
        }
        try {
            rb_deviation.setSelected(Variable.is_deviation_data_reporting);
            rb_distance.setSelected(!Variable.is_deviation_data_reporting);
        } catch (Exception e) {
        }

        try {
            cb_base_reading.getItems().add("1 hour");
            cb_base_reading.getItems().add("2 hour");
            cb_base_reading.getItems().add("3 hour");
            cb_base_reading.getItems().add("4 hour");
            cb_base_reading.getItems().add("6 hour");
            cb_base_reading.getItems().add("8 hour");
            cb_base_reading.getItems().add("12 hour");
            cb_base_reading.getItems().add("16 hour");
            cb_base_reading.getItems().add("24 hour");
            cb_base_reading.getItems().add("36 hour");
            cb_base_reading.getItems().add("48 hour");
            cb_base_reading.getItems().add("72 hour");
            cb_base_reading.setValue("" + Variable.base_reading_duration);
        } catch (Exception e) {
        }

    }

    @FXML
    private void action_cancel(ActionEvent event) {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void action_ok(ActionEvent event) {

        try {

            if (validate()) {
                Tool.save_parameter("" + cb_param_1.isSelected(),
                        "" + cb_param_2.isSelected(),
                        "" + cb_param_3.isSelected(),
                        "" + cb_param_4.isSelected(),
                        "" + cb_param_5.isSelected(),
                        "" + cb_param_6.isSelected(),
                        "" + cb_param_7.isSelected(),
                        "" + cb_param_8.isSelected(),
                        "" + cb_param_9.isSelected(),
                        "" + cb_param_10.isSelected(),
                        "" + cb_param_11.isSelected(),
                        "" + cb_param_12.isSelected(),
                        "" + cb_param_13.isSelected(),
                        "" + cb_param_14.isSelected(),
                        "" + cb_param_15.isSelected(),
                        "" + cb_base_reading.getValue().toString());
                Tool.read_parameter();
                try {
                    if (cb_reset_base_reading.isSelected()) {
                        String path = backslashReplace(System.getProperty("user.home"));
                        FileUtils.cleanDirectory(new File(path + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.INITIAL_READING_FOLDER));
                        FileUtils.cleanDirectory(new File(path + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.LAST_DATA_FOLDER));
                    }
                    Tool.save_reporting_data("" + rb_deviation.isSelected(), "" + cb_header_in_csv_file.isSelected());
                    Tool.read_reporting_data();
                } catch (Exception e) {
                }
                Button btn = (Button) event.getSource();
                Stage stage = (Stage) btn.getScene().getWindow();
                stage.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean validate() {
        try {
            if (!Variable.base_reading_duration.equalsIgnoreCase(cb_base_reading.getValue().toString()) && !cb_reset_base_reading.isSelected()) {
                objCall_Dialog.showDialog("Warning: You are about to change the Base Reading! This action will reset the Base Reading. Are you sure you want to proceed?", "", "Yes", "No");
                if (Variable.is_dialog_confirmed) {
                    cb_reset_base_reading.setSelected(true);
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
