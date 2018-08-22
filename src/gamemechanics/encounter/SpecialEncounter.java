package gamemechanics.encounter;

import enums.EncounterType;
import enums.FieldID;
import enums.OldOnes;
import lombok.Getter;
import lombok.Setter;
import model.Item.Investigator;

@Getter
@Setter
public class SpecialEncounter extends ExpeditionEncounter {

    private FieldID fieldID;
    private final OldOnes oldOne;

    public SpecialEncounter(Investigator inv, String encounterID, OldOnes oldOne) {
        super(inv, encounterID, EncounterType.SPECIAL_ENCOUNTER);
        setInvestigator(inv);
        setEncounterPart(0);
        this.oldOne=oldOne;
    }


    @Override
    public String getNameId() {
        return "${special_encounter_"+oldOne.getKey().replaceAll("[{}\\$]", "")+"}";
    }


    @Override
    public String getId() {
        return "&special_encounter_"+oldOne.getKey().replaceAll("[{}\\$]", "");
    }




}
