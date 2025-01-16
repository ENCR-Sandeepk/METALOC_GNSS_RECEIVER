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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tool.Call_Dialog;
import tool.Tool;
import tool.Variable;

/**
 * FXML Controller class
 *
 * @author sandeepk
 */
public class BaseLocationController implements Initializable {

    @FXML
    private Button btn_cancel;
    @FXML
    private Button btn_ok;
    @FXML
    private TextField tf_Latitude;
    @FXML
    private TextField tf_Longitude;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Variable.is_dialog_confirmed = false;
        Tool.read_base_location();

        try {
            tf_Latitude.setText(Variable.baae_latitude);
            tf_Longitude.setText(Variable.baae_longitude);
        } catch (Exception e) {
        }
    }

    @FXML
    private void action_cancel(ActionEvent event) {
        Variable.is_dialog_confirmed = false;
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void action_ok(ActionEvent event) {
        try {
            if (validate_input()) {
                Variable.is_dialog_confirmed = true;

                Variable.latitude = tf_Latitude.getText().trim();
                Variable.longitude = tf_Longitude.getText().trim();
                Tool.save_base_location(Variable.latitude, Variable.longitude);
                Tool.read_base_location();

                Button btn = (Button) event.getSource();
                Stage stage = (Stage) btn.getScene().getWindow();
                stage.close();
            }
        } catch (Exception e) {
        }
    }

    private boolean validate_input() {
        try {
            if (tf_Latitude.getText() != null
                    && tf_Latitude.getText().trim().length() > 0) {
                if (tf_Latitude.getText().trim().length() > 20) {
                    new Call_Dialog().showDialog("Latitude must be upto 20 digits !", "", "OK", "");
                    return false;
                }
                try {
                    Float.parseFloat(tf_Latitude.getText().trim());
                } catch (Exception e) {
                    new Call_Dialog().showDialog("Latitude should be neumeric", "", "OK", "");
                    return false;
                }
            } else {
                new Call_Dialog().showDialog("Latitude can not be empty", "", "OK", "");
                return false;
            }

            if (tf_Longitude.getText() != null
                    && tf_Longitude.getText().trim().length() > 0) {
                if (tf_Longitude.getText().trim().length() > 20) {
                    new Call_Dialog().showDialog("Longitude must be upto 20 digits !", "", "OK", "");
                    return false;
                }
                try {
                    Float.parseFloat(tf_Longitude.getText().trim());
                } catch (Exception e) {
                    new Call_Dialog().showDialog("Longitude should be neumeric", "", "OK", "");
                    return false;
                }
            } else {
                new Call_Dialog().showDialog("Longitude can not be empty", "", "OK", "");
                return false;
            }

        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
