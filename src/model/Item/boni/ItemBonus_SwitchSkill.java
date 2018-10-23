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
import preparation.Preparation;
import utils.ResourceUtil;

@Getter
@Setter

public class ItemBonus_SwitchSkill extends ItemBonus {
    private TestType to;
    private BonusType bonusType = BonusType.SWITCH_SKILL;

    public ItemBonus_SwitchSkill(SituationType situation, TestType test, TestType to, Item parentItem) {
        super(parentItem);
        this.situation = situation;
        this.test = test;
        this.to = to;
        this.eventTime = EventTimeType.BEFORE;
    }


    @Override
    public void execute(Encounter encounter) {
        if(!isExecutable()){
            return;
        }
        if(encounter instanceof CombatEncounter){
            CombatEncounter combatEncounter = (CombatEncounter) encounter;
            Preparation preparation = combatEncounter.getAttackPreparation();
            if(preparation.getTestTyp().equals(test)){
                preparation.setTestTyp(to);
                preparation.calculateBoni();
               // setActive(false);
                super.execute(encounter);
            }
        }
    }

    @Override
    public String getText() {
        return ResourceUtil.get("${switch_skill}",Bonus.class,situation.getText(),to.getSymbol(), test.getSymbol() );
    }

}
