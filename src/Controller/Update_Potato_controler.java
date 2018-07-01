package Controller;

import Beans.Potato;
import Beans.selected_potato;
import Utils.ControllerCenterUtil;
import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import Potato.Potato_Upload;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Update_Potato_controler {
    Potato_controler potato_controler;

    Potato potato;

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
        potato = selected_potato.selected_potato;
        time_t.setIs24HourView(true);
        name_t.setText(potato.getName());
        time_t.setValue(LocalTime.parse(potato.getTimeString()));
        data_p.setValue(LocalDate.parse(potato.getDateString()));
        color_p.setValue(Color.web(potato.getColor()));
        note_t.setText(potato.getInfo());
        ca_t.setText(potato.getCa());
        ok.setSelected(potato.isIs_ok());
    }



    public void update() throws SQLException, ParseException {
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
            if (Potato_Upload.update(potato) == 1) {
                Potato_controler.updatestage.close();
                potato_controler.classify();
            } else {
                done.setStyle("-fx-background-color: #ff7567");
                done.setText("请重试");
            }
        } else {
            done.setStyle("-fx-background-color: #ff7567");
            done.setText("信息不全");
        }
    }

    public void delete() throws ParseException {
        if (Potato_Upload.delete(potato)==1){
            Potato_controler.updatestage.close();
            potato_controler.classify();
        }
    }
}
