package model.Item;

import enums.EventTimeType;
import enums.SituationTyp;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class ItemBonus_SwitchSkill implements ItemBonus {

    public ItemBonus_SwitchSkill(SituationTyp situation, List<SpellConsequence> consequence) {
        this.situation = situation;
        this.consequence = consequence;
    }

    private EventTimeType eventTime = EventTimeType.BEFORE;
    private SituationTyp situation;
    private List<SpellConsequence> consequence;
    @Override
    public void execute() {

    }
}
