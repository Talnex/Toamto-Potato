package Controller;

import Beans.HboxCell;
import Beans.Potato;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.input.MouseEvent;

import javax.jws.soap.SOAPBinding;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;


public class Potato_controler {

    @FXML
    public Label label_1;
    @FXML
    public Label label_2;
    @FXML
    public Label label_3;
    @FXML
    public Label label_4;
    @FXML
    public Label label_5;
    @FXML
    public Label label_6;
    @FXML
    public Label label_7;

    @FXML
    public JFXListView listview2;
    @FXML
    public JFXListView listview5;
    @FXML
    public JFXListView listview4;
    @FXML
    public JFXListView listview1;
    @FXML
    public JFXListView listview3;
    @FXML
    public JFXListView listview6;
    @FXML
    public JFXListView listview7;


    @FXML
    private void initialize(){
        List<HboxCell> list = new ArrayList<>();
        list.add(new HboxCell(new Potato(new Time(2211),false,1,"学习","吃饭","你好")));
        list.add(new HboxCell(new Potato(new Time(1222),false,2,"学习","吃饭","你好")));
        list.add(new HboxCell(new Potato(new Time(3333),false,3,"学习","吃饭","你好")));
        list.add(new HboxCell(new Potato(new Time(4444),false,4,"学习","吃饭","你好")));
        listview1.setFixedCellSize(50);
        ObservableList<HboxCell> myObservableList = FXCollections.observableList(list);
        listview1.setItems(myObservableList);



    }

}
