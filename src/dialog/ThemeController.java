/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialog;

import com.jfoenix.controls.JFXColorPicker;
import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tool.Call_Dialog;
import tool.Constant;
import tool.Theme;
import tool.Variable;

/**
 * FXML Controller class
 *
 * @author sandeepk
 */
public class ThemeController implements Initializable {

    @FXML
    private TextField tf_theme_name;
    @FXML
    private ComboBox cbb_select_theme;
    @FXML
    private JFXColorPicker cp_color_back;
    @FXML
    private AnchorPane ap_content;
    @FXML
    private AnchorPane ap_theme;
    @FXML
    private Button btn_add_theme;
    @FXML
    private Button btn__apply_theme;
    @FXML
    private Button btn_delete_theme;
    @FXML
    private ComboBox cbb_header_background;
    @FXML
    private ImageView img_header_background;
    @FXML
    private Button btn_add_view_theme;
    @FXML
    private Button btn_apply_view_theme;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateTheme_cbb();
        updateHeaderImage_cbb();
    }

    @FXML
    private void action_add_theme(ActionEvent event) {

        if (validate_theme()) {
            try {
                String color_back = cp_color_back.getValue().toString().substring(2);
                String header = cbb_header_background.getValue().toString();

                Theme.addTheme(tf_theme_name.getText().trim(), color_back, header);
                updateTheme_cbb();
                new Call_Dialog().showDialog("Theme added successfully !!", "", "OK", "");
            } catch (Exception e) {
                new Call_Dialog().showDialog("Unknown error occurred !! Unable to added theme.", "", "OK", "");
            }
        }
    }

    @FXML
    private void action_apply_theme(ActionEvent event) {

        try {
            Variable.theme_selected = cbb_select_theme.getValue().toString();
            Theme.loadThemeColor(Variable.theme_selected);
            Theme.modify_selected_theme(Variable.theme_selected);
            new Call_Dialog().showDialog("Theme Applied Successfully !!", "", "OK", "");
        } catch (Exception e) {
            new Call_Dialog().showDialog("Unknown error occurred !! Unable to apply selected theme.", "", "OK", "");
        }
    }

