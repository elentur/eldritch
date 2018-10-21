package model.effects;


import Service.GameService;
import enums.EffectTyps;
import enums.ItemType;
import gamemechanics.Action;
import gamemechanics.choice.ReserveChoice;
import lombok.Getter;
import lombok.extern.java.Log;
import model.Effect;
import model.Item.Investigator;
import utils.ResourceUtil;

import java.util.Arrays;
import java.util.List;

@Getter
@Log
public class AssetFromReserve extends Effect {
    private final List<ItemType> itemType;
    private final Investigator investigator;

    public AssetFromReserve( Investigator investigator,ItemType... itemType) {
        super(EffectTyps.ASSET_FROM_RESERVE);
        this.itemType = Arrays.asList(itemType);
        this.investigator = investigator;
    }
    public AssetFromReserve( Investigator investigator) {
        super(EffectTyps.ASSET_FROM_RESERVE);
        this.itemType = null;
        this.investigator = investigator;
    }

    @Override
    public void execute() {
        if(isExecuted()){
            return;
        }
        super.execute();
        if(!isAccepted()) return;
        if(itemType==null){
            gamemechanics.encounter.Encounter obj = GameService.getInstance().getEncounterProperty().get();
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
        StringBuilder s = new StringBuilder("");
        itemType.stream().map(e->e.getText()).forEach(e->{
            if(s.toString().equals("")){
                s.append(e);
            }else{
                s.append(", "+e);
            }
        });
       return ResourceUtil.get("${gain}","effect" , ResourceUtil.get("${reserve}","effect", s.toString() )) ;
    }
}
