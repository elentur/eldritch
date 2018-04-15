package model.Item;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import model.Effect;

@Getter
@Setter
@EqualsAndHashCode
public class SpellConsequence {

    private int value;
    private String text;
    private Effect effect;
}
