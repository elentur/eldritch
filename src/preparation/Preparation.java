package preparation;

import Service.GameService;
import container.BonusContainer;
import container.ItemContainer;
import enums.EventTimeType;
import enums.SituationType;
import enums.TestType;
import gamemechanics.encounter.Encounter;
import lombok.Getter;
import lombok.Setter;
import model.Item.Investigator;
import model.Item.*;
import model.Item.Bonus;
import model.Item.boni.ItemBonus_AdditionalDice;
import model.Item.boni.ItemBonus_GainDice;

import java.util.function.Function;

@Getter
@Setter
public class Preparation {

    private final Encounter encounter;
    protected TestType testTyp;
    protected SituationType situation;
    protected Investigator investigator;
    protected BonusContainer<ItemBonus_AdditionalDice> additionalDiceBoni;
    protected ItemBonus_GainDice gainDiceBonus = ItemBonus_GainDice.EMPTY;
    private int modification;

    BonusContainer<Bonus> boni;
    BonusContainer<Bonus> usedBoni;

    private GameService game;



    public Preparation(TestType testTyp, Investigator investigator, SituationType situation, Encounter encounter) {
        this.testTyp = testTyp;
        this.situation = situation;
        this.investigator = investigator;
        game = GameService.getInstance();
        this.encounter =encounter;
        usedBoni = new BonusContainer<>();
        setModification(encounter.getMod()[encounter.getEncounterPart()]);
        calculateBoni();

    }


    public TestType getTestTyp() {
        return testTyp;
    }




    public int getModificationForSkillTest() {

        return modification+getGainDiceBonus().getValue()+getAdditionDiceBoniSum();

    }


    public int getNumberOfDice() {
        int value = getModificationForSkillTest()+ investigator.getSkill(testTyp);
        value=value<1?1:value;
        value=value>12?12:value;
        return value;
    }


    public BonusContainer<Bonus> getBoni(EventTimeType eventTime) {
     //   calculateBoni();
        return boni.getAllByEventTime(eventTime);
    }


    public ItemBonus_GainDice getGainDiceBonus() {
        return gainDiceBonus;
    }


    public int getAdditionDiceBoniSum() {
   //     calculateBoni();
        return additionalDiceBoni.stream().mapToInt(ItemBonus_AdditionalDice::getValue).sum();
    }


    public void calculateBoni() {
        Function<Bonus,Boolean> filter = bonus -> bonus.getSituation().equalsWithAll(situation)
                && bonus.getTest().equalsWithAll(testTyp)
                && bonus.getField().equalsWithAll(game.getFieldOfInvestigator(investigator).getType())
                && bonus.isActivated()
                && bonus.isUsable()
                &&! bonus.isPassive();
        ItemContainer<Item> bonusItems = game.getBonusItemsforInvestigator(investigator);
        boni =  bonusItems.getBoniWithFilter(filter);
        boni.removeAll(usedBoni);
        additionalDiceBoni = boni.getAdditionalDiceBoni(filter);
        gainDiceBonus = boni.getStrongestGainDiceBonus(filter);
    }

    public void markBoniAsUsed(Bonus bonus){
    boni.remove(bonus);
    usedBoni.add(bonus);
    }
}
