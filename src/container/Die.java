package container;

import Service.DiceRollerService;
import enums.ConditionType;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Die {
    private int value;

    public Die(int value) {
        this.value = value;
    }

    private boolean shiftable;
    public int getValue() {
        return value;
    }

    public void setValue(int v) {
        if (v > 6) {
            value = 6;
        } else if (v < 1) {
            value = 1;
        } else {
            value = v;
        }
    }

    public boolean isSuccess(ConditionType conditionTyp) {
        switch (conditionTyp) {
            case CURSED:
                return value == 6;
            case BLESSED:
                return value >= 4;
            default:
                return value >= 5;
        }
    }

    public void reroll() {
        DiceRollerService service = new DiceRollerService();
        service.rerollDie(this);
    }

    public void shift(int v) {
        setValue(getValue()+v);
        if(getValue()>=6){
            setShiftable(false);
        }
    }
}
