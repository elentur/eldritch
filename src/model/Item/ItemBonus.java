package model.Item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ItemBonus implements Bonus{
    private Item parentItem;
    private boolean active = true;




}
