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

public class ItemBonus_ChangeMonsterDamage extends ItemBonus {
    private int value;
    private BonusType bonusType = BonusType.CHANGE_MONSTER_DAMAGE;

    public ItemBonus_ChangeMonsterDamage(int value, SituationType situation, Item parentItem) {
        super(parentItem);
        this.situation = situation;
        this.eventTime = EventTimeType.BEFORE;
        this.value = value;
        this.test = TestType.STRENGTH;
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
            m.setDamage(m.getDamage() - 1 < 1 ? 1 : m.getDamage() - 1);

            super.execute(encounter);
        }

    }

    @Override
    public String getText() {
        return ResourceUtil.get("${change_monster_damage}", Bonus.class, value + "", situation.getText());
    }

}
