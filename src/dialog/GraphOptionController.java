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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import tool.Variable;

/**
 * FXML Controller class
 *
 * @author sandeepk
 */
public class GraphOptionController implements Initializable {

    @FXML
    Label label_Message;
    @FXML
    private Button btn_cancel;
    @FXML
    private Button btn_ok;
    @FXML
    private Label label_Message1;
    @FXML
    private RadioButton rb_fixed_data_points;
    @FXML
    private ToggleGroup select_graph;
    @FXML
    private RadioButton rb_live_data_points;
    @FXML
    private RadioButton rb_scatter;
    @FXML
    private ToggleGroup select_graph_type;
    @FXML
    private RadioButton rb_scatter_with_smooth_line;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Variable.is_dialog_confirmed = false;
    }

    @FXML
    private void action_cancel(ActionEvent event) {
        try {
            Variable.is_dialog_confirmed = false;
            Button btn = (Button) event.getSource();
            Stage stage = (Stage) btn.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void action_ok(ActionEvent event) {
        try {
            Variable.is_dialog_confirmed = true;
            Variable.fixed_data_point_graph = rb_fixed_data_points.isSelected();
            Variable.scatter_with_smooth_line_graph = rb_scatter_with_smooth_line.isSelected();
            Button btn = (Button) event.getSource();
            Stage stage = (Stage) btn.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
