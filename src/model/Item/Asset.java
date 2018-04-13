package model.Item;

import enums.ItemTyp;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class Asset implements Item {
    private String name;
    private ItemTyp typ;
    private List<ItemBonus> bonus;
}
