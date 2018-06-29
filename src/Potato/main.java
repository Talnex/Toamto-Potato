package Potato;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class main extends Application {

    public static void main(String[] args) throws IOException {
        launch(args);
//        Runtime.getRuntime().exec("say Hello World!");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/FXML/Potato_fxml.fxml"));
        Scene scene = new Scene(parent);
        scene.getStylesheets().add(UIManager.class.getResource("/Style/listview.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
