package model.effects;


import Service.GameService;
import enums.EffectTyps;
import enums.ItemType;
import gamemechanics.choice.Choice;
import gamemechanics.choice.InvestigatorChoice;
import gamemechanics.choice.ItemChoice;
import gamemechanics.choice.MonsterChoice;
import lombok.Getter;
import model.Effect;
import model.Item.Investigator;
import model.Item.Item;
import model.Item.token.GateToken;
import utils.ResourceUtil;

import java.util.List;

@Getter
public class Discard extends Effect {
    private Item item;

    public Discard(Item item) {
        super(EffectTyps.DISCARD);
        this.item = item;
    }

    public Discard(Choice condition) {
        super(EffectTyps.DISCARD);
        this.condition = condition;
    }


    @Override
    public void execute() {
        init();
        super.execute();
        if (!isAccepted()) return;
        if(item!=null) {
            for (Investigator inv : GameService.getInstance().getInvestigators()) {
                if (inv.getInventory().contains(item)) {
                    inv.removeFromInventory(item);
                }
            }
            item.discard();
        }

    }

    @Override
    public String getText() {
        if(item==null){
            List<ItemType> itemType=((ItemChoice)condition).getItemType();
            StringBuilder s = new StringBuilder(itemType.get(0).getText());

            for(int i =1; i< itemType.size();i++){
                s.append(" or " +itemType.get(i).getText());
            }
            return ResourceUtil.get("${discard}", "effect", ((ItemChoice)condition).getNumber() +" " + s.toString());
        }
        return ResourceUtil.get("${discard}", "effect", item.getName());
    }

    @Override
    public void init() {
        super.init();
        if (condition != null) {
            if(!((ItemChoice) condition).getChosenItems().isEmpty()) {
                item = ((ItemChoice) condition).getChosenItems().get(0);
            }
        }

    }
}
