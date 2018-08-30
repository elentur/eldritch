package model.effects;


import enums.EffectTyps;
import lombok.Getter;
import model.Effect;
import model.Item.Investigator;
import utils.ResourceUtil;

@Getter
public class Move extends Effect {
    private final int value;
    private final Investigator investigator;

    public Move(int value, Investigator investigator) {
        super(EffectTyps.MOVE);
        this.value = value;
        this.investigator = investigator;
    }


    @Override
    public void execute() {
        super.execute();


    }

    @Override
    public String getText() {

        return ResourceUtil.get("${move}","effect" ,value+"" );
    }
}
