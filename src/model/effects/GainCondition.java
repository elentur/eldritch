package model.effects;


import enums.ConditionType;
import enums.EffectTyps;
import lombok.Getter;
import lombok.extern.java.Log;
import model.Effect;
import model.Item.Investigator;

@Getter
@Log
public class GainCondition extends Effect {
    private final ConditionType conditionType;
    private final Investigator investigator;

    public GainCondition(ConditionType conditionType,Investigator investigator) {
        super(EffectTyps.GAIN_CONDITION);
        this.conditionType = conditionType;
        this.investigator = investigator;
    }


    @Override
    public void execute() {
        super.execute();
        switch (conditionType) {
            case DETAINED:

                break;

            default:
                break;
        }
        log.info(conditionType.toString() );
    }
}
