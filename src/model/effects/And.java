package model.effects;


import enums.EffectTyps;
import lombok.Getter;
import model.Effect;

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
    }
}
