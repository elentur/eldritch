package model.effects;


import enums.ConditionType;
import enums.EffectSelector;
import enums.EffectTyps;
import gamemechanics.choice.InvestigatorChoice;
import lombok.Getter;
import lombok.extern.java.Log;
import model.Effect;
import model.Item.Investigator;
import utils.ResourceUtil;

@Getter
@Log
public class GainCondition extends Effect {
    private final ConditionType conditionType;
    private Investigator investigator;

    public GainCondition(ConditionType conditionType, Investigator investigator) {
        super(EffectTyps.GAIN_CONDITION);
        this.conditionType = conditionType;
        this.investigator = investigator;
    }

    public GainCondition(ConditionType conditionType, InvestigatorChoice choice) {
        super(EffectTyps.GAIN_CONDITION);
        this.conditionType = conditionType;
        this.condition = choice;
    }


    @Override
    public void execute() {
        if(isExecuted()){
            return;
        }
        super.execute();
        if (!isAccepted()) return;
        switch (conditionType) {
            case DETAINED:

                break;

            default:
                break;
        }
        log.info(conditionType.toString());
    }

    @Override
    public String getText() {
        if (conditionType == null) {
            return ResourceUtil.get("${gain}", "effect", investigator.getName(), ResourceUtil.get("${nothing}", "effect"));
        }
        if(investigator==null){
            return ResourceUtil.get("${gain}", "effect", EffectSelector.ANY.getText(), conditionType.getText() + " " + ResourceUtil.get("${condition}", "effect"));

        }
        return ResourceUtil.get("${gain}", "effect", investigator.getName(), conditionType.getText() + " " + ResourceUtil.get("${condition}", "effect"));

    }

    @Override
    public void init() {
        super.init();
        if (condition != null) {

            investigator = ((InvestigatorChoice) condition).getSelectedInvs().get(0);

        }
    }


}
