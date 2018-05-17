package model.Item;


import enums.ItemTyp;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of={"id"})
public abstract class Item {
    protected String id;
    protected String name;


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
