package model.Item;

import enums.ItemType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import utils.ResourceUtil;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public abstract class Asset implements Item {

    public String uniqueId = UUID.randomUUID().toString();
    private final ItemType type;
    private final int price;
    private final List<ItemBonus> bonus;

    public Asset(ItemType type, int price ){
        this.type = type;
        this.price=price;
        this.bonus= createBonus();

    }
    public String getName(){
        return  ResourceUtil.get(getNameId(),"asset");
    }

    public String toString() {
        return getName();
    }

    @Override
    public ItemType getItemTyp() {
        return type;
    }
}