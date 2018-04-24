package model.Item;


import enums.ItemTyp;

import java.util.Arrays;
import java.util.List;


public abstract class Item {

    public abstract String getId();
    public abstract String getName();


    public  List<ItemBonus> getBonus(){

        return Arrays.asList(ItemBonus_Null.value());
    }
    public ItemTyp getItemTyp(){
        return ItemTyp.NONE;
    }

}
