package gamemechanics;

import enums.EncounterType;
import enums.TestType;
import lombok.Getter;
import lombok.Setter;
import model.Effect;
import model.Item.Investigator;

@Getter
@Setter
public class ActionEncounter extends Action {


    public ActionEncounter(Investigator inv, String encounterID, Effect startEffect) {
        super(inv, encounterID, startEffect);
    }

    public ActionEncounter(Investigator inv, String encounterID, Effect startEffect, Effect passEffect, Effect failEffect, TestType testType, int minNumberOfSuccesses) {
        super(EncounterType.ACTION_ENCOUNTER,inv, encounterID, startEffect, passEffect, failEffect, testType, minNumberOfSuccesses);
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
