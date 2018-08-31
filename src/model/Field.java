package model;

import Service.GameService;
import container.TokenContainer;
import enums.FieldID;
import enums.FieldType;
import enums.SpaceType;
import gamemechanics.encounter.Encounter;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import model.Item.Investigator;
import model.Item.Monster;
import model.Item.Token;
import model.Item.token.*;

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

    public BooleanProperty updateProperty() {
        return update;
    }

    public Field(FieldID fieldID) {
        update = new SimpleBooleanProperty(false);
        this.type = fieldID.getType();
        this.fieldID = fieldID;
        this.spaceType = fieldID.getSpaceType();
        neighbours = new ArrayList<>();
        investigators = new ArrayList<>();
        monster = new ArrayList<>();
        tokens = new TokenContainer();
    }


    public List<Encounter> getEncounters(Investigator inv) {
        List<Encounter> encounters = new ArrayList<>();
        if(type.equals(FieldType.CITY) || type.equals(FieldType.WILDERNESS)|| type.equals(FieldType.SEA)){
            encounters.add(GameService.getInstance().getStandardEncounters().showFirst());
        }
        if(fieldID.equals(FieldID.ARKHAM) || fieldID.equals(FieldID.SAN_FRANCISCO) || fieldID.equals(FieldID.BUENOS_AIRES) ){
            encounters.add(GameService.getInstance().getAmericaEncounter().showFirst());
        }
        if(fieldID.equals(FieldID.TOKYO) || fieldID.equals(FieldID.SHANGHAI) || fieldID.equals(FieldID.SYDNEY) ){
            encounters.add(GameService.getInstance().getAsiaEncounter().showFirst());
        }
        if(fieldID.equals(FieldID.LONDON) || fieldID.equals(FieldID.ROME) || fieldID.equals(FieldID.ISTANBUL) ){
            encounters.add(GameService.getInstance().getEuropeEncounter().showFirst());
        }
        if(hasExpedition()){
                encounters.add(tokens.getExpedition().getEncounter());
        }
        if(hasRumor()){
            encounters.add(tokens.getRumor().getEncounter());
        }
        if(hasGate()){
            encounters.add(tokens.getGate().getEncounter());
        }
        if(getNumberOfClues()>0){
            encounters.add(tokens.getClues().get(0).getEncounter());
        }
        if(hasMystery()){
            encounters.add(tokens.getMystery().getEncounter());
        }
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

    public void addGate() {
        if (!hasGate()) {
            addToken(new GateToken());
        } else {
            log.warning("There is still a gate on field " + getFieldID().getKey());
        }
    }

    public boolean hasGate() {
        return tokens.getGate() != null;
    }

    public Token removeGate() {
        return removeToken(getTokens().getGate());
    }

    public void addExpedition(ExpeditionToken token) {
        if (!hasExpedition()) {
            addToken(token);
        } else {
            log.warning("There is still a Expedition on field " + getFieldID().getKey());
        }
    }

    public boolean hasExpedition() {

        return tokens.getExpedition() != null;
    }

    public Token removeExpedition() {
        return removeToken(getTokens().getExpedition());
    }

    public void addClue(ClueToken token) {
        addToken(token);
    }

    public int getNumberOfClues() {

        return tokens.getClues().size();
    }

    public Token removeClue(ClueToken token) {
        if(token == null && getNumberOfClues()>0){
            return removeToken( tokens.getClues().get(0));
        }
        return removeToken(token);
    }


    public void addEldritchToken(EldritchToken token) {
        addToken(token);
    }

    public int getNumberOfEldritchTokens() {

        return tokens.getEldritchTokens().size();
    }

    public Token removeEldritchToken(EldritchToken token) {
        return removeToken(token);
    }

    public void addRumor(RumorToken token) {
        if (!hasRumor()) {
            addToken(token);
        } else {
            log.warning("There is still a Rumor on field " + getFieldID().getKey());
        }
    }

    public boolean hasRumor() {

        return tokens.getRumor() != null;
    }

    public Token removeRumor() {
        return removeToken(getTokens().getRumor());
    }

    public void addMystery(MysteryToken token) {
        if (!hasMystery()) {
            addToken(token);
        } else {
            log.warning("There is still a Mystery on field " + getFieldID().getKey());
        }
    }

    public boolean hasMystery() {

        return tokens.getMystery() != null;
    }

    public Token removeMystery() {
        return removeToken(getTokens().getMystery());
    }

    private void addToken(Token token) {
        getTokens().add(token);
        update.setValue(true);
    }

    private Token removeToken(Token token) {
        getTokens().remove(token);
        update.setValue(true);
        return token;
    }

}
