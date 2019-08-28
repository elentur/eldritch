package model.effects;


import Service.GameService;
import Service.StringUtils;
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
        if(isExecuted()){
            return;
        }
        init();
        if(!isAccepted()) return;
        for (Effect effect : effects) {
            GameService.getInstance().addEffectAfter(effect);
        }


    }

    @Override
    public String getText() {
        if (effects == null || effects.isEmpty()) {
            return ResourceUtil.get("${gain}", "effect") + " " + ResourceUtil.get("${nothing}", "effect");
        }
        StringBuilder value = new StringBuilder(effects.get(0).getText().substring(0, effects.get(0).getText().length() - 1));
        for (int i = 1; i < effects.size(); i++) {
            String text = effects.get(i).getText();
            if(StringUtils.isEmpty(text)){
                continue;
            }
            value.append(" ").append(ResourceUtil.get("${and}", "effect")).append(" ").append(text, 0, text.length() - 1);
        }
        return value.toString() + ".";
    }
}
