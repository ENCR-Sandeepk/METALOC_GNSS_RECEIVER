/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 *
 * @author sandeepk
 */
public class Call_Dialog {

    public void show_progress_bar(String message) {
        try {
            Variable.progress_bar_msg = message;
            Parent root = FXMLLoader.load(getClass().getResource("/dialog/ProgressBar.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Progress");
            Image image = new Image(getClass().getResourceAsStream("/img/icon.png"));
            stage.getIcons().add(image);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));

            stage.setOnCloseRequest((WindowEvent t) -> {
                t.consume();
            });

            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void show_google_map() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/google_map/Map.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Google Map");
            Image image = new Image(getClass().getResourceAsStream("/img/icon.png"));
            stage.getIcons().add(image);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));

            stage.setOnCloseRequest((WindowEvent t) -> {
                t.consume();
            });

            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void show_BaseLocation() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/dialog/BaseLocation.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Base Location");
            Image image = new Image(getClass().getResourceAsStream("/img/icon.png"));
            stage.getIcons().add(image);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));

            stage.setOnCloseRequest((WindowEvent t) -> {
                t.consume();
            });

            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showDialog(String message, String icon, String ok_btn_text, String cancel_btn_text) {

        try {
            Variable.is_dialog_confirmed = false;
            Variable.dialog_ok_btn_text = ok_btn_text;
            Variable.dialog_cancel_btn_text = cancel_btn_text;
            Variable.dialog_message = message;
            Parent root = FXMLLoader.load(getClass().getResource("/dialog/Dialog.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Message");
            Image image = new Image(getClass().getResourceAsStream("/img/icon.png"));
            stage.getIcons().add(image);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));

            stage.setOnCloseRequest((WindowEvent t) -> {
                t.consume();
            });

            stage.showAndWait();
        } catch (Exception e) {
        }

    }

    public void showSelectConfigFilePathDialog() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/dialog/SelectConfigFilePath.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Path");
            Image image = new Image(getClass().getResourceAsStream("/img/icon.png"));
            stage.getIcons().add(image);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));

            stage.setOnCloseRequest((WindowEvent t) -> {
                t.consume();
            });

            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showGraphOption() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/dialog/GraphOption.fxml"));
            Stage stage = new Stage();
            stage.setTitle("About Us");
            Image image = new Image(getClass().getResourceAsStream("/img/icon.png"));
            stage.getIcons().add(image);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));

            stage.setOnCloseRequest((WindowEvent t) -> {
                t.consume();
            });

            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showAboutUs() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/dialog/AboutUs.fxml"));
            Stage stage = new Stage();
            stage.setTitle("About Us");
            Image image = new Image(getClass().getResourceAsStream("/img/icon.png"));
            stage.getIcons().add(image);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));

            stage.setOnCloseRequest((WindowEvent t) -> {
                t.consume();
            });

            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showSelectParameter() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/dialog/SelectParameter.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Select Parameter's");
            Image image = new Image(getClass().getResourceAsStream("/img/icon.png"));
            stage.getIcons().add(image);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));

            stage.setOnCloseRequest((WindowEvent t) -> {
                t.consume();
            });

            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showConfigureScan() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/dialog/Configure_scan.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Configure Scan");
            Image image = new Image(getClass().getResourceAsStream("/img/icon.png"));
            stage.getIcons().add(image);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));

            stage.setOnCloseRequest((WindowEvent t) -> {
                t.consume();
            });

            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showChangeTheme() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/dialog/Theme.fxml"));
            Stage stage = new Stage();
            stage.setTitle("About Us");
            Image image = new Image(getClass().getResourceAsStream("/img/icon.png"));
            stage.getIcons().add(image);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));

            stage.setOnCloseRequest((WindowEvent t) -> {
                t.consume();
            });

            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showFTP_Settings() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/dialog/FTP_Settings.fxml"));
            Stage stage = new Stage();
            stage.setTitle("About Us");
            Image image = new Image(getClass().getResourceAsStream("/img/icon.png"));
            stage.getIcons().add(image);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));

            stage.setOnCloseRequest((WindowEvent t) -> {
                t.consume();
            });

            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String showTimePicker_hh_mm() {
        try {
            Variable.is_dialog_confirmed = false;
            Parent root = FXMLLoader.load(getClass().getResource("/dialog/Time_Picker_hh_mm.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Time");
            Image image = new Image(getClass().getResourceAsStream("/img/icon.png"));
            stage.getIcons().add(image);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));

            stage.setOnCloseRequest((WindowEvent t) -> {
                t.consume();
            });

            stage.showAndWait();
            return Variable.time_picker_hh_mm;

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}
