package model.effects;


import Service.GameService;
import enums.EffectTyps;
import lombok.Getter;
import model.Effect;
import model.Item.Investigator;
import utils.ResourceUtil;

@Getter
public class RetreatDoom extends Effect {
    private final int value;

    public RetreatDoom(  int value) {
        super(EffectTyps.RETREAT_DOOM);
        this.value=value;
    }

    @Override
    public void execute() {
        if(isExecuted()){
            return;
        }
        super.execute();
        if(!isAccepted()) return;
        GameService.getInstance().getDoomTrack().retreatDoom(value);
    }

    @Override
    public String getText() {


            return ResourceUtil.get("${retreat_doom}","effect"  ,value+"") ;


    }
}
