package model.effects;

import enums.EffectSelector;
import enums.EffectTyps;
import model.Effect;
import model.Item.Investigator;
import utils.ResourceUtil;

public class RetreatOmen extends Effect {
    private  Investigator investigator;
    private EffectSelector selector;
    private int value;
    public RetreatOmen(EffectSelector selector, int value, Investigator investigator) {
        super(EffectTyps.ADVANCE_DOOM);
        this.investigator = investigator;
        this.selector = selector;
        this.value=value;
    }

    @Override
    public void execute() {
        super.execute();


    }

    @Override
    public String getText() {

            return ResourceUtil.get("${retreat_omen}","effect" ,value+"" ) ;

    }
}
