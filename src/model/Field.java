package model;

import container.TokenContainer;
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
import lombok.extern.java.Log;
import model.Item.Investigator;
import model.Item.Monster;
import model.Item.Token;
import model.Item.token.ExpeditionToken;
import model.Item.token.GateToken;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Log
public class Field {
    private final FieldType type;
    private final FieldID fieldID;
    private final SpaceType spaceType;

    private BooleanProperty update;

    private boolean expedition;
    private boolean specialEncounter;

    private List<Neighbour> neighbours;
    private final List<Investigator> investigators;
    private final List<Monster> monster;
    private final TokenContainer tokens;

    public Field( FieldID fieldID){
        update=new SimpleBooleanProperty(false);
        this.type = fieldID.getType();
        this.fieldID = fieldID;
        this.spaceType = fieldID.getSpaceType();
        neighbours=new ArrayList<>();
        investigators = new ArrayList<>();
        monster = new ArrayList<>();
        tokens = new TokenContainer();
    }





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

    public void removeMonster(Monster monster) {
        getMonster().remove(monster);
        update.setValue(true);
    }

    public void addMonster(Monster monster) {
        getMonster().add(monster);
        update.setValue(true);
    }

    public void addGate(){
        if(!hasGate()) {
            getTokens().add(new GateToken());
            update.setValue(true);
        }else{
            log.warning("There is still a gate on field " + getFieldID().getKey());
        }
    }

    public boolean hasGate(){
        return tokens.getGate()!=null;
    }

    public void removeGate(){
        getTokens().remove(new GateToken());
        update.setValue(true);
    }

    public void addExpedition(ExpeditionToken token){
        if(!hasExpedition()) {
            getTokens().add(token);
            update.setValue(true);
        }else{
            log.warning("There is still a Expedition on field " + getFieldID().getKey());
        }
    }

    public boolean hasExpedition(){
        Token t = tokens.getExpedition();
        return tokens.getExpedition()!=null;
    }

    public void removeExpedition(){
        getTokens().remove(new GateToken());
        update.setValue(true);
    }

    public BooleanProperty updateProperty() {
        return update;
    }


}
