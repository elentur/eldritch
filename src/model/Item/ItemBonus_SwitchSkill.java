package model.Item;

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


    private SituationTyp situation;
    private List<SpellConsequence> consequence;
    @Override
    public void execute() {

    }
}
