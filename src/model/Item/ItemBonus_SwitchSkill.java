package model.Item;

import enums.BonusType;
import enums.EventTimeType;
import enums.SituationType;
import enums.TestType;
import gamemechanics.CombatEncounter;
import gamemechanics.Encounter;
import lombok.Getter;
import lombok.Setter;
import preparation.Preparation;
import utils.ResourceUtil;

import java.util.List;

@Getter
@Setter

public class ItemBonus_SwitchSkill extends ItemBonus {
    private TestType to;
    private BonusType bonusType = BonusType.SWITCH_SKILL;

    public ItemBonus_SwitchSkill(SituationType situation, TestType test, TestType to, List<SpellConsequence> consequence,Item parentItem) {
        super(parentItem);
        this.situation = situation;
        this.consequence = consequence;
        this.test = test;
        this.to = to;
        this.eventTime = EventTimeType.BEFORE;
    }


    @Override
    public void execute(Encounter encounter) {
        if(!isActive())return;
        if(encounter instanceof CombatEncounter){
            CombatEncounter combatEncounter = (CombatEncounter) encounter;
            Preparation preparation = combatEncounter.getAttackPreparation();
            if(preparation.getTestTyp().equals(test)){
                preparation.setTestTyp(to);
                preparation.calculateBoni();
               // setActive(false);
            }
        }
    }

    @Override
    public String getText() {
        return ResourceUtil.get("${switchSkill}",Bonus.class,situation.getText(),to.getText(), test.getText() );
    }

}
