package model.Item;

import enums.ItemTyp;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Asset implements Item {
    private String name;
    private ItemTyp typ;
    private List<ItemBonus> bonus;
}
