package model.effects;


import enums.EffectTyps;
import lombok.Getter;
import model.Effect;

@Getter
public class NullEffect extends Effect {


    public NullEffect() {
        super(EffectTyps.NULL);

    }


    @Override
    public void execute() {

    }
}
