package gamemechanics;

import enums.EncounterType;
import enums.SituationType;
import enums.TestType;
import lombok.Getter;
import lombok.Setter;
import model.Effect;
import model.Item.Investigator;

@Getter
@Setter
public class ActionEncounter extends Action {


    public ActionEncounter(Investigator inv, String encounterID, Effect startEffect, SituationType situationType) {
        super(inv, encounterID, startEffect,situationType);
    }

    public ActionEncounter(Investigator inv, String encounterID, Effect startEffect, Effect passEffect, Effect failEffect, TestType testType, int mod,int minNumberOfSuccesses, SituationType situationType) {
        super(EncounterType.ACTION_ENCOUNTER,inv, encounterID, startEffect, passEffect, failEffect, testType,mod, minNumberOfSuccesses,situationType);
    }


    @Override
    public String getNameId() {
        return  "${action_encounter}";
    }



    @Override
    public String getId() {
        return "&actionEncounter";
    }

    @Override
    public void discard(){


    }
}
