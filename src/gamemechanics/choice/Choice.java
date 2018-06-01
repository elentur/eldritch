package gamemechanics.choice;

import javafx.beans.property.SimpleBooleanProperty;
import model.Effect;

public abstract class Choice {
    protected final SimpleBooleanProperty choiceTaken =new SimpleBooleanProperty(false);
    private final String headline;
    private final String info;
    protected boolean accepted=false;


    public SimpleBooleanProperty getChoiceTakenProperty( ){
        return choiceTaken;
    }

    public Choice(String headline, String info){
        this.headline =headline;
        this.info =info;
    }


    public String getHeadline() {
        return headline;
    }

    public String getInfo() {
        return info;
    }

    public boolean isAccepted() {
        return accepted;
    }
}
