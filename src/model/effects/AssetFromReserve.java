package model.effects;


import Service.GameService;
import enums.EffectTyps;
import enums.ItemType;
import gamemechanics.choice.ReserveChoice;
import lombok.Getter;
import lombok.extern.java.Log;
import model.Effect;
import model.Item.Investigator;
import utils.ResourceUtil;

@Getter
@Log
public class AssetFromReserve extends Effect {
    private final ItemType itemType;
    private final Investigator investigator;

    public AssetFromReserve(ItemType itemType, Investigator investigator) {
        super(EffectTyps.ASSET_FROM_RESERVE);
        this.itemType = itemType;
        this.investigator = investigator;
    }


    @Override
    public void execute() {
        super.execute();
        GameService.getInstance().addChoice(new ReserveChoice(true,itemType));
        log.info(itemType.toString() );
    }

    @Override
    public String getText() {
        if(itemType==null){
            return ResourceUtil.get("${gain}","effect" , ResourceUtil.get("${nothing}","effect"  ));
        }
       return ResourceUtil.get("${gain}","effect" , ResourceUtil.get("${reserve}","effect", itemType.getText()  )) ;
    }
}
