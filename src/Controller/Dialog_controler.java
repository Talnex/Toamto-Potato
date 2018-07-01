package Controller;

import Beans.Potato;
import Beans.selected_potato;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Dialog_controler {

    Potato potato;

    public Label note;
    public Label ca;

    @FXML
    private void initialize(){
        potato = selected_potato.selected_potato;
        note.setText(potato.getInfo());
        ca.setText(potato.getCa());
    }

}
