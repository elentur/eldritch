package model.effects;


import enums.SpendType;
import lombok.Getter;
import model.Effect;
import model.Item.Investigator;
import model.Item.Monster;

@Getter
public class Loose extends Effect {
    private final SpendType spendType;
    private final int value;
    private  Investigator investigator;
    private  Monster monster;

    public Loose(SpendType spendType, int value, Investigator investigator) {

        this.spendType = spendType;
        this.value = value;
        this.investigator = investigator;
    }
    public Loose(SpendType spendType, int value, Monster monster) {

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
}
