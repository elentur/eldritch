package model.Item.boni;

import Service.GameService;
import enums.*;
import gamemechanics.Test;
import gamemechanics.choice.YesNoChoice;
import gamemechanics.encounter.Encounter;
import lombok.Getter;
import lombok.Setter;
import model.Item.Bonus;
import model.Item.Item;
import model.Item.ItemBonus;
import model.Item.Spell;
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

    public ItemBonus_PreventLossOfHealthOrSanity(int value, SpendType spendType, SituationType situation, RangeType range, Test test, Item parentItem) {
        super(parentItem);
        this.value = value;
        this.situation = situation;
        this.range = range;
        this.testEncounter=test;
        this.eventTime = EventTimeType.BEFORE;
        this.spendType = spendType;
    }

    @Override
    public void execute(Encounter encounter) {
        if (!isExecutable()) {
            return;
        }
        int tempValue = value;
        if ((encounter != null && encounter.getSituationType().equalsWithAll(situation)) || (encounter == null && situation.equals(SituationType.ALL))) {
            YesNoChoice choice = new YesNoChoice(parentItem.getName(), getText(), null, null);
            GameService.getInstance().addChoice(choice);

            if (choice.isAccepted()) {
                boolean isAccepted = true;
                if (testEncounter != null) {
                    GameService.getInstance().addTest(testEncounter);
                    isAccepted = testEncounter.getResult().isSuccess();
                    if (parentItem.getItemType().equals(ItemType.SPELL)) {
                        GameService.getInstance().addUsedSpell((Spell) parentItem);
                    }
                }
                if (isAccepted) {
                    preventedValue = value;
                }

            }
        }
        if (isPerRound()) {
            setUsable(false);
        }
        value=tempValue;
        super.execute(encounter);

    }

    @Override
    public String getText() {
        return ResourceUtil.get("${prevent_loss_of_health_or_sanity}", Bonus.class, value + "", spendType.getText());
    }

}
