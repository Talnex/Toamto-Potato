package Beans;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

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

        setPrefWidth(155);
        this.getChildren().addAll(time, event);

    }

    @Override
    public String toString() {
        return String.valueOf(potato.getNo());
    }
}
