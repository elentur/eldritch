package model.Item.investigators;

import Service.GameService;
import enums.*;
import factory.ItemFactory;
import gamemechanics.choice.Choice;
import gamemechanics.choice.YesNoChoice;
import model.Item.Investigator;
import model.Item.ItemBonus;
import model.Item.boni.ItemBonus_AdditionalDice;
import model.SkillSet;
import model.effects.Spend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AkachiOnyele extends Investigator {

    public AkachiOnyele(){
        super("&akachiOnyele",
                new SkillSet(
                        3,
                        2,
                        2,
                        2,
                        4),
                5,
                7,
                FieldID.FIELD_15,
               GameService.getInstance().getAssets().get("&profaneTome"),
                GameService.getInstance().getSpells().get("&stormOfSpirits")
        );



    }
    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        ItemBonus_AdditionalDice bonus1 = new ItemBonus_AdditionalDice(2,TestType.WILL,SituationType.COMBAT_ENCOUNTER,RangeType.LOCAL,FieldType.ALL,this);
        bonus1.setPerRound(true);
        Choice condition = new YesNoChoice("",getBonusText(), Arrays.asList(new Spend(SpendType.SANITY,1,this),new Spend(SpendType.HEALTH,1,this)),null );
        bonus1.setCondition(condition);
        boni.add(bonus1);
        return boni;
    }


}