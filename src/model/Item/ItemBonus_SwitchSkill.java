package model.Item;

import enums.EventTimeType;
import enums.SituationTyp;
import enums.TestTyp;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import utils.ResourceUtil;

import java.util.List;
import java.util.ResourceBundle;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class ItemBonus_SwitchSkill extends ItemBonus {
    private EventTimeType eventTime = EventTimeType.BEFORE;
    private SituationTyp situation;
    private TestTyp from;
    private TestTyp to;
    private List<SpellConsequence> consequence;

    public ItemBonus_SwitchSkill(SituationTyp situation, TestTyp from, TestTyp to, List<SpellConsequence> consequence) {
        this.situation = situation;
        this.consequence = consequence;
        this.from=from;
        this.to = to;
    }


    @Override
    public void execute() {

    }

    @Override
    public String getText() {
        return ResourceUtil.get("${switchSkill}",Bonus.class,situation.getText(),to.getText(), from.getText() );
    }

}
