/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialog;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tool.Call_Dialog;
import tool.Tool;
import tool.Variable;

/**
 * FXML Controller class
 *
 * @author Sandeep K
 */
public class Configure_scanController implements Initializable {

    @FXML
    private Label label_Message;
    @FXML
    private Label label_Message11;
    @FXML
    private CheckBox cb_static_scanning;
    @FXML
    private ComboBox cbb_static_scanning_interval;
    @FXML
    private Label label_Message2;
    @FXML
    private CheckBox cb_dynamic_scanning;
    @FXML
    private Label label_Message112;
    @FXML
    private ComboBox cbb_dynamic_scanning_interval;
    @FXML
    private Label label_Message1122;
    @FXML
    private ComboBox cbb_dynamic_scanning_duration;
    @FXML
    private Label label_Message21;
    @FXML
    private CheckBox cb_triggered_scanning;
    @FXML
    private Label label_Message1121;
    @FXML
    private ComboBox cbb_pre_data_triggered_scanning;
    @FXML
    private Label label_Message11221;
    @FXML
    private ComboBox cbb_post_data_triggered_scanning;
    @FXML
    private Label label_Message11211;
    @FXML
    private Label label_Message112211;
    @FXML
    private Label label_Message1122111;
    @FXML
    private TextField tf_trigger_value_n;
    @FXML
    private TextField tf_trigger_value_e;
    @FXML
    private TextField tf_trigger_value_a;
    @FXML
    private Label lbl_unit_n;
    @FXML
    private Label lbl_unit_e;
    @FXML
    private Label lbl_unit_a;
    @FXML
    private Label label_Message111;
    @FXML
    private ComboBox cb_deviation_unit;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cb_static_scanning.setSelected(Variable.static_scanning_status);
        lbl_unit_n.setText(Variable.reporting_unit);
        lbl_unit_e.setText(Variable.reporting_unit);
        lbl_unit_a.setText(Variable.reporting_unit);

        //      Static Scanning Interval
        try {
            cbb_static_scanning_interval.getItems().add("1 Second");
            cbb_static_scanning_interval.getItems().add("2 Second");
            cbb_static_scanning_interval.getItems().add("5 Second");
            cbb_static_scanning_interval.getItems().add("10 Second");
            cbb_static_scanning_interval.getItems().add("15 Second");
            cbb_static_scanning_interval.getItems().add("30 Second");
            cbb_static_scanning_interval.getItems().add("1 Minute");
            cbb_static_scanning_interval.getItems().add("5 Minute");
            cbb_static_scanning_interval.getItems().add("10 Minute");
            cbb_static_scanning_interval.getItems().add("15 Minute");
            cbb_static_scanning_interval.getItems().add("30 Minute");
            cbb_static_scanning_interval.getItems().add("1 Hour");
            cbb_static_scanning_interval.getItems().add("2 Hour");
            cbb_static_scanning_interval.getItems().add("3 Hour");
            cbb_static_scanning_interval.getItems().add("6 Hour");
            cbb_static_scanning_interval.getItems().add("12 Hour");
            cbb_static_scanning_interval.getItems().add("24 Hour");
            cbb_static_scanning_interval.setValue("" + Variable.static_scan_interval);
        } catch (Exception e) {
        }
        cb_dynamic_scanning.setSelected(Variable.dynamic_scanning_status);
        //      Dynamic Scanning Interval
        try {
            cbb_dynamic_scanning_interval.getItems().add("10 Minute");
            cbb_dynamic_scanning_interval.getItems().add("15 Minute");
            cbb_dynamic_scanning_interval.getItems().add("30 Minute");
            cbb_dynamic_scanning_interval.getItems().add("1 Hour");
            cbb_dynamic_scanning_interval.getItems().add("2 Hour");
            cbb_dynamic_scanning_interval.getItems().add("3 Hour");
            cbb_dynamic_scanning_interval.getItems().add("6 Hour");
            cbb_dynamic_scanning_interval.getItems().add("12 Hour");
            cbb_dynamic_scanning_interval.getItems().add("24 Hour");
            cbb_dynamic_scanning_interval.setValue("" + Variable.dynamic_scan_interval);
        } catch (Exception e) {
        }

        //      Dynamic Scanning Duration
        try {
            cbb_dynamic_scanning_duration.getItems().add("10 Second");
            cbb_dynamic_scanning_duration.getItems().add("15 Second");
            cbb_dynamic_scanning_duration.getItems().add("30 Second");
            cbb_dynamic_scanning_duration.getItems().add("1 Minute");
            cbb_dynamic_scanning_duration.getItems().add("5 Minute");
            cbb_dynamic_scanning_duration.getItems().add("10 Minute");
            cbb_dynamic_scanning_duration.setValue("" + Variable.dynamic_scan_duration);
        } catch (Exception e) {
        }

        cb_triggered_scanning.setSelected(Variable.trigger_scanning_status);
        //      Triggered Scanning Pre Data
        try {
            cbb_pre_data_triggered_scanning.getItems().add("10 Second");
            cbb_pre_data_triggered_scanning.getItems().add("15 Second");
            cbb_pre_data_triggered_scanning.getItems().add("30 Second");
            cbb_pre_data_triggered_scanning.getItems().add("1 Minute");
            cbb_pre_data_triggered_scanning.getItems().add("5 Minute");
            cbb_pre_data_triggered_scanning.getItems().add("10 Minute");
            cbb_pre_data_triggered_scanning.setValue("" + Variable.trigger_pre_data_duration);
        } catch (Exception e) {
        }

