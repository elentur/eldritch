package model.Item;


import enums.EventTimeType;
import enums.SituationTyp;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;


public abstract class Item {

    public abstract String getId();

    public  List<ItemBonus> getBonus(){

        return Arrays.asList(ItemBonus_Null.value());
    }


}
