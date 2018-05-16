package preparation;

import Service.GameService;
import container.BonusContainer;
import container.ItemContainer;
import enums.EventTimeType;
import enums.SituationTyp;
import enums.TestTyp;
import lombok.Getter;
import lombok.Setter;
import model.Investigator;
import model.Item.Item;
import model.Item.ItemBonus;
import model.Item.ItemBonus_GainDice;
import model.Monster;

@Getter
@Setter
public class CombatPreparation implements Preparation {

    private TestTyp testTyp;
    private SituationTyp situation;
    private Investigator investigator;
    private Monster monster;
    private int modification;
    private ItemContainer<Item> bonusItems;
    private ItemBonus_GainDice gainDiceBonus = ItemBonus_GainDice.EMPTY;

    BonusContainer<ItemBonus> boni;

    private GameService game;

    public CombatPreparation(TestTyp testTyp, Investigator investigator, Monster monster) {
        this.testTyp = testTyp;
        this.situation = SituationTyp.COMBAT_ENCOUNTER;
        this.modification = monster.getStrengthTest();
        this.investigator = investigator;
        this.monster = monster;
        game = GameService.getInstance();
        calculateBoni();

    }

    @Override
    public TestTyp getTestTyp() {
        return testTyp;
    }

    @Override
    public int getModification() {
        return modification;
    }

    @Override
    public int getModificationForSkillTest() {

        int wValue = gainDiceBonus.getValue();
        return modification+wValue;

    }

    @Override
    public int getNumberOfDice() {
        int value = getModificationForSkillTest()+ investigator.getSkill(testTyp);
        return value<1?1:value;
    }


    public BonusContainer<ItemBonus> getBoni(EventTimeType eventTime) {
        calculateBoni();
        return boni.getAllByEventTime(eventTime);
    }

    @Override
    public int getBonusModification() {
        return gainDiceBonus.getValue();
    }


    @Override
    public void calculateBoni() {
        bonusItems = investigator.getInventory().getItemsWidthSituationTyp(situation);
        boni =  bonusItems.getBoniWithSituationTyp(situation,testTyp);
        gainDiceBonus = boni.getStrongestGainDiceBonus(testTyp);
    }
}
