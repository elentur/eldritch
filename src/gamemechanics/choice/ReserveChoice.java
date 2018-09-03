package gamemechanics.choice;


import Service.GameService;
import enums.ChoiceType;
import enums.ItemType;
import lombok.Getter;
import model.Effect;
import model.Field;
import model.Item.Asset;
import model.Item.Item;
import model.Item.Monster;
import utils.ResourceUtil;

import java.util.List;

public class ReserveChoice extends Choice {
    @Getter
    private final boolean singleSelect;

    private final ItemType itemType;
    public ReserveChoice() {
        this(false,null);
    }

    public ReserveChoice(boolean singleSelect,ItemType itemType) {
        super(ChoiceType.RESERVE_CHOICE, ResourceUtil.get("${reserve_choice}", "ui"), "");
        this.singleSelect = singleSelect;
        this.itemType=itemType;
    }

    public List<Asset> get() {
        if(itemType==null) {
            return GameService.getInstance().getReserve().getReserve();
        }else{
            return GameService.getInstance().getReserve().getReserve(itemType);
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
}
