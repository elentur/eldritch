package model.Item.boni;

import enums.BonusType;
import enums.EventTimeType;
import enums.SituationType;
import enums.TestType;
import gamemechanics.CombatEncounter;
import gamemechanics.Encounter;
import lombok.Getter;
import lombok.Setter;
import model.Item.Item;
import model.Item.SpellConsequence;
import preparation.Preparation;
import utils.ResourceUtil;

import java.util.List;

@Getter
@Setter

public class ItemBonus_ChangeMonsterHorror extends ItemBonus {
    private int value;
    private BonusType bonusType = BonusType.CHANGE_MONSTER_HORROR;

    public ItemBonus_ChangeMonsterHorror(SituationType situation, int value,Item parentItem) {
        super(parentItem);
        this.situation = situation;
        this.eventTime = EventTimeType.BEFORE;
    }


    @Override
    public void execute(Encounter encounter) {
        if(!isActive())return;
        if(encounter instanceof CombatEncounter){
            CombatEncounter combatEncounter = (CombatEncounter) encounter;
            Preparation preparation = combatEncounter.getAttackPreparation();

        }
    }

    @Override
    public String getText() {
        return ResourceUtil.get("${switchSkill}",Bonus.class,situation.getText(),to.getText(), test.getText() );
    }

}