        //      Triggered Scanning Post Data
        try {
            cbb_post_data_triggered_scanning.getItems().add("10 Second");
            cbb_post_data_triggered_scanning.getItems().add("15 Second");
            cbb_post_data_triggered_scanning.getItems().add("30 Second");
            cbb_post_data_triggered_scanning.getItems().add("1 Minute");
            cbb_post_data_triggered_scanning.getItems().add("5 Minute");
            cbb_post_data_triggered_scanning.getItems().add("10 Minute");
            cbb_post_data_triggered_scanning.setValue("" + Variable.trigger_post_data_duration);
        } catch (Exception e) {
        }

        try {
            cb_deviation_unit.getItems().add("meter");
            cb_deviation_unit.getItems().add("feet");
            cb_deviation_unit.getItems().add("mm");
            cb_deviation_unit.getItems().add("cm");
            cb_deviation_unit.getItems().add("inch");
            cb_deviation_unit.setValue("" + Variable.reporting_unit);
        } catch (Exception e) {
        }

        tf_trigger_value_n.setText("" + Variable.trigger_value_n);
        tf_trigger_value_e.setText("" + Variable.trigger_value_e);
        tf_trigger_value_a.setText("" + Variable.trigger_value_a);

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
            if (validate_input()) {
                Tool.save_sampling_setting(
                        Variable.received_data_interval,
                        Variable.moving_avg,
                        cbb_static_scanning_interval.getValue().toString(),
                        Variable.scan_start_time,
                        cb_static_scanning.isSelected(),
                        cb_dynamic_scanning.isSelected(),
                        cbb_dynamic_scanning_interval.getValue().toString(),
                        cbb_dynamic_scanning_duration.getValue().toString(),
                        cb_triggered_scanning.isSelected(),
                        cbb_pre_data_triggered_scanning.getValue().toString(),
                        cbb_post_data_triggered_scanning.getValue().toString(),
                        tf_trigger_value_n.getText().trim(),
                        tf_trigger_value_e.getText().trim(),
                        tf_trigger_value_a.getText().trim(),
                        cb_deviation_unit.getValue().toString());

                Tool.read_sampling_setting();

                Button btn = (Button) event.getSource();
                Stage stage = (Stage) btn.getScene().getWindow();
                stage.close();
            }
        } catch (Exception e) {
            new Call_Dialog().showDialog("Unknown error occurred !!", "", "OK", "");
        }
    }

    private boolean validate_input() {
        try {

            if (cb_triggered_scanning.isSelected()) {
                String data = tf_trigger_value_n.getText();
                float value;
                if (data == null) {
                    new Call_Dialog().showDialog("Trigger Value N can't be empty !!", "", "OK", "");
                    return false;
                }
                try {
                    value = Float.parseFloat(data);
                } catch (Exception e) {
                    new Call_Dialog().showDialog("Trigger Value N should be neumeric !!", "", "OK", "");
                    return false;
                }
                if (value <= 0) {
                    new Call_Dialog().showDialog("Trigger Value for N can't be zero (0) or negative !!", "", "OK", "");
                    return false;
                }

                data = tf_trigger_value_e.getText();
                if (data == null) {
                    new Call_Dialog().showDialog("Trigger Value E can't be empty !!", "", "OK", "");
                    return false;
                }
                try {
                    value = Float.parseFloat(data);
                } catch (Exception e) {
                    new Call_Dialog().showDialog("Trigger Value E should be neumeric !!", "", "OK", "");
                    return false;
                }
                if (value <= 0) {
                    new Call_Dialog().showDialog("Trigger Value for E can't be zero (0) or negative !!", "", "OK", "");
                    return false;
                }
                data = tf_trigger_value_a.getText();
                if (data == null) {
                    new Call_Dialog().showDialog("Trigger Value A can't be empty !!", "", "OK", "");
                    return false;
                }
                try {
                    value = Float.parseFloat(data);
                } catch (Exception e) {
                    new Call_Dialog().showDialog("Trigger Value A should be neumeric !!", "", "OK", "");
                    return false;
                }

                if (value <= 0) {
                    new Call_Dialog().showDialog("Trigger Value for A can't be zero (0) or negative !!", "", "OK", "");
                    return false;
                }
            }
        } catch (Exception e) {
            new Call_Dialog().showDialog("Unknown error occurred !!", "", "OK", "");
            return false;
        }

        return true;
    }

    @FXML
    private void action_cb_deviation_unit(ActionEvent event) {
        String unit = cb_deviation_unit.getValue().toString();
        lbl_unit_n.setText(unit);
        lbl_unit_e.setText(unit);
        lbl_unit_a.setText(unit);
    }

    @FXML
    private void action_cb_triggered_scanning(ActionEvent event) {
        if (!Variable.is_deviation_data_reporting) {
            new Call_Dialog().showDialog("Triggered Scanning run only in deviation reporting !!", "", "OK", "");
            cb_triggered_scanning.setSelected(false);
        }
    }

}
