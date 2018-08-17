package gamemechanics.choice;


import enums.ChoiceType;
import enums.YesNo;
import model.Effect;

import java.util.List;

public class InformationChoice extends Choice{
    private final List<Effect> effectOnOK;
    private boolean value;

    public InformationChoice(String headline, String info, List<Effect> effectOnOK){
        super(ChoiceType.INFORMATION,headline,info);
        this.effectOnOK= effectOnOK;
    }

    public void setValue(boolean value){
        if(!choiceTaken.getValue()) {
            this.value = value;
            accepted=value;
            executeEffects(effectOnOK);
            choiceTaken.setValue(true);
        }
    }

    private void executeEffects(List<Effect> effects) {
        if(effects==null){
            return;
        }
        for(Effect effect:effects){
            effect.execute();
        }
    }

    public boolean getValue(){
        return value;
    }

}
