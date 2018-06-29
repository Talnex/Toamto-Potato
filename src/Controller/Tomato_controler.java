package Controller;

import Beans.Tomato_clock;
import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Tomato_controler {
    public JFXButton button;
    private boolean flag = false;
    private Tomato_clock tomato_clock = new Tomato_clock();
    private Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(0),
                    new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            button.setText(tomato_clock.getTime());
                        }
                    }
            ),
            new KeyFrame(Duration.seconds(1))
    );


    public void Button_start() {
        flag = !flag;
        if (flag == true){
            timeline.setCycleCount(Animation.INDEFINITE);
            button.setBackground(new Background(new BackgroundFill(Color.rgb(159,255,121),null,null)));

            timeline.play();
            if (button.getText().equals("完成")) timeline.stop();
        }else{
            timeline.stop();
            button.setText("中断");
            button.setBackground(new Background(new BackgroundFill(Color.rgb(255,133,126),null,null)));
            tomato_clock.reset();
        }

    }


}
