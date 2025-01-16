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
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tool.Constant;

/**
 * FXML Controller class
 *
 * @author sandeepk
 */
public class AboutUs_Controller implements Initializable {

    @FXML
    private Button btn_ok;
    @FXML
    private Label lbl_version;
    @FXML
    private Label lbl_copyright;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbl_version.setText(Constant.VERSION);
        lbl_copyright.setText(Constant.COPYRIGHT);
    }

    @FXML
    private void action_ok(ActionEvent event) {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

}
