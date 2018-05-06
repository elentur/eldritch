package model.Item;

import enums.RangeType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ItemBonus implements Bonus{
    private Item parentItem;
    private boolean active = true;
    private RangeType range = RangeType.SELF;




}
