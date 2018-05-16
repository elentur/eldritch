package model.Item;

import enums.EventTimeType;
import enums.SituationTyp;
import enums.TestTyp;
import gamemechanics.CombatEncounter;
import gamemechanics.Encounter;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import preparation.Preparation;
import utils.ResourceUtil;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public class ItemBonus_SwitchSkill extends ItemBonus {
    private EventTimeType eventTime = EventTimeType.BEFORE;
    private SituationTyp situation;
    private TestTyp test;
    private TestTyp to;
    private List<SpellConsequence> consequence;

    public ItemBonus_SwitchSkill(SituationTyp situation, TestTyp test, TestTyp to, List<SpellConsequence> consequence) {
        this.situation = situation;
        this.consequence = consequence;
        this.test = test;
        this.to = to;
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
