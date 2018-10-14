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
    public void init() {
        switch (spendType) {
            case CLUE:
                if (value> investigator.getClues().size()) {
                  setAccepted(false);
                  return;
                }
                break;
            case HEALTH:
                if (value> investigator.getActualHealth()) {
                    setAccepted(false);
                    return;
                }
                break;
            case SANITY:
                if (value> investigator.getActualSanity()) {
                    setAccepted(false);
                    return;
                }
                break;
            default:
                break;
        }
        super.init();
    }

    @Override
    public void execute() {
        super.execute();
        if(!isAccepted()) return;
        if(isAccepted()) {
            switch (spendType) {
                case CLUE:
                        for (int i = 0; i < value; i++) {
                            investigator.getClues().remove(0);
                        }
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

    }

    @Override
    public String getText() {
        if(spendType==null ||value ==0){
            return ResourceUtil.get("${spend}","effect",investigator.getName() , ResourceUtil.get("${nothing}","effect"  ));
        }
        return ResourceUtil.get("${spend}","effect",investigator.getName(), value + " " + spendType.getText()) ;
    }
}
