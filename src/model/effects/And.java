package model.effects;


import enums.EffectTyps;
import lombok.Getter;
import model.Effect;
import utils.ResourceUtil;

import java.util.Arrays;
import java.util.List;

@Getter
public class And extends Effect {
    private final List<Effect> effects;

    public And(Effect... effects) {
        super(EffectTyps.AND);
        this.effects = Arrays.asList(effects);
    }


    @Override
    public void execute() {
        super.execute();
        for (Effect e: effects){
            e.execute();
        }
    }

    @Override
    public String getText() {
        if(effects== null || effects.isEmpty()){
            return ResourceUtil.get("${gain}","effect" ) + " " + ResourceUtil.get("${nothing}","effect"  );
        }
        StringBuilder value = new StringBuilder(effects.get(0).getText()) ;
        for(int i =1; i < effects.size();i++){
            value.append(" ").append(ResourceUtil.get("${and}", "effect" )).append(" ").append(effects.get(i).getText());
        }
        return value.toString()+".";
    }
}
