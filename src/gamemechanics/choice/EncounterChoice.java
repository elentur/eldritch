package gamemechanics.choice;


import enums.YesNo;
import gamemechanics.encounter.Encounter;
import model.Effect;

import java.util.List;

public class EncounterChoice extends Choice{
    private final List<Encounter> encounters;

    public EncounterChoice(String headline, String info, List<Encounter> encounters){
        super(headline,info);
        this.encounters = encounters;
    }



    private void executeEffects(List<Effect> effects) {
        if(effects==null){
            return;
        }
        for(Effect effect:effects){
            effect.execute();
        }
    }


    public List<gamemechanics.encounter.Encounter> getEncounters() {
        return encounters;
    }
}
