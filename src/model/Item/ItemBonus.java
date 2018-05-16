package model.Item;

import enums.RangeType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public abstract class ItemBonus implements Bonus{
    private Item parentItem;
    private boolean active = true;
    private RangeType range = RangeType.SELF;




}
