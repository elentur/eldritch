package gamemechanics.choice;


import Service.GameService;
import container.Inventory;
import enums.ChoiceType;
import enums.ItemType;
import lombok.Getter;
import model.Effect;
import model.Item.Asset;
import model.Item.Investigator;
import utils.ResourceUtil;

import java.util.List;

public class TradeChoice extends Choice {
    public final static int BOTH = 0;
    public final static int LEFT_TO_RIGHT = 1;
    public final static int RIGHT_TO_LETF = 2;

    @Getter
    private final boolean singleSelect;
    @Getter
    private final Investigator inv2;
    @Getter
    private final Investigator inv1;
    @Getter
    private final int tradeMode;

    public TradeChoice(Investigator inv1, Investigator inv2, boolean singleSelect) {
       this(inv1,inv2,singleSelect,BOTH);
    }

    public TradeChoice(Investigator inv1, Investigator inv2, boolean singleSelect, int tradeMode) {
        super(ChoiceType.TRADE,"Trade","");
        this.inv1 = inv1;
        this.inv2=inv2;
        this.singleSelect=singleSelect;
        this.tradeMode=tradeMode;
    }


    public Inventory getLeft() {
      return inv1.getInventory();
    }
    public Inventory getRight() {
        return inv2.getInventory();
    }

}
