package model.effects;


import enums.EffectTyps;
import enums.TestType;
import gamemechanics.choice.SkillChoice;
import lombok.Getter;
import lombok.Setter;
import model.Effect;
import model.Item.Investigator;
import utils.ResourceUtil;

@Getter
public class Improve extends Effect {
    private final int value;
    private final Investigator investigator;
    private TestType testType;
    @Setter
    private int number;

    public Improve(TestType testType, int value, Investigator investigator) {
        super(EffectTyps.IMPROVE);
        this.value = value;
        this.testType = testType;
        this.investigator = investigator;
    }

    public Improve(int number, int value, Investigator investigator) {
        super(EffectTyps.IMPROVE);
        this.value = value;
        this.number = number;
        this.investigator = investigator;
    }

    @Override
    public void init() {
        if (number > 0) {
            condition = new SkillChoice(number, investigator);
        }
        super.init();
    }

    @Override
    public void execute() {
        if (isExecuted()) {
            return;
        }
        super.execute();
        if (!isAccepted()) return;
        if (testType != null && !testType.equals(TestType.ALL) && !testType.equals(TestType.NONE)) {
            investigator.improve(testType, value);
        }

    }

    @Override
    public String getText() {
        if (testType != null) {
            return ResourceUtil.get("${improve}", "effect", investigator.getName(), testType.getSymbol(), value + "");
        }
        return ResourceUtil.get("${improve_choice}", "effect", investigator.getName(), number+"");
    }
}
