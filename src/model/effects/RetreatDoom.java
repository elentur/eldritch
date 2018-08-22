package model.effects;


import enums.EffectSelector;
import enums.EffectTyps;
import lombok.Getter;
import model.Effect;
import model.Item.Investigator;
import utils.ResourceUtil;

@Getter
public class RetreatDoom extends Effect {
    private final int value;
    private  Investigator investigator;

    public RetreatDoom(  int value,Investigator investigator) {
        super(EffectTyps.ADVANCE_DOOM);
        this.investigator = investigator;
        this.value=value;
    }

    @Override
    public void execute() {
        super.execute();


    }

    @Override
    public String getText() {


            return ResourceUtil.get("${retreat_doom}","effect"  ,value+"") ;


    }
}
