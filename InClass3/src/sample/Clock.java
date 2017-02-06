package sample;

/**
 * Created by Devon on 2/2/2017.
 */

import javafx.scene.paint.Color;

import javafx.scene.control.Label;
import java.util.HashMap;

public class Clock {

    private Label hoursLabel;
    private Label minutesLabel;
    private int editState;
    private int hoursState;
    private int minutesState;

    public Clock(Label hoursLabel, Label minutesLabel)
    {
        this.hoursLabel = hoursLabel;
        this.minutesLabel = minutesLabel;

        this.editState = 0;
        this.hoursState = 0;
        this.minutesState = 0;


    }

    public void Increase(){

        switch(this.editState){
            case(1):

                this.hoursLabel.setText(Integer.toString(this.hoursState));
                this.hoursState++;
                if (this.hoursState > 12) {
                    this.hoursState = 0;
                }
                break;

            case(2):

                this.minutesLabel.setText(Integer.toString(this.minutesState));
                this.minutesState++;

                if (this.minutesState > 59)
                {
                    this.minutesState = 0;
                }
                break;

        }

    }

    public void Decrease(){
        switch(this.editState){
            case(1):

                this.hoursLabel.setText(Integer.toString(this.hoursState));
                this.hoursState--;
                if (this.hoursState < 0) {
                    this.hoursState = 12;
                }
                break;

            case(2):

                this.minutesLabel.setText(Integer.toString(this.minutesState));
                this.minutesState--;

                if (this.minutesState < 0)
                {
                    this.minutesState = 59;
                }
                break;

        }
    }

    public void incrementEdit() {

        switch(this.editState){
            case(0):
                this.minutesLabel.setTextFill(Color.color(0, 0, 0));
                this.hoursLabel.setTextFill(Color.color(1, 0, 0));
                break;
            case(1):
                this.minutesLabel.setTextFill(Color.color(1, 0, 0));
                this.hoursLabel.setTextFill(Color.color(0, 0, 0));
                break;
            case(2):
                this.minutesLabel.setTextFill(Color.color(0, 0, 0));
                this.hoursLabel.setTextFill(Color.color(0, 0, 0));
                break;
        }

        this.editState++;

        if (this.editState > 2)
        {
            this.editState = 0;
        }
    }

    public void resetEdit()
    {
        this.editState = 0;
        this.minutesLabel.setTextFill(Color.color(0, 0, 0));
        this.hoursLabel.setTextFill(Color.color(0, 0, 0));
    }

}
