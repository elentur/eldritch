package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class DoomTrack {
    private int value;
    private BooleanProperty update;

    public DoomTrack(int startValue){
        value=startValue;
        update = new SimpleBooleanProperty(false);
    }
    public BooleanProperty updateProperty() {
        return update;
    }
    public void advanceDoom(int value){

            this.value-=value;
            if(this.value<0){
                this.value=0;
            }
        update.set(true);
    }
    public  void retreatDoom(int value){


        this.value+=value;
        if(this.value>20){
            this.value=20;
        }
        update.set(true);
    }
    public void setDoom(int value){
        this.value=value;
        if(this.value<0){
            this.value=0;
        }else if(this.value>20){
            this.value=20;
        }
        update.set(true);

    }

    public int getDoom(){
        return value;
    }
}
