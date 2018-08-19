package model.effects;


import enums.EffectTyps;
import lombok.Getter;
import model.Effect;

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
}
