package gamemechanics.choice;


import enums.YesNo;
import model.Effect;

import java.util.List;

public class YesNoChoice extends Choice{
    private final List<Effect> effectOnYes;
    private final List<Effect> effectsOnNo;
    private YesNo value;

    public YesNoChoice(String headline, String info, List<Effect> effectOnYes, List<Effect> effectsOnNo){
        super(headline,info);
        this.effectOnYes= effectOnYes;
        this.effectsOnNo=effectsOnNo;
    }

    public void setValue(YesNo value){
        if(!choiceTaken.getValue()) {
            this.value = value;
            accepted=value.equals(YesNo.YES);
            if(value.equals(YesNo.YES)){
                executeEffects(effectOnYes);
            }else {
                executeEffects(effectsOnNo);
            }

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

    public YesNo getValue(){
        return value;
    }

}
