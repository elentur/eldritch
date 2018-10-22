package gamemechanics;

import Service.GameService;
import enums.PhaseTypes;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import lombok.Getter;
import gamemechanics.mythos.green.Mythos0;
import model.Item.Bonus;
import model.Item.Investigator;
import model.Item.Item;
import model.Item.ItemBonus;

public class Phases {

    @Getter
    private PhaseTypes actualPhase;
    @Getter
    private BooleanProperty update;
    GameService game;
    public Phases() {
         game =  GameService.getInstance();
        actualPhase = PhaseTypes.ACTION;
        update = new SimpleBooleanProperty(false);
    }

    public PhaseTypes getNextPhase() {
        return PhaseTypes.values()[(actualPhase.ordinal() + 1) % PhaseTypes.values().length];
    }

    public PhaseTypes switchPhase() {

        actualPhase = getNextPhase();
        if(actualPhase.equals(PhaseTypes.MYTHOS)){
          Mythos mythos = (Mythos) GameService.getInstance().getMythos().draw();
          mythos.execute();
          reactivateBoni();
         //  GameService.getInstance().setStartInvestigator();
          //  GameService.getInstance().addEffect(new SwitchPhase());

        }else if(actualPhase.equals(PhaseTypes.ACTION)){
            game.handleMystery();
            game.setStartInvestigator();
        }
        update.setValue(true);
        return actualPhase;
    }


    private void reactivateBoni(){
        for(Investigator inv:GameService.getInstance().getInvestigators()){
            for(Item item : inv.getInventory()){
                for(ItemBonus bonus:item.getBonus()){
                    bonus.setUsable(true);
                }
            }
        }
    }

}
