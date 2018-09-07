package model.effects;


import enums.EffectTyps;
import enums.SpendType;
import lombok.Getter;
import model.Effect;
import model.Item.Investigator;
import model.Item.Monster;
import utils.ResourceUtil;

@Getter
public class LooseOrGainHealthSanity extends Effect {
    private final SpendType spendType;
    private final int value;
    private  Investigator investigator;
    private  Monster monster;

    public LooseOrGainHealthSanity(SpendType spendType, int value, Investigator investigator) {
        super(EffectTyps.LOOSE_OR_GAIN_HEALTH_SANITY);
        this.spendType = spendType;
        this.value = value;
        this.investigator = investigator;
    }
    public LooseOrGainHealthSanity(SpendType spendType, int value, Monster monster) {
        super(EffectTyps.LOOSE_OR_GAIN_HEALTH_SANITY);
        this.spendType = spendType;
        this.value = value;
        this.monster = monster;
    }

    @Override
    public void execute() {
        super.execute();
        switch (spendType) {
            case HEALTH:
                if(investigator!= null) {
                    investigator.addHealth(value);
                }else if(monster!=null){
                    monster.addDamage(value);
                }
                break;
            case SANITY:
                investigator.addSanity(value);
                break;
            default:
                break;
        }

    }

    @Override
    public String getText() {
        if(spendType==null ||value ==0){
            return ResourceUtil.get("${loose}","effect" , ResourceUtil.get("${nothing}","effect"  ));
        }
        return ResourceUtil.get("${loose}","effect"  , value + " " + spendType.getText()) ;
    }
}
