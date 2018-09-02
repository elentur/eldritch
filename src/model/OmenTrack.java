package model;

import enums.OmenStates;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class OmenTrack {


    private int value;
    private final OmenStates[] states;
    private final BooleanProperty update;

    public OmenTrack(OmenStates startValue) {
        value = startValue.ordinal();
        update = new SimpleBooleanProperty(false);
        states = new OmenStates[]{OmenStates.GREEN_COMET, OmenStates.BLUE_STARS_1, OmenStates.RED_ECLIPSE, OmenStates.BULE_STARS_2};
    }

    public BooleanProperty updateProperty() {
        return update;
    }

    public void advanceOmen(int value) {

        this.value = (this.value + value) % states.length;
        update.set(true);
    }

    public void retreatOmen(int value) {
        this.value = (this.value+ states.length - value) % states.length;
        update.set(true);
    }

    public void setOmen(OmenStates value) {

        this.value=  value.ordinal();
        update.set(true);
    }

    public OmenStates getOmen() {
        return states[value];
    }
}
