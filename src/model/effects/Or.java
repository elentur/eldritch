package model.effects;


import Service.GameService;
import Service.StringUtils;
import enums.EffectTyps;
import gamemechanics.choice.EffectChoice;
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
        if(isExecuted()){
            return;
        }
        super.execute();
        if(!isAccepted()) return;
        GameService.getInstance().addChoice(new EffectChoice(effects));
    }

    @Override
    public String getText() {
        if(effects== null || effects.isEmpty()){
            return ResourceUtil.get("${gain}","effect"  ) + " " + ResourceUtil.get("${nothing}","effect"  );
        }
        StringBuilder value = new StringBuilder( effects.get(0).getText().substring(0, effects.get(0).getText().length()-1)) ;
        for(int i =1; i < effects.size();i++){

            String text = effects.get(i).getText();
            if(StringUtils.isEmpty(text)){
                continue;
            }
            value.append(" ").append(ResourceUtil.get("${or}", "effect" )).append(" ").append(text, 0, text.length() - 1);
        }
        return value.toString()+".";
    }
}
