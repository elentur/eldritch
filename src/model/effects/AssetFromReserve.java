package model.effects;


import Service.GameService;
import enums.EffectTyps;
import enums.ItemType;
import gamemechanics.Action;
import gamemechanics.choice.ReserveChoice;
import lombok.Getter;
import lombok.Setter;
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
    @Setter
    private  int num;
    private final int success;
    @Setter
    private boolean useSuccess;

    public AssetFromReserve( int num,Investigator investigator,ItemType... itemType) {
        super(EffectTyps.ASSET_FROM_RESERVE);
        this.itemType = Arrays.asList(itemType);
        this.investigator = investigator;
        this.num=num;
        this.success=0;
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
            if(  obj instanceof Action) {
                Action action = (Action) obj;
                GameService.getInstance().addChoice(new ReserveChoice( action.getResult().getNumberOfSuccess()));
            }
        }else{
           ReserveChoice choice=  new ReserveChoice(num,itemType);
            if(useSuccess){
                gamemechanics.encounter.Encounter obj = GameService.getInstance().getEncounterProperty().get();
                if(  obj instanceof Action) {
                    Action action = (Action) obj;
                    choice.setSuccess(action.getResult().getNumberOfSuccess());
                    choice.setNum(0);
                }
            }
            GameService.getInstance().addChoice(choice);

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
                s.append(" or "+e);
            }
        });
       return ResourceUtil.get("${gain}","effect" , investigator.getName(), ResourceUtil.get("${reserve}","effect", s.toString() )) ;
    }
}
