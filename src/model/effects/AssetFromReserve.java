package model.effects;


import Service.GameService;
import enums.EffectTyps;
import enums.ItemType;
import gamemechanics.Action;
import gamemechanics.choice.ReserveChoice;
import gamemechanics.encounter.Encounter;
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
    public AssetFromReserve( Investigator investigator) {
      this(null,investigator);
    }

    @Override
    public void execute() {
        super.execute();
        if(itemType==null){
            Encounter obj = GameService.getInstance().getEncounterProperty().get();
            if(obj != null && obj instanceof Action) {
                Action action = (Action) obj;
                action.getResult().getNumberOfSuccess();
                GameService.getInstance().addChoice(new ReserveChoice( action.getResult().getNumberOfSuccess()));
            }
        }else{
            GameService.getInstance().addChoice(new ReserveChoice(true,itemType));
            log.info(itemType.toString() );
        }

    }

    @Override
    public String getText() {
        if(itemType==null){
            return"";
        }
       return ResourceUtil.get("${gain}","effect" , ResourceUtil.get("${reserve}","effect", itemType.getText()  )) ;
    }
}
