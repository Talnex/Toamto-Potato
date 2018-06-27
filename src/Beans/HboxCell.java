package Beans;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;

public class HboxCell extends FlowPane {
    Label time = new Label("");
    Label event = new Label("");

    public HboxCell(Potato potato){
        super();
        if (potato.isIs_ok()) time.setBackground(new Background(new BackgroundFill(ColorSetter.getColor(0), null, null)));
        else time.setBackground(new Background(new BackgroundFill(ColorSetter.getColor(potato.getRank()),null,null)));
        time.setText(potato.getDate().toString());
        event.setText(potato.getName());
        event.setOnMouseClicked(event -> System.out.println(potato.getInfo()));
        time.setFont(Font.font(14));
        event.setFont(Font.font(16));
        event.setPrefWidth(90);
        event.setAlignment(Pos.CENTER);
        resize(150,50);
        this.getChildren().addAll(time,event);
    }

}
