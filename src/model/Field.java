package model;

import enums.FieldID;
import enums.FieldType;
import gamemechanics.encounter.Encounter;
import gamemechanics.encounter.americaencounter.AmericaEncounter0;
import gamemechanics.encounter.expeditionencounter.ExpeditionEncounter0;
import gamemechanics.encounter.researchencounter.azathoth.ResearchEncounter0;
import gamemechanics.encounter.standardencounter.StandardEncounter0;
import lombok.Getter;
import model.Item.Investigator;
import model.Item.investigators.AgnesBaker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class Field {
    private final FieldType type;
    private final FieldID fieldID;

    private boolean expedition;

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
        return encounters;
    }

    public String getName() {
        return fieldID.getText();
    }

    public void setExpedition(boolean expedition) {
        this.expedition = expedition;
    }
}
