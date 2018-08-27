package model.Item.boni;

import enums.BonusType;
import enums.EventTimeType;
import enums.SituationType;
import enums.TestType;
import gamemechanics.encounter.CombatEncounter;
import gamemechanics.encounter.Encounter;
import lombok.Getter;
import lombok.Setter;
import model.Item.Bonus;
import model.Item.Item;
import model.Item.ItemBonus;
import model.Item.Monster;
import utils.ResourceUtil;

@Getter
@Setter

public class ItemBonus_ChangeMonsterHorror extends ItemBonus {
    private int value;
    private BonusType bonusType = BonusType.CHANGE_MONSTER_HORROR;

    public ItemBonus_ChangeMonsterHorror(int value, SituationType situation, Item parentItem) {
        super(parentItem);
        this.situation = situation;
        this.eventTime = EventTimeType.BEFORE;
        this.value = value;
        this.test = TestType.WILL;
        this.passive = true;

    }


    @Override
    public void execute(Encounter encounter) {

        if (!isExecutable()) {
            return;
        }
        if (encounter instanceof CombatEncounter) {
            CombatEncounter combatEncounter = (CombatEncounter) encounter;
            Monster m = combatEncounter.getActiveMonster();
            m.setHorror(m.getHorror() - 1 < 1 ? 1 : m.getHorror() - 1);


        }
    }

    @Override
    public String getText() {
        return ResourceUtil.get("${changeMonsterHorror}", Bonus.class, value + "", situation.getText());
    }

}
