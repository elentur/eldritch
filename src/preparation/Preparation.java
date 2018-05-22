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
import model.Item.*;

import java.util.function.Function;

@Getter
@Setter
public class Preparation {

    protected TestTyp testTyp;
    protected SituationTyp situation;
    protected Investigator investigator;
    protected BonusContainer<ItemBonus_AdditionalDice> additionalDiceBoni;
    protected ItemBonus_GainDice gainDiceBonus = ItemBonus_GainDice.EMPTY;

    BonusContainer<Bonus> boni;

    private GameService game;



    Preparation(TestTyp testTyp, Investigator investigator,SituationTyp situation) {
        this.testTyp = testTyp;
        this.situation = situation;
        this.investigator = investigator;
        game = GameService.getInstance();
        calculateBoni();

    }


    public TestTyp getTestTyp() {
        return testTyp;
    }




    public int getModificationForSkillTest() {
        return getGainDiceBonus().getValue()+getAdditionDiceBoniSum();

    }


    public int getNumberOfDice() {
        int value = getModificationForSkillTest()+ investigator.getSkill(testTyp);
        value=value<1?1:value;
        value=value>12?12:value;
        return value;
    }


    public BonusContainer<Bonus> getBoni(EventTimeType eventTime) {
        calculateBoni();
        return boni.getAllByEventTime(eventTime);
    }


    public ItemBonus_GainDice getGainDiceBonus() {
        return gainDiceBonus;
    }


    public int getAdditionDiceBoniSum() {
        return additionalDiceBoni.stream().mapToInt(ItemBonus_AdditionalDice::getValue).sum();
    }


    public void calculateBoni() {
        Function<Bonus,Boolean> filter = bonus -> bonus.getSituation().equalsWithAll(situation)
                &&bonus.getTest().equalsWithAll(testTyp)
                && bonus.getField().equalsWithAll(game.getFieldOfInvestigator(investigator).getType())
                && bonus.isActive();
        ItemContainer<Item> bonusItems = game.getBonusItemsforInvestigator(investigator);
        boni =  bonusItems.getBoniWithFilter(filter);
        boni.addAll( game.getInvestigatorBoni(investigator));
        additionalDiceBoni = boni.getAdditionalDiceBoni(filter);
        gainDiceBonus = boni.getStrongestGainDiceBonus(filter);
    }
}
