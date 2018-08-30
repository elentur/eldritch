package model.Item;


import container.ItemStack;
import enums.ItemType;

import java.util.List;

public interface Item {
    String getNameId();

    String getName();

    String getId();

    String getUniqueId();

    List<ItemBonus> createBonus();

    List<ItemBonus> getBonus();

    ItemType getSubType();

    ItemType getItemType();


    void setStack(ItemStack itemStack);
    void discard();
}