//    private void load_theme() {
//        try {
//            ap_content.setStyle("-fx-background-color: #" + Variable.color_front_or_btn);
//            ap_theme.setStyle("-fx-background-color: #" + Variable.color_back);
//            btn_add_theme.setStyle("-fx-background-color: #" + Variable.color_front_or_btn);
//            btn__apply_theme.setStyle("-fx-background-color: #" + Variable.color_front_or_btn);
//            btn_delete_theme.setStyle("-fx-background-color: #" + Variable.color_front_or_btn);
//
////            Main_Controller.get_Controller().load_theme();
//            try {
//
////                if (Variable.connection_status == Constant.WITHOUT_CONNECTION) {
////                    EAN_95MW_NodeMenu_Controller.get_Controller().load_theme();
////                } else if (Variable.device_model.equalsIgnoreCase(Constant.EWG_01)) {
////                    EWG_01_GatewayMenu_Controller.get_Controller().load_theme();
////                } else if (Variable.device_model.equalsIgnoreCase(Constant.EAN_95MW)) {
////                    EAN_95MW_NodeMenu_Controller.get_Controller().load_theme();
////                } else if (Variable.device_model.equalsIgnoreCase(Constant.EWN_01V)) {
////
////                }
//            } catch (Exception e) {
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
    @FXML
    private void action_delete_theme(ActionEvent event) {
        try {
            String theme_name = cbb_select_theme.getValue().toString();

            if (theme_name.equalsIgnoreCase(Variable.theme_selected)) {
                new Call_Dialog().showDialog("Applied theme can't be deleted !!", "", "OK", "");
            } else if (theme_name.equalsIgnoreCase("Theme-1")
                    || theme_name.equalsIgnoreCase("Theme-2")
                    || theme_name.equalsIgnoreCase("Theme-3")
                    || theme_name.equalsIgnoreCase("Theme-4")
                    || theme_name.equalsIgnoreCase("Theme-5")
                    || theme_name.equalsIgnoreCase("Theme-6")
                    || theme_name.equalsIgnoreCase("Theme-7")
                    || theme_name.equalsIgnoreCase("Theme-8")
                    || theme_name.equalsIgnoreCase("Theme-9")) {
                new Call_Dialog().showDialog("Default theme can't be deleted !!", "", "OK", "");
            } else {
                try {
                    new Call_Dialog().showDialog("Are you sure want to delete theme ?", "", "OK", "Cancel");

                    if (Variable.is_dialog_confirmed) {
                        new File(Variable.storage_location + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.THEME_FOLDER + "/" + theme_name + ".txt").delete();
                        updateTheme_cbb();
                        new Call_Dialog().showDialog("Theme Deleted Successfully !!", "", "OK", "");
                    }
                } catch (Exception e) {
                    new Call_Dialog().showDialog("Unknown error occured !! Unable to delete Theme.", "", "OK", "");
                }
            }

        } catch (Exception e) {
        }

    }

    private boolean validate_theme() {
        try {
            String theme_name = tf_theme_name.getText().trim();
            if (theme_name != null && theme_name.length() > 0) {
                if (theme_name.contains(" ")) {
                    new Call_Dialog().showDialog("Theme name can't contain space !!", "", "OK", "");
                    return false;
                }
                if (theme_name.length() > 15) {
                    new Call_Dialog().showDialog("Theme name should be upto 15 characters !!", "", "OK", "");
                    return false;
                }
            } else {
                new Call_Dialog().showDialog("Theme name can't be empty !!", "", "OK", "");
                return false;
            }

            if (Theme.isDuplicateTheme(Variable.storage_location + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.THEME_FOLDER + "/" + theme_name + ".txt") || Theme.isPreDefineTheme(theme_name)) {

                new Call_Dialog().showDialog("Theme name already exists !!", "", "OK", "");
                return false;
            }

            if (Theme.validateTheme(theme_name)) {
                new Call_Dialog().showDialog("Theme name should contain only (A-Z), (a-z), (0-9) characters, underscore and minus symbols only !!", "", "OK", "");
                return false;
            }
            return true;
        } catch (Exception e) {
            new Call_Dialog().showDialog("Unkown error occured !!", "", "OK", "");
            e.printStackTrace();
            return false;
        }
    }

    private void updateTheme_cbb() {

        try {
            String fileList = "";
            String temp;
            File[] files = finder(Variable.storage_location + Constant.COMPANY_FOLDER + Constant.ROOT_FOLDER + Constant.THEME_FOLDER, ".txt");

            cbb_select_theme.getItems().add("Theme-1");
            cbb_select_theme.getItems().add("Theme-2");
            cbb_select_theme.getItems().add("Theme-3");
            cbb_select_theme.getItems().add("Theme-4");
            cbb_select_theme.getItems().add("Theme-5");
            cbb_select_theme.getItems().add("Theme-6");
            cbb_select_theme.getItems().add("Theme-7");
            cbb_select_theme.getItems().add("Theme-8");
            cbb_select_theme.getItems().add("Theme-9");

            for (File file : files) {
                temp = file.getName();
                fileList = fileList + temp + "\n";
                cbb_select_theme.getItems().add(temp.substring(0, temp.length() - 4));
            }
            cbb_select_theme.setValue(Variable.theme_selected.trim());
        } catch (Exception e) {
        }
    }

    private void updateHeaderImage_cbb() {

        try {

            cbb_header_background.getItems().add("Option-1");
            cbb_header_background.getItems().add("Option-2");
            cbb_header_background.getItems().add("Option-3");
            cbb_header_background.getItems().add("Option-4");
            cbb_header_background.getItems().add("Option-5");
            cbb_header_background.getItems().add("Option-6");
            cbb_header_background.getItems().add("Option-7");
            cbb_header_background.getItems().add("Option-8");
            cbb_header_background.getItems().add("Option-9");

//            cbb_select_theme.setValue(Variable.selected_theme.trim());
        } catch (Exception e) {
        }

    }

    public static File[] finder(String dirName, final String extension) {

        File dir = new File(dirName);
        return dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String filename) {
                return filename.endsWith(extension);
            }
        });
    }

    boolean a;

    private void action_cbb_header_background(ActionEvent event) {
        try {
            System.out.println(a);
            if (a) {
                img_header_background.setImage(new Image("img/about.png"));
            } else {
                img_header_background.setImage(new Image("img/wave.png"));
            }
            a = !a;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void action_close_application(ActionEvent event) {
        try {
            Button btn = (Button) event.getSource();
            Stage stage = (Stage) btn.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
        }
    }

    @FXML
    private void action_btn_add_view_theme(ActionEvent event) {
    }

    @FXML
    private void action_btn_apply_view_theme(ActionEvent event) {
    }

}
