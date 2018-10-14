package model.effects;


import Service.GameService;
import enums.EffectSelector;
import enums.EffectTyps;
import enums.SpendType;
import lombok.Getter;
import model.Effect;
import model.Item.Investigator;
import model.Item.token.ClueToken;
import utils.ResourceUtil;

@Getter
public class GainClue extends Effect {
    private final SpendType spendType;
    private final int value;
    private final EffectSelector selector;
    private Investigator investigator;

    public GainClue(EffectSelector selector, int value, Investigator investigator) {
        super(EffectTyps.GAIN_CLUE);
        this.spendType = SpendType.CLUE;
        this.value = value;
        this.investigator = investigator;
        this.selector = selector;
    }

    @Override
    public void execute() {
        super.execute();
        if(!isAccepted()) return;
        switch (selector) {
            case THIS:
                investigator.addClue((ClueToken) GameService.getInstance().getFieldOfInvestigator(investigator).removeClue(null));
                break;
            default:
                for (int i = 0; i < value; i++) {
                    investigator.addClue(GameService.getInstance().getClueTokens().draw());
                }
                break;
        }


    }

    @Override
    public String getText() {
        if (spendType == null || value == 0) {
            return ResourceUtil.get("${gain}", "effect",investigator.getName(),ResourceUtil.get("${nothing}", "effect") );
        }
        if (selector.equals(EffectSelector.THIS)) {
            return ResourceUtil.get("${gain}", "effect",investigator.getName(), selector.getText() + " " + spendType.getText());
        } else {
            return ResourceUtil.get("${gain}", "effect", investigator.getName(),value + " " + selector.getText() + " " + spendType.getText() );
        }

    }
}
