package gamemechanics.choice;

import enums.ChoiceType;
import javafx.beans.property.SimpleBooleanProperty;
import lombok.Getter;
import model.Effect;

public abstract class Choice {
    @Getter
    private final ChoiceType choiceType;
    protected final SimpleBooleanProperty choiceTaken =new SimpleBooleanProperty(false);
    private final String headline;
    private final String info;
    protected boolean accepted=false;


    public SimpleBooleanProperty getChoiceTakenProperty( ){
        return choiceTaken;
    }

    public Choice(ChoiceType choiceType,String headline, String info){
        this.headline =headline;
        this.info =info;
        this.choiceType = choiceType;
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
