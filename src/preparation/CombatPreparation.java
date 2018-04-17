package preparation;

import Service.GameService;
import container.ItemContainer;
import enums.EventTimeType;
import enums.SituationTyp;
import enums.TestTyp;
import model.Investigator;
import model.Item.Asset;
import model.Item.Item;

public class CombatPreparation implements Preparation {

    private TestTyp testTyp;
    private SituationTyp situation;
    private Investigator investigator;
    private int modification;
    private ItemContainer<Item> bonusItems;

    private GameService game;

    public CombatPreparation(Investigator investigator) {
        this.testTyp = TestTyp.STRENGTH;
        this.situation = SituationTyp.COMBAT_ENCOUNTER;
        this.modification = 0;
        this.investigator = investigator;
        game = GameService.getInstance();
        bonusItems = investigator.getInventory().getItemsWidthSituationTyp(situation);
    }

    @Override
    public TestTyp getTestTyp() {
        return testTyp;
    }

    @Override
    public int getModification() {
        return modification;
    }

    public ItemContainer<Item> getBonusItems() {

        return bonusItems;
    }

    public ItemContainer<Item> getBonusItems(EventTimeType eventTime) {

        return bonusItems;
    }
}
