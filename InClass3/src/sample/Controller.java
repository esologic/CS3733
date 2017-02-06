package sample;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import sample.Clock;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javafx.scene.control.Label;


public class Controller {

    @FXML
    private Button edit;

    @FXML
    private Button increase;

    @FXML
    private Button decrease;

    @FXML
    private Button set;

    @FXML
    private Label hoursLabel;

    @FXML
    private Label minutesLabel;

    private Clock c;

    @FXML
    public void initialize() {
        this.c = new Clock(this.hoursLabel, this.minutesLabel);
    }


    @FXML
    void editValue() {
        this.c.incrementEdit();
    }

    @FXML
    void increaseValue() {
        this.c.Increase();
    }

    @FXML
    void decreaseValue() {
        this.c.Decrease();
    }

    @FXML
    void setValue() {
        this.c.resetEdit();
    }

}
