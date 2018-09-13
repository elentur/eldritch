package gamemechanics.encounter;

import enums.EncounterType;
import enums.OtherWorldID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtherWorldEncounter extends ExpeditionEncounter {

    private OtherWorldID otherWorldID;

    public OtherWorldEncounter( String encounterID) {
        super( encounterID, EncounterType.OTHER_WORLD_ENCOUNTER);

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
