/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialog;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import tool.Variable;

/**
 * FXML Controller class
 *
 * @author sandeepk
 */
public class ProgressBarController implements Initializable {

    float progress;

    @FXML
    private Label lbl_msg;
    @FXML
    private ProgressBar progress_bar;
    @FXML
    private Label lbl_progress;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        progress_bar.setProgress(0.0);
        lbl_msg.setText(Variable.progress_bar_msg);

        new Thread() {
            @Override
            public void run() {

                while (Variable.progressBarRunning) {
                    try {

                        progress = Variable.progress / 100.0f;

                        if (progress >= 100) {
                            progress = 100;
                        }
                        Platform.runLater(() -> {
                            try {
                                lbl_progress.setText("" + ((int) (progress * 100)) + " %");
                                progress_bar.setProgress(progress);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        Thread.sleep(50);

                    } catch (Exception e) {
                    }
                }
                Platform.runLater(() -> {
                    try {
                        Stage stage = (Stage) lbl_msg.getScene().getWindow();
                        stage.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

            }
        }.start();
    }

}
