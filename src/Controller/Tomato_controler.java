package Controller;

import Beans.Runtime_Info;
import Beans.Tomato_clock;
import Tomato.Get_Runtime_Result;
import Tomato.Tomato_upload;
import Utils.DB.DBM;
import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.json.JSONException;
import sun.security.pkcs11.Secmod;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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


    @FXML
    private void initialize() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                List<String> info_name = new ArrayList<>();
                Connection connection = DBM.getConnection();
                try {
                    for (Runtime_Info info : Get_Runtime_Result.get_runtime_result()
                            ) {
                        info_name.add(info.getApp());
                        Tomato_upload.upload(info, connection);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                connection = DBM.getConnection();
                try {
                    Tomato_upload.check(connection,info_name);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        };
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(runnable, 0, 5, TimeUnit.SECONDS);
    }

    public void Button_start() {
        flag = !flag;
        if (flag == true) {
            timeline.setCycleCount(Animation.INDEFINITE);
            button.setBackground(new Background(new BackgroundFill(Color.rgb(159, 255, 121), null, null)));

            timeline.play();
            if (button.getText().equals("完成")) timeline.stop();
        } else {
            timeline.stop();
            button.setText("中断");
            button.setBackground(new Background(new BackgroundFill(Color.rgb(255, 133, 126), null, null)));
            tomato_clock.reset();
        }

    }


}
