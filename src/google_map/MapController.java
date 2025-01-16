/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package google_map;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import tool.Call_Dialog;
import tool.Variable;

/**
 * FXML Controller class
 *
 * @author sandeepk
 */
public class MapController implements Initializable {

    WebEngine webEngine;

    @FXML
    private WebView web_view;
    @FXML
    private Button btn_ok;
    @FXML
    private Button btn_show;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        webEngine = web_view.getEngine();
        final URL urlGoogleMaps = getClass().getResource("show.html");
        webEngine.load(urlGoogleMaps.toExternalForm());

    }

    @FXML
    private void action_ok(ActionEvent event) {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void action_show(ActionEvent event) {
        try {

            new Call_Dialog().show_BaseLocation();

            if (Variable.is_dialog_confirmed) {
//                Variable.google_map_information = "RF-LoRa-GATEWAY1,86.0,26.0,RF-TILT-NODE-1,20,20,RF-TILT-NODE-2,28,28";

                Variable.google_map_information = "BASE_STATION," + Variable.baae_latitude + "," + Variable.baae_longitude + Variable.google_map_information;

                System.out.println(Variable.google_map_information);
                String a = webEngine.executeScript("myFunction(\"" + Variable.google_map_information + "\")").toString();

                btn_show.setVisible(false);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
