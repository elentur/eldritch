package model.Item;


import enums.ItemTyp;

import java.util.Collections;
import java.util.List;


public abstract class Item {

    public abstract String getId();
    public abstract String getName();


    public  List<ItemBonus> getBonus(){

        return Collections.singletonList(ItemBonus_Null.value());
    }
    public ItemTyp getItemTyp(){
        return ItemTyp.NONE;
    }

    @Override
    public String toString() {
        return getName();
    }
}
