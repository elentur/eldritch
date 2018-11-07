package model.effects;


import Service.GameService;
import enums.EffectTyps;
import gamemechanics.Test;
import lombok.Getter;
import model.Effect;
import utils.ResourceUtil;

import java.util.Arrays;
import java.util.List;

@Getter
public class StartTest extends Effect {
    private final Test test;

    public StartTest(Test test) {
        super(EffectTyps.START_TEST);
        this.test = test;
    }


    @Override
    public void execute() {
        if(isExecuted()){
            return;
        }
        init();
        if(!isAccepted()) return;
        GameService.getInstance().addTest(test);


    }

    @Override
    public String getText() {

        return test.getStartText();
    }
}
