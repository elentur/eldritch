package model.Item.conditions;

import Service.GameService;
import enums.*;
import gamemechanics.choice.InformationChoice;
import model.Effect;
import model.Item.Condition;
import model.Item.Investigator;
import model.Item.ItemBonus;
import model.Item.boni.ItemBonus_Rest;
import model.effects.And;
import model.effects.Devoured;
import model.effects.Discard;
import model.effects.LooseOrGainHealthSanity;
import utils.RNG;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Cursed0 extends Condition {

    public Cursed0() {
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
    public void doubleEffect(Investigator inv) {
        super.doubleEffect(inv);
        if(GameService.getInstance().getFieldOfInvestigator(inv).getType().equals(FieldType.SEA)){
            InformationChoice choice = new InformationChoice(getName(), ResourceUtil.get(getNameId().replace("}", "_0}"), "condition") +"\n" +
                    ResourceUtil.get(getNameId().replace("}", "_0a}"), "condition"),
                    Collections.singletonList(new And(new Discard(this), new Devoured(inv))));
            GameService.getInstance().addChoice(choice);
        }else{
            InformationChoice choice = new InformationChoice(getName(), ResourceUtil.get(getNameId().replace("}", "_0}"), "condition")+"\n" +
                    ResourceUtil.get(getNameId().replace("}", "_0b}"), "condition"),
                    Collections.singletonList(new LooseOrGainHealthSanity(SpendType.HEALTH,3, inv)));
            GameService.getInstance().addChoice(choice);
        }


    }
}
