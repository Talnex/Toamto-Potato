package Tomato;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class main extends Application {
    public static void main(String[] args) throws IOException {
        launch(args);

    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Tomato_fxml.fxml"));

        primaryStage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);

        scene.setRoot(root);

        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
