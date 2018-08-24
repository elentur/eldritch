package model;

import enums.FieldID;
import enums.FieldType;
import enums.SpaceType;
import gamemechanics.encounter.Encounter;
import gamemechanics.encounter.americaencounter.AmericaEncounter0;
import gamemechanics.encounter.expeditionencounter.ExpeditionEncounter0;
import gamemechanics.encounter.otherworldencounter.OtherWorldEncounter0;
import gamemechanics.encounter.researchencounter.azathoth.ResearchEncounter0;
import gamemechanics.encounter.rumorencounter.RumorEncounter0;
import gamemechanics.encounter.specialencounter.shubniggurath.SpecialEncounter0;
import gamemechanics.encounter.standardencounter.StandardEncounter0;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
    private final SpaceType spaceType;

    private BooleanProperty update;

    private boolean expedition;
    private boolean specialEncounter;

    private List<Neighbour> neighbours;

    public Field( FieldID fieldID){
        update=new SimpleBooleanProperty(false);
        this.type = fieldID.getType();
        this.fieldID = fieldID;
        this.spaceType = fieldID.getSpaceType();
        neighbours=new ArrayList<>();

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

    public void removeInvestigator(Investigator inv) {
        getInvestigators().remove(inv);
        update.setValue(true);
    }

    public void addInvestigator(Investigator inv) {
        getInvestigators().add(inv);
        update.setValue(true);
    }

    public BooleanProperty updateProperty() {
        return update;
    }
}
