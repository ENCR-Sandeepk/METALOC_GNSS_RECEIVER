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
import tool.Validate_IP_Address;
import tool.Variable;

/**
 * FXML Controller class
 *
 * @author sandeepk
 */
public class FTP_SettingsController implements Initializable {

    Call_Dialog objCall_Dialog = new Call_Dialog();

    @FXML
    private Button btn_cancel;
    @FXML
    private Button btn_ok;
    @FXML
    private TextField tf_ftp_ip;
    @FXML
    private TextField tf_ftp_port;
    @FXML
    private TextField tf_ftp_user;
    @FXML
    private PasswordField pf_ftp_cnf_pass;
    @FXML
    private PasswordField pf_ftp_pass;
    @FXML
    private CheckBox cb_enable_ftp_upload;
    @FXML
    private CheckBox cb_enable_ftp_upload_S;
    @FXML
    private CheckBox cb_enable_ftp_upload_D;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Variable.is_dialog_confirmed = false;
        try {
            tf_ftp_ip.setText(Variable.ftp_url);
            tf_ftp_port.setText(Variable.ftp_port);
            tf_ftp_user.setText(Variable.ftp_user);
            cb_enable_ftp_upload.setSelected(Variable.ftp_upload_enable.equalsIgnoreCase("true"));
            cb_enable_ftp_upload_S.setSelected(Variable.ftp_upload_enableS.equalsIgnoreCase("true"));
            cb_enable_ftp_upload_D.setSelected(Variable.ftp_upload_enableD.equalsIgnoreCase("true"));
        } catch (Exception e) {
            e.printStackTrace();
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
            if (validate_ftp()) {
                Tool.save_ftp(tf_ftp_ip.getText().trim(), tf_ftp_port.getText().trim(), tf_ftp_user.getText().trim(), pf_ftp_pass.getText().trim(),
                        "" + cb_enable_ftp_upload.isSelected(), "" + cb_enable_ftp_upload_S.isSelected(), "" + cb_enable_ftp_upload_D.isSelected());
                Tool.read_ftp();

                Variable.is_dialog_confirmed = false;
                Button btn = (Button) event.getSource();
                Stage stage = (Stage) btn.getScene().getWindow();
                stage.close();
            }
        } catch (Exception e) {
            objCall_Dialog.showDialog("Unknown error occurred !!", "", "OK", "");
        }

    }

    private boolean validate_ftp() {
        try {
            if (tf_ftp_ip.getText() == null || tf_ftp_ip.getText().trim().isEmpty()) {
                objCall_Dialog.showDialog("FTP IP can't be empty !!", "", "OK", "");
                return false;
            } else if (Validate_IP_Address.isValid(tf_ftp_ip.getText().trim()) == 0) {
                objCall_Dialog.showDialog("Please enter a valid FTP IP Address !", "", "OK", "");
                return false;
            }

            if (tf_ftp_port.getText() == null || tf_ftp_port.getText().trim().isEmpty()) {
                objCall_Dialog.showDialog("FTP Port can't be empty !!", "", "OK", "");
                return false;
            } else {
                try {
                    Integer.parseInt(tf_ftp_port.getText().trim());
                } catch (Exception e) {
                    objCall_Dialog.showDialog("FTP Port should be integer number !!", "", "OK", "");
                    return false;
                }
            }

            if (tf_ftp_user.getText() == null || tf_ftp_user.getText().trim().isEmpty()) {
                objCall_Dialog.showDialog("FTP User can't be empty !!", "", "OK", "");
                return false;
            }
            if (pf_ftp_pass.getText() == null || pf_ftp_pass.getText().trim().isEmpty()) {
                objCall_Dialog.showDialog("FTP Password can't be empty !!", "", "OK", "");
                return false;
            }
            if (pf_ftp_cnf_pass.getText() == null || pf_ftp_cnf_pass.getText().trim().isEmpty()) {
                objCall_Dialog.showDialog("FTP Confirm Password can't be empty !!", "", "OK", "");
                return false;
            }
            if (!pf_ftp_pass.getText().equals(pf_ftp_cnf_pass.getText())) {
                objCall_Dialog.showDialog("FTP Password and Confirm Password are not same !!", "", "OK", "");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            objCall_Dialog.showDialog("Exception occurred !!", "", "OK", "");
            return false;
        }
        return true;
    }
}
