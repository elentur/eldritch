package gamemechanics.choice;


import Service.GameService;
import enums.ChoiceType;
import gamemechanics.encounter.Encounter;
import model.Effect;
import model.Field;
import utils.ResourceUtil;

import java.util.List;

public class EncounterChoice extends Choice{
    private final List<Encounter> encounters;

    public EncounterChoice( Field field){
        super(ChoiceType.ENCOUNTER, field.getName() +" - " +field.getType().getText()+"\n" +ResourceUtil.get("${encounter_choice}","ui"),"");
        this.encounters = field.getEncounters(GameService.getInstance().getActiveInvestigator());
    }



    private void executeEffects(List<Effect> effects) {
        if(effects==null){
            return;
        }
        for(Effect effect:effects){
            effect.execute();
        }
    }


    public List<Encounter> getEncounters() {
        return encounters;
    }
}
