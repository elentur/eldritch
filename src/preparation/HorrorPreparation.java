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
public class HorrorPreparation implements Preparation {

    private TestTyp testTyp;
    private SituationTyp situation;
    private Investigator investigator;
    private Monster monster;
    private int modification;
    private ItemContainer<Item> bonusItems;
    ItemBonus_GainDice itemBonus = ItemBonus_GainDice.EMPTY;

    BonusContainer<ItemBonus> boni;

    private GameService game;

    public HorrorPreparation(Investigator investigator, Monster monster) {
        this.testTyp = TestTyp.WILL;
        this.situation = SituationTyp.COMBAT_ENCOUNTER;
        this.modification = monster.getWillTest();
        this.investigator = investigator;
        this.monster = monster;
        game = GameService.getInstance();
        bonusItems = investigator.getInventory().getItemsWidthSituationTyp(situation);
        boni =  bonusItems.getBoniWithSituationTyp(situation,testTyp);

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
    public int getModifiedSkill() {
        itemBonus = boni.getStrongestWeaponBoni(testTyp);
        int wValue = itemBonus.getValue();
        int value=   investigator.getSkill(testTyp)+modification+wValue;
        System.out.println(wValue + "   "+ investigator.getSkill(testTyp)+"   "+ modification);
        return value<1?1:value;
    }


    public BonusContainer<ItemBonus> getBoni(EventTimeType eventTime) {

        return boni.getAllByEventTime(eventTime);
    }

    @Override
    public int getBonusModification() {
        return getItemBonus().getValue();
    }
}
