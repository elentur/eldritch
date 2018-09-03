package model.effects;


import Service.GameService;
import enums.EffectTyps;
import enums.ItemType;
import lombok.Getter;
import lombok.extern.java.Log;
import model.Effect;
import model.Item.Investigator;
import utils.ResourceUtil;

@Getter
@Log
public class RandomAsset extends Effect {
    private final ItemType itemType;
    private final Investigator investigator;

    public RandomAsset(ItemType itemType,  Investigator investigator) {
        super(EffectTyps.RANDOM_ASSET);
        this.itemType = itemType;
        this.investigator = investigator;
    }


    @Override
    public void execute() {
        super.execute();
        switch (itemType) {
            case ANY :
                investigator.getInventory().add(GameService.getInstance().getAssets().draw());
                break;
            default:
                investigator.getInventory().add(GameService.getInstance().getAssets().getByItemType(itemType));
                break;
        }
        log.info(itemType.toString() );
    }

    @Override
    public String getText() {
        if(itemType==null){
            return ResourceUtil.get("${gain}","effect" , ResourceUtil.get("${nothing}","effect"  ));
        }
        return ResourceUtil.get("${gain}","effect"  ,ResourceUtil.get("${random_asset}","effect" ,itemType.getText()  ) ) ;

    }
}
