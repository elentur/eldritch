package gamemechanics.choice;


import enums.ChoiceType;
import lombok.Getter;
import model.Effect;
import utils.ResourceUtil;

import java.util.List;

public class EffectChoice extends Choice{
    @Getter
    private final List<Effect> effects;

    public EffectChoice(List<Effect> effects){
        super(ChoiceType.EFFECT, ResourceUtil.get("${effect_choice}","ui"),"");
      this.effects=effects;
    }



    private void executeEffects(List<Effect> effects) {
        if(effects==null){
            return;
        }
        for(Effect effect:effects){
            effect.execute();
        }
    }

}
