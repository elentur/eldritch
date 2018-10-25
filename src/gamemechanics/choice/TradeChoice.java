package gamemechanics.choice;


import Service.GameService;
import container.Inventory;
import container.ItemContainer;
import enums.ChoiceType;
import enums.ItemType;
import lombok.Getter;
import model.Effect;
import model.Item.Asset;
import model.Item.Investigator;
import model.Item.Item;
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
    private final ItemType itemType;

    public TradeChoice(Investigator inv1, Investigator inv2, boolean singleSelect) {
       this(inv1,inv2,singleSelect,BOTH, ItemType.ITEM);
    }

    public TradeChoice(Investigator inv1, Investigator inv2, boolean singleSelect, int tradeMode, ItemType itemType) {
        super(ChoiceType.TRADE,"Trade","");
        this.inv1 = inv1;
        this.inv2=inv2;
        this.singleSelect=singleSelect;
        this.tradeMode=tradeMode;
        this.itemType=itemType;
    }


    public ItemContainer<? extends Item>  getLeft() {
      switch (itemType){
          case CLUE_TOKEN:
              return inv1.getClues();
          default:
              return inv1.getInventory();


      }

    }
    public  ItemContainer<? extends Item>  getRight() {
        switch (itemType){
            case CLUE_TOKEN:
                return inv2.getClues();
            default:
                return inv2.getInventory();
        }
    }

}
