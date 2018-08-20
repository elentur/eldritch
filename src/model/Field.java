package model;

import enums.FieldType;
import gamemechanics.encounter.Encounter;
import gamemechanics.encounter.StandardEncounter;
import gamemechanics.encounter.researchencounter.ResearchEncounter0;
import gamemechanics.encounter.standardencounter.StandardEncounter0;
import lombok.AllArgsConstructor;
import lombok.Getter;
import model.Item.Investigator;
import model.Item.investigators.AgnesBaker;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class Field {
    private final FieldType type;
    private final String fieldID;
    public Field(FieldType fieldType,  int index){
        this.type = fieldType;
        fieldID = "${field_"+index+"}";
    }

    private final Set<Investigator> investigators = new HashSet<>();

    public List<Encounter> getEncounters() {
        List<Encounter> encounters = new ArrayList<>();
        encounters.add(new StandardEncounter0(new AgnesBaker()));
        encounters.add(new ResearchEncounter0(new AgnesBaker()));
        return encounters;
    }

    public String getName() {
        return ResourceUtil.get(fieldID, this.getClass());
    }
}
