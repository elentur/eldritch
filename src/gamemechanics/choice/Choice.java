package gamemechanics.choice;

import enums.ChoiceType;
import javafx.beans.property.SimpleBooleanProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class Choice {
    @Getter
    private final ChoiceType choiceType;
    protected final SimpleBooleanProperty choiceTaken =new SimpleBooleanProperty(false);
    private final String headline;
    private final String info;
    protected boolean accepted=false;
    private List<Function<Void, Boolean>> conditions;

    public SimpleBooleanProperty getChoiceTakenProperty( ){
        return choiceTaken;
    }

    public Choice(ChoiceType choiceType,String headline, String info){
        this.headline =headline;
        this.info =info;
        this.choiceType = choiceType;
        conditions = new ArrayList<>();
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

    public boolean checkConditions() {
        boolean isAllowed = true;
        for (Function<Void, Boolean> f : conditions) {
            isAllowed &= f.apply(null);
        }
        return isAllowed;

    }

    public void addCondition(Function<Void, Boolean> condition) {
        conditions.add(condition);
    }
}
