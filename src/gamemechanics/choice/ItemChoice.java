package gamemechanics.choice;


import Service.GameService;
import container.ItemContainer;
import enums.ChoiceType;
import enums.ItemType;
import lombok.Getter;
import model.Effect;
import model.Item.Asset;
import model.Item.Item;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ItemChoice extends Choice {
    @Getter
    private final boolean singleSelect;

    private final List<ItemType> itemType;
    private final ItemContainer itemContainer;
    @Getter
    private  List<Item> chosenItems;


    public ItemChoice(boolean singleSelect, List<ItemType> itemType, ItemContainer itemContainer) {
        super(ChoiceType.ITEM_CHOICE, ResourceUtil.get("${item_choice}", "ui"), "");
        this.singleSelect = singleSelect;
        this.itemType=itemType;
        this.itemContainer = itemContainer;
        chosenItems= new ArrayList<>();
    }


    public List<Item> get() {
        if(itemType==null) {
            return itemContainer;
        }else{
            Function<Item, Boolean> filter = item ->itemType.contains(item.getItemType());
            return itemContainer.getItemsWidthTypeFilter(filter);
        }
    }

    private void executeEffects(List<Effect> effects) {
        if (effects == null) {
            return;
        }
        for (Effect effect : effects) {
            effect.execute();
        }
    }

    public void addItem(Item item) {
        chosenItems.add(item);
    }
}
