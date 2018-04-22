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

    BonusContainer<ItemBonus> boni;

    private GameService game;

    public CombatPreparation(Investigator investigator, Monster monster) {
        this.testTyp = TestTyp.STRENGTH;
        this.situation = SituationTyp.COMBAT_ENCOUNTER;
        this.modification =  monster.getStrengthTest();
        this.investigator = investigator;
        this.monster=monster;
        game = GameService.getInstance();
        bonusItems = investigator.getInventory().getItemsWidthSituationTyp(situation);
        boni =  bonusItems.getBoniWithSituationTyp(situation);

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
    public int getModifiedSkill(){
        int value=   investigator.getSkill(testTyp)+modification;
        return value<1?1:value;
    }


    public BonusContainer<ItemBonus> getBoni(EventTimeType eventTime) {

        return boni.getAllByEventTime(eventTime);
    }
}
