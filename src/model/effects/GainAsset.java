package model.effects;


import Service.GameService;
import enums.EffectTyps;
import enums.ItemType;
import lombok.Getter;
import lombok.extern.java.Log;
import model.Effect;
import model.Item.Asset;
import model.Item.Investigator;
import utils.ResourceUtil;

@Getter
@Log
public class GainAsset extends Effect {
    private final ItemType itemType;
    private final Investigator investigator;
    private final Asset asset;

    public GainAsset(ItemType itemType, Investigator investigator) {
        super(EffectTyps.RANDOM_ASSET);
        this.itemType = itemType;
        this.investigator = investigator;
        this.asset=null;
    }

    public GainAsset(Asset asset, Investigator investigator) {
        super(EffectTyps.RANDOM_ASSET);
        this.itemType = null;
        this.investigator = investigator;
        this.asset=asset;
    }

    @Override
    public void execute() {
        super.execute();
        if(!isAccepted()) return;
        if(asset==null) {
            switch (itemType) {
                case ANY:
                    investigator.getInventory().add(GameService.getInstance().getAssets().draw());
                    break;
                default:
                    investigator.getInventory().add(GameService.getInstance().getAssets().getByItemType(itemType));
                    break;
            }
            log.info(itemType.toString() );
        }else{
            investigator.getInventory().add(asset);
            log.info(asset.getName() );
        }

    }

    @Override
    public String getText() {
        if(itemType==null && asset==null){
            return ResourceUtil.get("${gain}","effect" , investigator.getName(),ResourceUtil.get("${nothing}","effect"  ));
        }else if(asset==null) {
            return ResourceUtil.get("${gain}", "effect",investigator.getName(), ResourceUtil.get("${random_asset}", "effect", itemType.getText()));
        }else{
            return ResourceUtil.get("${gain}", "effect",investigator.getName(), ResourceUtil.get("${random_asset}", "effect", asset.getName()));
        }
    }
}
