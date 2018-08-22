package model;

import enums.FieldID;
import enums.FieldType;
import gamemechanics.encounter.Encounter;
import gamemechanics.encounter.americaencounter.AmericaEncounter0;
import gamemechanics.encounter.expeditionencounter.ExpeditionEncounter0;
import gamemechanics.encounter.otherworldencounter.OtherWorldEncounter0;
import gamemechanics.encounter.researchencounter.azathoth.ResearchEncounter0;
import gamemechanics.encounter.rumorencounter.RumorEncounter0;
import gamemechanics.encounter.specialencounter.shubniggurath.SpecialEncounter0;
import gamemechanics.encounter.standardencounter.StandardEncounter0;
import lombok.Getter;
import lombok.Setter;
import model.Item.Investigator;
import model.Item.investigators.AgnesBaker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class Field {
    private final FieldType type;
    private final FieldID fieldID;

    private boolean expedition;
    private boolean specialEncounter;

    public Field( FieldID fieldID){
        this.type = fieldID.getType();
        this.fieldID = fieldID;

    }

    private final Set<Investigator> investigators = new HashSet<>();

    public List<Encounter> getEncounters(Investigator inv) {
        List<Encounter> encounters = new ArrayList<>();
        encounters.add(new StandardEncounter0(inv));
        encounters.add(new ResearchEncounter0(inv));
        encounters.add(new AmericaEncounter0(inv));
        encounters.add(new ExpeditionEncounter0(inv));
        encounters.add(new OtherWorldEncounter0(inv));
        encounters.add(new SpecialEncounter0(inv));
        encounters.add(new RumorEncounter0(inv));
        return encounters;
    }

    public String getName() {
        return fieldID.getText();
    }


}
