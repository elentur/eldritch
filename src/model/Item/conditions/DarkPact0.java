package model.Item.conditions;

import Service.GameService;
import enums.ItemType;
import enums.SituationType;
import enums.SpendType;
import enums.TestType;
import gamemechanics.Action;
import gamemechanics.Test;
import gamemechanics.choice.InformationChoice;
import model.Effect;
import model.Item.Condition;
import model.Item.Investigator;
import model.Item.Item;
import model.Item.ItemBonus;
import model.effects.*;
import utils.RNG;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class DarkPact0 extends Condition {

    public DarkPact0() {
        super(ItemType.DARK_PACT);
    }

    @Override
    public String getId() {
        return "&darkPactCondition";
    }


    @Override
    public String getNameId() {
        return "${dept_condition}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        return boni;
    }



    @Override
    public void executeReckoning(Investigator inv, boolean autoFail) {
        super.executeReckoning(inv, autoFail);
       if(RNG.getInt(6)==0) {
           InformationChoice choice = new InformationChoice(getName(), ResourceUtil.get(getNameId().replace("}", "_info}"), "condition")
                   +"\n"+ResourceUtil.get(getNameId().replace("}", "_0}"), "condition"), null);
           GameService.getInstance().addChoice(choice);
           for(Item item: inv.getInventory().getItems()){
               if(item.getItemType().equals(ItemType.SPELL)){
                   GameService.getInstance().addEffect(new SpawnGate());
               }
           }
           GameService.getInstance().addEffect(new AdvanceOmen(1));
           GameService.getInstance().addEffect(new Discard(this));

       }
    }

}
