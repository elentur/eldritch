package model.effects;


import enums.SpendType;
import model.Effect;
import model.Item.Investigator;

public class Spend extends Effect {
    private final SpendType spendType;
    private final int value;
    private final Investigator investigator;

    public Spend(SpendType spendType, int value, Investigator investigator) {

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
}
