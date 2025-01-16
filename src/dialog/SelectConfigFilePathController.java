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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import tool.Call_Dialog;
import tool.Constant;
import tool.Tool;
import tool.Variable;

/**
 * FXML Controller class
 *
 * @author sandeepk
 */
public class SelectConfigFilePathController implements Initializable {

    Call_Dialog objCall_Dialog = new Call_Dialog();

    @FXML
    private Label lbl_data_file_location;
    @FXML
    private Button btn_browse;
    @FXML
    private AnchorPane achor_pane;
    @FXML
    private Button btn_cancel;
    @FXML
    private Button btn_ok;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Variable.is_dialog_confirmed = false;
        lbl_data_file_location.setText(Variable.storage_location);
    }

    @FXML
    private void action_browse(ActionEvent event) {

        Stage stage = (Stage) achor_pane.getScene().getWindow();
        final DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Browse ...");

        String configFilePath = Tool.backslashReplace(System.getProperty("user.home"));
        if (Variable.storage_location != null && Variable.storage_location.trim().length() > 0) {
            chooser.setInitialDirectory(new File(Variable.storage_location));
        } else {
            chooser.setInitialDirectory(new File(configFilePath));
        }

        File file = chooser.showDialog(stage);
        if (file != null) {
            lbl_data_file_location.setText(Tool.removeDoubleQuotes(file.getAbsolutePath()));
        }

    }

    @FXML
    private void action_cancel(ActionEvent event) {
        Variable.isConfigFileFolderDone = false;
        Stage stage = (Stage) achor_pane.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void action_ok(ActionEvent event) {

        if (lbl_data_file_location.getText().length() > 0) {

            try {

                Stage stage = (Stage) achor_pane.getScene().getWindow();
                stage.close();

                String configFilePath = Tool.backslashReplace(System.getProperty("user.home"));

                File dir = new File(configFilePath + Constant.COMPANY_FOLDER);
                if (!dir.exists()) {
                    dir.mkdir();
                }
                configFilePath = configFilePath + Constant.COMPANY_FOLDER;

                File dir1 = new File(configFilePath + Constant.ROOT_FOLDER);
                if (!dir1.exists()) {
                    dir1.mkdir();
                }
                configFilePath = configFilePath + Constant.ROOT_FOLDER;

                Tool.createConfigFile(configFilePath + Constant.CONFIG_FILE, lbl_data_file_location.getText());

                if (Tool.create_data_folder(lbl_data_file_location.getText())) {
                    Tool.readConfigFile();
                    Variable.isConfigFileFolderDone = true;
                } else {
                    objCall_Dialog.showDialog("Unable to Create Data Files Folder. Please change Storage Location", "", "OK", "");
                }
            } catch (Exception e) {
                objCall_Dialog.showDialog("Unable to Create Config Files Folder", "", "OK", "");
            }
        } else {
            objCall_Dialog.showDialog("Please Provide Data File Storage Location", "", "OK", "");
        }
    }
}
