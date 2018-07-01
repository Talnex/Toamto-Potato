package Controller;

import Beans.HboxCell;
import Beans.Potato;
import Beans.selected_potato;
import Potato.Potato_Dowload;
import Utils.ControllerCenterUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Potato_controler {

    private Date now = new Date();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static  Stage newtage = new Stage();
    public static  Stage updatestage = new Stage();

    List<String> labels = new ArrayList<>();

    List<List<HboxCell>> lists = new ArrayList<>();
    List<HboxCell> list1 = new ArrayList<>();
    List<HboxCell> list2 = new ArrayList<>();
    List<HboxCell> list3 = new ArrayList<>();
    List<HboxCell> list4 = new ArrayList<>();
    List<HboxCell> list5 = new ArrayList<>();
    List<HboxCell> list6 = new ArrayList<>();
    List<HboxCell> list7 = new ArrayList<>();

    List<ListView> listViews = new ArrayList<>();

    List<Potato> potatoes = new ArrayList<>();
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
    public JFXButton newbutton;


    @FXML
    private void initialize() throws ParseException, IOException {
        ControllerCenterUtil.controllerCenterMap.put("Potato_controler",this);

        label_1.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0), null, null)));
        label_2.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0), null, null)));
        label_3.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0), null, null)));
        label_4.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0), null, null)));
        label_5.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0), null, null)));
        label_6.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0), null, null)));
        label_7.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0), null, null)));

        label_1.setTextFill(Color.rgb(255, 255, 255));
        label_2.setTextFill(Color.rgb(255, 255, 255));
        label_3.setTextFill(Color.rgb(255, 255, 255));
        label_4.setTextFill(Color.rgb(255, 255, 255));
        label_5.setTextFill(Color.rgb(255, 255, 255));
        label_6.setTextFill(Color.rgb(255, 255, 255));
        label_7.setTextFill(Color.rgb(255, 255, 255));

        lists.add(list1);
        lists.add(list2);
        lists.add(list3);
        lists.add(list4);
        lists.add(list5);
        lists.add(list6);
        lists.add(list7);

        listViews.add(listview1);
        listViews.add(listview2);
        listViews.add(listview3);
        listViews.add(listview4);
        listViews.add(listview5);
        listViews.add(listview6);
        listViews.add(listview7);
        classify();

        for (ListView listview_ : listViews
                ) {
            listview_.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.getClickCount() == 2) {
                        String res = listview_.getSelectionModel().getSelectedItems().toString();
                        res = res.substring(1, res.length() - 1);
                        for (Potato p : potatoes
                                ) {
                            if (res.equals(String.valueOf(p.getNo()))) {
                                selected_potato.selected_potato = p;

                                Parent parent1 = null;
                                try {
                                    parent1 = FXMLLoader.load(getClass().getResource("/FXML/update_potato_fxml.fxml"));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                Scene scene1 = new Scene(parent1);
                                updatestage.setScene(scene1);
                                updatestage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                                    @Override
                                    public void handle(WindowEvent event) {
                                        updatestage.hide();
                                    }
                                });
                                updatestage.show();
                            }
                        }

                        listview_.getSelectionModel().clearSelection();
                    }
                }
            });
        }

        Parent parent = FXMLLoader.load(getClass().getResource("/FXML/new_potato_fxml.fxml"));
        Scene scene = new Scene(parent);
        newtage.setScene(scene);
        newtage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                newtage.close();
            }
        });
        newtage.show();
        newtage.hide();


    }

    public void classify() throws ParseException {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                labels.clear();
                for (List<HboxCell> list :
                        lists) {
                    list.clear();
                }
//                System.out.println(simpleDateFormat.format(now));
                potatoes = Potato_Dowload.select(simpleDateFormat.format(now));
                try {
                    labels = getweek(simpleDateFormat.format(now));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                label_1.setText(labels.get(0));
                label_2.setText(labels.get(1));
                label_3.setText(labels.get(2));
                label_4.setText(labels.get(3));
                label_5.setText(labels.get(4));
                label_6.setText(labels.get(5));
                label_7.setText(labels.get(6));


                if (!potatoes.isEmpty()) {
                    for (Potato po :
                            potatoes) {
//                        System.out.println(po.getDateString());
                        if (labels.indexOf(po.getDateString()) != -1) {
                            lists.get(labels.indexOf(po.getDateString())).add(new HboxCell(po));
                        }
                    }
                }else System.out.println("啥也没有");

                for (int i = 0; i < 7; i++) {
                    listViews.get(i).setFixedCellSize(50);
                    ObservableList<HboxCell> myObservableList = FXCollections.observableList(lists.get(i));
                    listViews.get(i).setItems(myObservableList);
                }
            }
        });

    }

    public void setNow(Date now) {
        this.now = now;
    }

    public List<String> getweek(String now) throws ParseException {

        List<String> label = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date time = sdf.parse(now);
        cal.setTime(time);
        System.out.println("要计算日期为:" + sdf.format(cal.getTime()));
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);

        for (int i = 0; i <= 6; i++) {
            label.add(sdf.format(cal.getTime()));
            cal.add(Calendar.DAY_OF_WEEK, 1);
        }
        return label;
    }

    public void addnow() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DAY_OF_WEEK, 7);
        setNow(calendar.getTime());
        classify();
    }

    public void decnow() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DAY_OF_WEEK, -7);
        setNow(calendar.getTime());
        classify();
    }

    public void upload() throws IOException {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                newtage.show();

            }
        });

    }
}
