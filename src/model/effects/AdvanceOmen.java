package model.effects;

import Service.GameService;
import enums.EffectSelector;
import enums.EffectTyps;
import lombok.Getter;
import model.Effect;
import model.Item.Investigator;
import utils.ResourceUtil;
@Getter
public class AdvanceOmen extends Effect {
    private EffectSelector selector;
    private int value;
    public AdvanceOmen(EffectSelector selector, int value) {
        super(EffectTyps.ADVANCE_OMEN);
        this.selector = selector;
        this.value=value;
    }
    public AdvanceOmen( int value) {
        this(EffectSelector.THIS,value);
    }

    @Override
    public void execute() {
        super.execute();
        if(selector.equals(EffectSelector.ANY)){
            GameService.getInstance().getOmenTrack().setEditable(true);
        }else {
            GameService.getInstance().getOmenTrack().advanceOmen(value);
        }
    }

    @Override
    public String getText() {
        if(selector.equals(EffectSelector.ANY)){
            return ResourceUtil.get("${advance_omen_any}","effect"  ) ;
        }else{
            return ResourceUtil.get("${advance_omen}","effect" ,value+"" ) ;
        }
    }
}
