package gamemechanics.choice;


import Service.GameService;
import enums.ChoiceType;
import enums.ItemType;
import lombok.Getter;
import model.Effect;
import model.Item.Asset;
import utils.ResourceUtil;

import java.util.List;

public class ReserveChoice extends Choice {
    @Getter
    private final boolean singleSelect;

    private final ItemType itemType;
    @Getter
    private int success;

    public ReserveChoice(int success) {
        this(false,null);
        this.success =success;
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
