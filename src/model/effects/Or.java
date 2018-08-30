package model.effects;


import enums.EffectTyps;
import lombok.Getter;
import model.Effect;
import utils.ResourceUtil;

import java.util.Arrays;
import java.util.List;

@Getter
public class Or extends Effect {
    private final List<Effect> effects;

    public Or(Effect... effects) {
        super(EffectTyps.OR);
        this.effects = Arrays.asList(effects);
    }


    @Override
    public void execute() {
        super.execute();
    }

    @Override
    public String getText() {
        if(effects== null || effects.isEmpty()){
            return ResourceUtil.get("${gain}","effect"  ) + " " + ResourceUtil.get("${nothing}","effect"  );
        }
        StringBuilder value = new StringBuilder( effects.get(0).getText().substring(0, effects.get(0).getText().length()-1)) ;
        for(int i =1; i < effects.size();i++){
            value.append(" ").append(ResourceUtil.get("${or}", "effect" )).append(" ").append(effects.get(i).getText(), 0, effects.get(i).getText().length()-1);
        }
        return value.toString()+".";
    }
}
