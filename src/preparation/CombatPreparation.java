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
import model.Item.Bonus;
import model.Item.Item;

@Getter
@Setter
public class CombatPreparation implements Preparation {

    private TestTyp testTyp;
    private SituationTyp situation;
    private Investigator investigator;
    private int modification;
    private ItemContainer<Item> bonusItems;

    BonusContainer<Bonus> boni;

    private GameService game;

    public CombatPreparation(Investigator investigator) {
        this.testTyp = TestTyp.STRENGTH;
        this.situation = SituationTyp.COMBAT_ENCOUNTER;
        this.modification = 0;
        this.investigator = investigator;
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
    public int getModifiedSkill() {
        return 0;
    }


    public BonusContainer<Bonus> getBoni(EventTimeType eventTime) {

        return boni.getAllByEventTime(eventTime);
    }
}
