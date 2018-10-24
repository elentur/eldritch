package model.effects;

import Service.GameService;
import enums.EffectSelector;
import enums.EffectTyps;
import lombok.Getter;
import model.Effect;
import model.Item.Investigator;
import utils.ResourceUtil;
@Getter
public class RetreatOmen extends Effect {
    private  Investigator investigator;
    private EffectSelector selector;
    private int value;
    public RetreatOmen(EffectSelector selector, int value) {
        super(EffectTyps.RETREAT_OMEN);
        this.selector = selector;
        this.value=value;
    }

    @Override
    public void execute() {
        if(isExecuted()){
            return;
        }
        super.execute();
        if(!isAccepted()) return;
        GameService.getInstance().getOmenTrack().retreatOmen(value);

    }

    @Override
    public String getText() {
            return ResourceUtil.get("${retreat_omen}","effect" ,value+"" ) ;

    }
}
