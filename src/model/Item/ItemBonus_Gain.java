package model.Item;

import enums.SituationTyp;
import enums.TestTyp;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class ItemBonus_Gain implements ItemBonus {

    private int value;
    private TestTyp test;
    private SituationTyp situation;

    @Override
    public void execute() {

    }


}
