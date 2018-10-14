package model.effects;


import Service.GameService;
import enums.EffectTyps;
import lombok.Getter;
import model.Effect;
import utils.ResourceUtil;

import java.util.concurrent.Callable;

@Getter
public class NextInvestigator extends Effect {

    private final Callable<Boolean> callableCondition;

    public NextInvestigator() {
        this(null);
    }

    public NextInvestigator(Callable<Boolean> callableCondition) {
        super(EffectTyps.NEXT_INVESTIGATOR);
        this.callableCondition = callableCondition;
    }

    @Override
    public void execute() {
        super.execute();
        if(!isAccepted()) return;
        try {

            if (callableCondition == null || callableCondition.call()) {
                GameService.getInstance().setActiveInvestigator();
            }
        } catch (Exception e) {

        }

    }

    @Override
    public String getText() {


        return ResourceUtil.get("${next_Investigator}", "effect");


    }
}
