package model.Item.investigators;

import container.ItemContainer;
import enums.*;
import factory.ItemFactory;
import model.Item.boni.ItemBonus;
import model.Item.boni.ItemBonus_AdditionalDice;
import model.SkillSet;
import model.StartingPossession;

import java.util.ArrayList;
import java.util.List;

public class AgnesBaker extends Investigator {

    public AgnesBaker(){

        this.setId("&agnesBaker");
        this.setFirstName( "Agnes");
        this.setLastName("Baker");
        this.setOccupation("${agnesBakerOccupation}");
        SkillSet skills = new SkillSet(
               4,
               3,
                2,
                2,
                2);
        this.setSkillSet(skills);
        this.setHealth(7);
        this.setSanity(5);
        this.setActualHealth(getHealth());
        this.setActualSanity(getSanity());
        this.setStartingSpace("London");
        this.setStartingPossessions(createStartingPossessions());
        this.setBonus(createBonus());
        ItemFactory itemFactory = new ItemFactory();
        this.setInventory(new ItemContainer<>());
        for (StartingPossession p : getStartingPossessions()) {
            switch (p.getTyp()) {
                case ASSET:
                    this.getInventory().add(itemFactory.getAssets().get(p.getId()));
                    break;
                case SPELL:
                    this.getInventory().add(itemFactory.getSpells().get(p.getId()));
                    break;
                default:
                    break;

            }
        }

    }
    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        ItemBonus_AdditionalDice bonus1 = new ItemBonus_AdditionalDice(2,TestType.WILL,SituationType.SPELL_EFFECT,RangeType.LOCAL,FieldType.ALL,this);
        bonus1.setPerRound(true);
        boni.add(bonus1);
        return boni;
    }

    @Override
    protected List<StartingPossession> createStartingPossessions() {
        List<StartingPossession> possessions = new ArrayList<>();
        possessions.add( new StartingPossession("&profaneTome",1,ElementTyp.ASSET));
        possessions.add( new StartingPossession("&stormOfSpirits",1,ElementTyp.SPELL));
        return possessions;

    }
}
