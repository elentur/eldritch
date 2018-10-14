package model.effects;


import enums.EffectTyps;
import enums.SpendType;
import lombok.Getter;
import model.Effect;
import model.Item.Investigator;
import utils.ResourceUtil;

@Getter
public class GainFocus extends Effect {
    private final SpendType spendType;
    private final int value;
    private Investigator investigator;

    public GainFocus(int value, Investigator investigator) {
        super(EffectTyps.GAIN_FOCUS);
        this.spendType = SpendType.FOCUS;
        this.value = value;
        this.investigator = investigator;
    }

    @Override
    public void execute() {
        super.execute();
        if(!isAccepted()) return;
        investigator.addFocus(value);

    }

    @Override
    public String getText() {
        if (spendType == null || value == 0) {
            return ResourceUtil.get("${gain}", "effect", investigator.getName(),ResourceUtil.get("${nothing}", "effect") );
        }
            return ResourceUtil.get("${gain}", "effect",investigator.getName(), value + " " + spendType.getText() );
    }
}
