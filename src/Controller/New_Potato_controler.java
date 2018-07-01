package Controller;

import Beans.Potato;
import Potato.Potato_Upload;
import Utils.ControllerCenterUtil;
import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

import java.text.ParseException;
import java.util.Date;

public class New_Potato_controler {
    Potato potato;
    Potato_controler potato_controler;

    @FXML
    public JFXTimePicker time_t;
    @FXML
    public JFXDatePicker data_p;
    @FXML
    public JFXColorPicker color_p;
    @FXML
    public JFXTextField name_t;
    @FXML
    public JFXTextField note_t;
    @FXML
    public JFXTextField ca_t;
    @FXML
    public JFXToggleButton ok;
    @FXML
    public JFXButton done;


    @FXML
    private void initialize() {
        potato_controler = (Potato_controler) ControllerCenterUtil.controllerCenterMap.get("Potato_controler");
        potato = new Potato();
        time_t.setIs24HourView(true);
        name_t.setPromptText("事件名");
        time_t.setPromptText("选择时间");
        data_p.setPromptText("选择日期");
        color_p.setPromptText("选择日期");
        note_t.setPromptText("备注");
        ca_t.setPromptText("类别");
    }


    public void upload() throws InterruptedException, ParseException {
        if (data_p.getValue() != null && time_t.getValue() != null && !name_t.getText().isEmpty()) {
            potato.setName(name_t.getText());
            potato.setColor(color_p.getValue().toString());
            potato.setCa(ca_t.getText());
            potato.setInfo(note_t.getText());
            potato.setIs_ok(ok.isSelected());
            Date date = new Date(data_p.getValue().getYear()-1900,
                    data_p.getValue().getMonthValue()-1,
                    data_p.getValue().getDayOfMonth(),
                    time_t.getValue().getHour(),
                    time_t.getValue().getMinute());
            potato.setDate(date);
            System.out.println(potato.toString());
            if (Potato_Upload.insert(potato) == 1) {
                Potato_controler.newtage.close();
            } else {
                done.setStyle("-fx-background-color: #ff7567");
                done.setText("请重试");
            }
        } else {
            done.setStyle("-fx-background-color: #ff7567");
            done.setText("信息不全");
        }
        potato_controler.classify();

    }


}
