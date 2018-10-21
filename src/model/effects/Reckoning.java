package model.effects;


import Service.GameService;
import enums.EffectTyps;
import enums.ItemType;
import lombok.Getter;
import model.Effect;
import model.Field;
import model.Item.Investigator;
import model.Item.Item;
import model.Item.Monster;
import utils.ResourceUtil;

import java.util.List;
import java.util.function.Function;

@Getter
public class Reckoning extends Effect {
    private final List<ItemType> itemTypeList;
    private final Function<Item, Boolean> filter;
    private final boolean autoFail;

    public Reckoning() {
        this(null,false);
    }

    public Reckoning(List<ItemType> itemTypeList, boolean autoFail) {
        super(EffectTyps.RECKONING);
        this.autoFail =autoFail;
        this.itemTypeList =itemTypeList;
        if(itemTypeList==null){
            filter =item->true;
        }else {
            filter = item -> {
                boolean isTrue = true;
                for (ItemType itemType : itemTypeList) {
                    isTrue &= itemType.equalsWithParts(item.getItemType());
                }
                return isTrue;
            };
        }
    }

    @Override
    public void execute() {
        if(isExecuted()){
            return;
        }
        super.execute();
        if(!isAccepted()) return;
        for(Field field: GameService.getInstance().getGameBoard().getFields()){
            for(Monster m : field.getMonster()){
                m.executeReckoning(GameService.getInstance().getStartInvestigator(), autoFail);
            }
        }
        GameService.getInstance().getAncientOne().executeReckoning(GameService.getInstance().getStartInvestigator(), autoFail);
        for(Field field: GameService.getInstance().getGameBoard().getFields()){
            if(field.hasRumor()){
                field.getRumor().getMythos().executeReckoning(GameService.getInstance().getStartInvestigator(),autoFail );
            }
        }
        for(Investigator inv: GameService.getInstance().getInvestigators()){
            inv.executeReckoning(inv, autoFail);
            for(Item item : inv.getInventory()){
                item.executeReckoning(inv,autoFail );
            }
        }

    }

    @Override
    public String getText() {
        if(itemTypeList== null){
            return ResourceUtil.get("${reckoning}", "effect" );
        }else{
            StringBuilder itemtypes = new StringBuilder();
            for(int i = 0; i < itemTypeList.size();i++){
                itemtypes.append(itemTypeList.get(0).getText());
                if(i < itemTypeList.size()-1 ){
                    itemtypes.append(", ");
                }
            }
            return ResourceUtil.get("${reckoning_for_types}", "effect", itemtypes.toString() );
        }

    }
}
