package gamemechanics;

import Service.GameService;
import enums.PhaseTypes;
import lombok.Getter;
import model.Item.mythos.Mythos0;
import model.effects.SwitchPhase;

public class Phases {

    @Getter
    private PhaseTypes actualPhase;

    public Phases() {
        actualPhase = PhaseTypes.ACTION;
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

        }
        return actualPhase;
    }

}
