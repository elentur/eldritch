package model.effects;


import enums.EffectSelector;
import enums.EffectTyps;
import enums.SpendType;
import lombok.Getter;
import model.Effect;
import model.Item.Investigator;
import utils.ResourceUtil;

@Getter
public class GainClue extends Effect {
    private final SpendType spendType;
    private final int value;
    private final EffectSelector selector;
    private  Investigator investigator;

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


    }

    @Override
    public String getText() {
        if(spendType==null ||value ==0){
            return ResourceUtil.get("${gain}","effect"  ) + " " + ResourceUtil.get("${nothing}","effect"  )+".";
        }
        if(selector.equals(EffectSelector.THIS)){
            return ResourceUtil.get("${gain}","effect"  ) + " "+ selector.getText() + " " + spendType.getText() +"."  ;
        }else{
            return ResourceUtil.get("${gain}","effect"  ) + " "+ value + " " + selector.getText() + " " + spendType.getText() +"."  ;
        }

    }
}
