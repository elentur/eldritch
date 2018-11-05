package model.Item.conditions;

import Service.GameService;
import enums.ItemType;
import gamemechanics.choice.InformationChoice;
import gamemechanics.encounter.CombatEncounter;
import model.Item.Condition;
import model.Item.Investigator;
import model.Item.ItemBonus;
import model.Item.Monster;
import model.effects.Discard;
import utils.RNG;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Cursed2 extends Condition {

    public Cursed2() {
        super(ItemType.CURSED_CONDITION);
    }

    @Override
    public String getId() {
        return "&cursedCondition";
    }


    @Override
    public String getNameId() {
        return "${cursed_condition}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        return boni;
    }



    @Override
    public void executeReckoning(Investigator inv, boolean autoFail) {
        super.executeReckoning(inv, autoFail);
       if(RNG.getInt(6)<=2) {
           InformationChoice choice = new InformationChoice(getName(), ResourceUtil.get(getNameId().replace("}", "_info}"), "condition"), Collections.singletonList(new Discard(this)));
           GameService.getInstance().addChoice(choice);

       }
    }

    @Override
    public void doubleEffect() {
        super.doubleEffect();
             InformationChoice choice = new InformationChoice(getName(), ResourceUtil.get(getNameId().replace("}", "_2}"), "condition"),
                     Collections.singletonList(new Discard(this)));

        GameService.getInstance().addChoice(choice);



    }
}
