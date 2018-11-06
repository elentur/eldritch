package model.effects;


import enums.EffectTyps;
import lombok.Getter;
import lombok.extern.java.Log;
import model.Effect;
import model.Item.Investigator;
import utils.ResourceUtil;

@Getter
@Log
public class BecomeDelayed extends Effect {
    private final Investigator investigator;

    public BecomeDelayed( Investigator investigator) {
        super(EffectTyps.BECOME_DELAYED);
        this.investigator = investigator;
    }


    @Override
    public void execute() {
        if(isExecuted()){
            return;
        }
        super.execute();
        if(!isAccepted()) return;
        log.info("Delayed" );
    }

    @Override
    public String getText() {

        return ResourceUtil.get("${become}","effect" ,investigator.getName(),ResourceUtil.get("${delayed}","effect"  ));

    }
}
