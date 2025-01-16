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
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import tool.Tool;
import tool.Variable;

/**
 * FXML Controller class
 *
 * @author sandeepk
 */
public class Time_Picker_hh_mm_Controller implements Initializable {

    @FXML
    private Button btn_ok;
    @FXML
    private Button btn_cancel;
    @FXML
    private ComboBox cbb_hour;
    @FXML
    private ComboBox cbb_minute;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        set_value_into_combobox();
    }

    @FXML
    private void action_ok(ActionEvent event) {

        try {
            Variable.time_picker_hh_mm = cbb_hour.getValue().toString() + ":" + cbb_minute.getValue().toString();

            Variable.is_dialog_confirmed = true;
            Stage stage = (Stage) btn_ok.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
        }
    }

    @FXML
    private void action_cancel(ActionEvent event) {
        try {
            Variable.time_picker_hh_mm = "";
            Variable.is_dialog_confirmed = false;
            Stage stage = (Stage) btn_cancel.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
        }
    }

    private void set_value_into_combobox() {
        try {
            for (int i = 0; i <= 23; i++) {
                cbb_hour.getItems().add(Tool.pad("" + i, 2));
            }
            cbb_hour.setValue("00");
            for (int i = 0; i <= 59; i++) {
                cbb_minute.getItems().add(Tool.pad("" + i, 2));
            }
            cbb_minute.setValue("00");

            if (Variable.set_time_picker_hh_mm.length() > 2) {

                String time[] = Variable.set_time_picker_hh_mm.split(":");

                cbb_hour.setValue(time[0]);
                cbb_minute.setValue(time[1]);
            }

        } catch (Exception e) {
        }
    }
}
