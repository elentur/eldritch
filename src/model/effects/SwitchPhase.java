package model.effects;


import Service.GameService;
import enums.EffectTyps;
import lombok.Getter;
import model.Effect;
import utils.ResourceUtil;

@Getter
public class SwitchPhase extends Effect {


    public SwitchPhase() {
      super(EffectTyps.SWITCH_PHASE);
    }

    @Override
    public void execute() {
        if(isExecuted()){
            return;
        }
        super.execute();
        if(!isAccepted()) return;
        GameService.getInstance().getPhases().switchPhase();


    }

    @Override
    public String getText() {
            return ResourceUtil.get("${switch_phase}","effect"  ) ;


    }
}
