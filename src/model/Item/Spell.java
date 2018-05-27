package model.Item;

import enums.ItemType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import utils.ResourceUtil;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public abstract class Spell implements Item {

    private ItemType type;
    private List<ItemBonus> bonus;


    public Spell(ItemType type ){
        this.type = type;
        this.bonus= createBonus();
    }
    public String getName(){
        return  ResourceUtil.get(getNameId(),this.getClass());
    }

    public String toString() {
        return getName();
    }

    @Override
    public ItemType getItemTyp() {
        return type;
    }
}
