package Beans;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXDialog;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import sun.jvm.hotspot.debugger.proc.arm.ProcARMThread;
import sun.security.krb5.internal.PAData;

import java.io.IOException;

public class HboxCell extends FlowPane {
    Potato potato;
    Label time = new Label("");
    Label event = new Label("");

    public Potato getPotato() {
        return potato;
    }

    public void setPotato(Potato potato) {
        this.potato = potato;
    }

    public HboxCell(Potato potato) {
        super();
        this.potato = potato;

        if (potato.isIs_ok()) time.setBackground(new Background(new BackgroundFill(Color.web("#ffffff"), null, null)));
        else time.setBackground(new Background(new BackgroundFill(Color.web(potato.getColor()), null, null)));
        time.setText(potato.getTimeString());
        time.setFont(Font.font(14));
        time.setPrefWidth(70);
        time.setPrefHeight(25);
        time.setAlignment(Pos.CENTER);

        event.setText(potato.getName());
        event.setFont(Font.font(16));
        event.setPrefWidth(85);
        event.setPrefHeight(25);
        event.setAlignment(Pos.CENTER);

        event.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    selected_potato.selected_potato = potato;
                    Parent parent = FXMLLoader.load(getClass().getResource("/FXML/Dialog.fxml"));
                    Scene scene = new Scene(parent);
                    Stage stage = new Stage();
                    stage.setX(event.getSceneX()+100);
                    stage.setY(event.getSceneY()+120);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setAlwaysOnTop(true);
                    stage.setScene(scene);
                    scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            stage.close();
                        }
                    });
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        setPrefWidth(155);
        this.getChildren().addAll(time, event);

    }

    @Override
    public String toString() {
        return String.valueOf(potato.getNo());
    }
}
