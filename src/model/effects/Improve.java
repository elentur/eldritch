package model.effects;


import enums.EffectTyps;
import enums.TestType;
import lombok.Getter;
import model.Effect;
import model.Item.Investigator;
import utils.ResourceUtil;

@Getter
public class Improve extends Effect {
    private final int value;
    private final Investigator investigator;
    private final TestType testType;

    public Improve(TestType testType,int value, Investigator investigator) {
        super(EffectTyps.IMPROVE);
        this.value = value;
        this.testType =testType;
        this.investigator = investigator;
    }


    @Override
    public void execute() {
        super.execute();
        investigator.improve(testType,value);

    }

    @Override
    public String getText() {

        return ResourceUtil.get("${improve}","effect" ,testType.getSymbol(), value+"" );
    }
}
