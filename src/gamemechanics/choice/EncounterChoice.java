package gamemechanics.choice;


import enums.ChoiceType;
import enums.YesNo;
import gamemechanics.encounter.Encounter;
import model.Effect;
import model.Field;
import utils.ResourceUtil;

import java.util.List;

public class EncounterChoice extends Choice{
    private final List<Encounter> encounters;

    public EncounterChoice( Field field){
        super(ChoiceType.ENCOUNTER, field.getName() +"\n" +ResourceUtil.get("${encounter_choice}","ui"),"");
        this.encounters = field.getEncounters();
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