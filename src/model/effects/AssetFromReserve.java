package model.effects;


import enums.EffectTyps;
import enums.ItemType;
import lombok.Getter;
import lombok.extern.java.Log;
import model.Effect;
import model.Item.Investigator;
import utils.ResourceUtil;

@Getter
@Log
public class AssetFromReserve extends Effect {
    private final ItemType itemType;
    private final int value;
    private final Investigator investigator;

    public AssetFromReserve(ItemType itemType, int value, Investigator investigator) {
        super(EffectTyps.ASSET_FROM_RESERVE);
        this.itemType = itemType;
        this.value = value;
        this.investigator = investigator;
    }


    @Override
    public void execute() {
        super.execute();
        switch (itemType) {
            case ITEM :
                break;
            default:
                break;
        }
        log.info(itemType.toString() );
    }

    @Override
    public String getText() {
        if(itemType==null ||value ==0){
            return ResourceUtil.get("${gain}","effect"  ) + " " + ResourceUtil.get("${nothing}","effect"  );
        }
       return ResourceUtil.get("${gain}","effect"  ) + " "+ value + " " + itemType.getText() + " " + ResourceUtil.get("${reserve}","effect"  ) ;
    }
}
