/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import tool.Constant;

/**
 *
 * @author sandeepk
 */
public class METALOC_GNSS_RECEIVER_Main extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("METALOC_GNSS_RECEIVER.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);

        // Setting Image icon
        Image image = new Image(getClass().getResourceAsStream("/img/icon.png"));
        stage.getIcons().add(image);

        // Setting Title
        stage.setTitle("EGNSS-01 " + Constant.VERSION);

        //move around here
        root.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.setOnCloseRequest((WindowEvent t) -> {
            t.consume();
        });

        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
