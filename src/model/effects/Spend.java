package model.effects;


import enums.EffectTyps;
import enums.SpendType;
import lombok.Getter;
import model.Effect;
import model.Item.Investigator;
import utils.ResourceUtil;

@Getter
public class Spend extends Effect {
    private final SpendType spendType;
    private final int value;
    private final Investigator investigator;

    public Spend(SpendType spendType, int value, Investigator investigator) {
        super(EffectTyps.SPEND);
        this.spendType = spendType;
        this.value = value;
        this.investigator = investigator;
    }


    @Override
    public void execute() {
        super.execute();
        switch (spendType) {
            case CLUE:
                break;
            case HEALTH:
                investigator.addHealth(-value);
                break;
            case SANITY:
                investigator.addSanity(-value);
                break;
            default:
                break;
        }

    }

    @Override
    public String getText() {
        if(spendType==null ||value ==0){
            return ResourceUtil.get("${spend}",investigator.getName(),"effect" , ResourceUtil.get("${nothing}","effect"  ));
        }
        return ResourceUtil.get("${spend}",investigator.getName(),"effect", value + " " + spendType.getText()) ;
    }
}
