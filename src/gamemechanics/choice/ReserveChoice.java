package gamemechanics.choice;


import Service.GameService;
import enums.ChoiceType;
import enums.ItemType;
import lombok.Getter;
import lombok.Setter;
import model.Effect;
import model.Item.Asset;
import utils.ResourceUtil;

import java.util.List;

public class ReserveChoice extends Choice {
    @Getter
    @Setter
    private  int num;//0 bedeutet ohne Einschränkungen

    private final List<ItemType> itemType;
    @Getter
    private int success;

    public ReserveChoice(int success) {
        this(0,null);
        this.success =success;
    }

    public ReserveChoice(int num ,List<ItemType> itemType) {
        super(ChoiceType.RESERVE_CHOICE, ResourceUtil.get("${reserve_choice}", "ui"), "");
        this.num = num;
        this.itemType=itemType;
    }

    public List<Asset> get() {
        if(itemType==null || itemType.isEmpty()) {
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

    public void setSuccess(int success) {
        this.success = success;
    }
}
