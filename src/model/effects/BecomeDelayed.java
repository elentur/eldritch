package model.effects;


import enums.EffectTyps;
import lombok.Getter;
import lombok.extern.java.Log;
import model.Effect;
import model.Item.Investigator;

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
        super.execute();

        log.info("Delayed" );
    }
}
