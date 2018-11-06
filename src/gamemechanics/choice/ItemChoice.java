package gamemechanics.choice;


import Service.GameService;
import container.ItemContainer;
import enums.ChoiceType;
import enums.ItemType;
import expetions.ItemChoiceException;
import lombok.Getter;
import model.Effect;
import model.Item.Item;
import model.effects.Discard;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ItemChoice extends Choice {


    @Getter
    private final List<ItemType> itemType;
    private final ItemContainer itemContainer;
    @Getter
    private final int number;
    @Getter
    private  List<Item> chosenItems;

    private ItemContainer returnContainer;


    public ItemChoice(int number, List<ItemType> itemType, ItemContainer itemContainer) {
        super(ChoiceType.ITEM_CHOICE, ResourceUtil.get("${item_choice}", "ui"), "");
        this.number = number;
        this.itemType=itemType;
        this.itemContainer = itemContainer;
        chosenItems= new ArrayList<>();

    }


    public List<Item> get() {
        if(itemType==null) {
            returnContainer= itemContainer;
        }else{
            Function<Item, Boolean> filter = item ->{
                boolean contains=true;
                for(ItemType type:itemType){
                    contains &=item.getSubType().equalsWithParts(type);
                }
                return contains;
            };
            returnContainer= itemContainer.getItemsWithTypeFilter(filter);
        }
        if(returnContainer.isEmpty()){
            throw new ItemChoiceException(ResourceUtil.get("${no_item_for_choice}","exception"));
        }
        return returnContainer;
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

    public void choose(List<Item> chosen) {
        if(number >0 && chosen.size()!=number && returnContainer!=null && returnContainer.size()>=number){
            throw new ItemChoiceException(ResourceUtil.get("${item_number_wrong}","exception",chosen.size()+"",number+""));

        }else{
            for(Item item:chosen){
               GameService.getInstance().addEffect(new Discard(item));
            }
        }
    }
}
