package model.Item;

import enums.ItemTyp;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import utils.ResourceUtil;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class Spell extends Item {
    @Getter
    private String id;
    private String name;
    private ItemTyp typ;
    private List<ItemBonus> bonus;

    public String getName(){
        return  ResourceUtil.get(name,this.getClass());
    }
}
