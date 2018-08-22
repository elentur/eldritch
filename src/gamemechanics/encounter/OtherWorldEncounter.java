package gamemechanics.encounter;

import enums.EncounterType;
import enums.FieldID;
import gamemechanics.choice.InformationChoice;
import lombok.Getter;
import lombok.Setter;
import model.Effect;
import model.Item.Investigator;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OtherWorldEncounter extends ExpeditionEncounter {

    private FieldID fieldID;

    public OtherWorldEncounter(Investigator inv, String encounterID) {
        super(inv, encounterID, EncounterType.OTHER_WORLD_ENCOUNTER);
        setInvestigator(inv);
        setEncounterPart(0);
    }


    @Override
    public String getNameId() {
        return "${other_world_encounter}";
    }


    @Override
    public String getId() {
        return "&other_world_encounter";
    }




}
