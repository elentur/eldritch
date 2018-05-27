package model.Item;


import enums.ItemType;

import java.util.List;

public interface Item {
    String getNameId();

    String getName();

    String getId();

    List<ItemBonus> createBonus();

    List<ItemBonus> getBonus();

    ItemType getItemTyp();


}
