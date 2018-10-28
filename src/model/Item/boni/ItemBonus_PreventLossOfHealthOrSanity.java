package model.Item.boni;

import Service.GameService;
import enums.*;
import gamemechanics.Test;
import gamemechanics.choice.YesNoChoice;
import gamemechanics.encounter.Encounter;
import lombok.Getter;
import lombok.Setter;
import model.Item.*;
import utils.ResourceUtil;


@Getter
@Setter
public class ItemBonus_PreventLossOfHealthOrSanity extends ItemBonus {

    public static final ItemBonus_PreventLossOfHealthOrSanity EMPTY = new ItemBonus_PreventLossOfHealthOrSanity(0, SpendType.NONE, SituationType.NONE, RangeType.NONE, null, null);
    private int value;
    private BonusType bonusType = BonusType.ADDITIONAL_DICE;
    private int preventedValue;
    private SpendType spendType;
    private Test testEncounter;
    private Investigator investigator;
    private int damage;

    public ItemBonus_PreventLossOfHealthOrSanity(int value, SpendType spendType, SituationType situation, RangeType range, Test test, Item parentItem) {
        super(parentItem);
        this.value = value;
        this.situation = situation;
        this.range = range;
        this.testEncounter = test;
        this.eventTime = EventTimeType.BEFORE;
        this.spendType = spendType;
    }

    @Override
    public void execute(Encounter encounter) {
        if (!isExecutable() || !checkConditions(encounter)) {
            return;
        }

        int tempValue = value;

        if ((encounter != null && encounter.getSituationType().equalsWithAll(situation)) || (encounter == null && situation.equals(SituationType.ALL))) {
            YesNoChoice choice = new YesNoChoice(parentItem.getName(), getText(), null, null);
            GameService.getInstance().addChoice(choice);

            if (choice.isAccepted()) {
                super.execute(encounter);
                boolean isAccepted = true;
                if (testEncounter != null) {
                    GameService.getInstance().addTest(testEncounter);
                    isAccepted = testEncounter.getResult().isSuccess();

                }
                if (isAccepted) {
                    preventedValue = value;
                }

            }
        }
        if (isPerRound()) {
            setUsable(false);
        }
        value = tempValue;


    }

    private boolean checkConditions(Encounter encounter) {

        Investigator owner = GameService.getInstance().getInvestigatorForItem(parentItem);
        if(owner== null){
            return false;
        }
        boolean isChecked = encounter == null || encounter.getSituationType().equalsWithAll(situation);
        switch (range){
            case ALL:
                break;
            case SELF:
                isChecked&= owner.equals(investigator);
                break;
            case LOCAL:
                isChecked&= GameService.getInstance().getFieldOfInvestigator(owner).equals(
                        GameService.getInstance().getFieldOfInvestigator(investigator)
                );
            break;
            default:
                return false;
        }
        return isChecked;
    }

    @Override
    public String getText() {
        return ResourceUtil.get("${prevent_loss_of_health_or_sanity}", Bonus.class, value + "", investigator.getName(), damage + "", spendType.getText());
    }

    public void setInvestigator(Investigator investigator) {
        this.investigator = investigator;
    }

    public void setDamage(int damage) {
        this.damage = Math.abs(damage);
    }
}