package model.Item;

import enums.ItemTyp;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import utils.ResourceUtil;

@Getter
@Setter
@EqualsAndHashCode
public class Spell implements Item {
    private String id;
    private String name;
    private ItemTyp typ;
    private ItemBonus bonus;

    public String getName(){
        return  ResourceUtil.get(name,this.getClass());
    }
}
