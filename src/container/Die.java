package container;

import Service.DiceRollerService;
import enums.ConditionTyp;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Die {
    private int value;


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

    public boolean isSuccess(ConditionTyp conditionTyp) {
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
}
