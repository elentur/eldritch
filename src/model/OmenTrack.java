package model;

import enums.OmenStates;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import lombok.Getter;
import lombok.Setter;
import model.Item.token.EldritchToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OmenTrack {


    private int value;
    private final OmenStates[] states;
    private final BooleanProperty update;
    @Getter
    private boolean editable;

    private HashMap<EldritchToken, OmenStates> eldritchTokens;

    public OmenTrack(OmenStates startValue) {
        value = startValue.ordinal();
        update = new SimpleBooleanProperty(false);
        states = new OmenStates[]{OmenStates.GREEN_COMET, OmenStates.BLUE_STARS_1, OmenStates.RED_ECLIPSE, OmenStates.BULE_STARS_2};
        eldritchTokens = new HashMap<>();
    }

    public BooleanProperty updateProperty() {
        return update;
    }

    public void advanceOmen(int value) {

        this.value = (this.value + value) % states.length;
        update.set(true);
    }

    public void retreatOmen(int value) {
        this.value = (this.value + states.length - value) % states.length;
        update.set(true);
    }

    public void setOmen(OmenStates value) {

        this.value = value.ordinal();
        update.set(true);
    }

    public OmenStates getOmen() {
        return states[value];
    }

    public void setEditable(boolean value) {
        editable = value;
        update.set(true);
    }

    public void addToken(OmenStates states) {
        eldritchTokens.put(new EldritchToken(null), states);
        update.set(true);
    }

    public void removeToken(OmenStates states) {
        for (EldritchToken key : eldritchTokens.keySet()) {
            if (eldritchTokens.get(key).equals(states)) {
                eldritchTokens.remove(key);
                update.set(true);
                break;
            }
        }
    }

    public int getToken(OmenStates omen) {
        int i = 0;
        for (EldritchToken key : eldritchTokens.keySet()) {
            if (eldritchTokens.get(key).equals(omen)) {
                i++;
            }
        }
        return i;
    }
}
