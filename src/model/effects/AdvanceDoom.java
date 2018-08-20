package model.effects;


import enums.EffectSelector;
import enums.EffectTyps;
import lombok.Getter;
import model.Effect;
import model.Item.Investigator;
import utils.ResourceUtil;

@Getter
public class AdvanceDoom extends Effect {
    private final EffectSelector selector;
    private  Investigator investigator;

    public AdvanceDoom(EffectSelector selector, Investigator investigator) {
        super(EffectTyps.ADVANCE_DOOM);
        this.investigator = investigator;
        this.selector = selector;
    }

    @Override
    public void execute() {
        super.execute();


    }

    @Override
    public String getText() {

        if(selector.equals(EffectSelector.ANY)){
            return ResourceUtil.get("${advance_doom_any}","effect"  )   ;
        }else{
            return ResourceUtil.get("${advance_doom}","effect"  ) ;
        }

    }
}
