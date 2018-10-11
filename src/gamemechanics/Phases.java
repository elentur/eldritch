package gamemechanics;

import Service.GameService;
import enums.PhaseTypes;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import lombok.Getter;
import model.Item.mythos.Mythos0;

public class Phases {

    @Getter
    private PhaseTypes actualPhase;
    @Getter
    private BooleanProperty update;

    public Phases() {
        actualPhase = PhaseTypes.ACTION;
        update = new SimpleBooleanProperty(false);
    }

    public PhaseTypes getNextPhase() {
        return PhaseTypes.values()[(actualPhase.ordinal() + 1) % PhaseTypes.values().length];
    }

    public PhaseTypes switchPhase() {
        actualPhase = getNextPhase();
        if(actualPhase.equals(PhaseTypes.MYTHOS)){
            new Mythos0().execute();
         //  GameService.getInstance().setStartInvestigator();
          //  GameService.getInstance().addEffect(new SwitchPhase());

        }else if(actualPhase.equals(PhaseTypes.ACTION)){
            GameService.getInstance().setStartInvestigator();
        }
        update.setValue(true);
        return actualPhase;
    }

}
