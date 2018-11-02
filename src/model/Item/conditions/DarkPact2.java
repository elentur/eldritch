package model.Item.conditions;

import Service.GameService;
import enums.ItemType;
import gamemechanics.choice.InformationChoice;
import gamemechanics.choice.InvestigatorChoice;
import model.Item.Condition;
import model.Item.Investigator;
import model.Item.ItemBonus;
import model.effects.Devoured;
import model.effects.Discard;
import utils.RNG;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;


public class DarkPact2 extends Condition {

    public DarkPact2() {
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
                   +"\n"+ResourceUtil.get(getNameId().replace("}", "_2}"), "condition"), null);
           GameService.getInstance().addChoice(choice);
           List<Investigator> investigators = new ArrayList<>(GameService.getInstance().getInvestigators());
           investigators.remove(inv);
           GameService.getInstance().addEffect(new Devoured(new InvestigatorChoice(investigators,1)));
           GameService.getInstance().addEffect(new Discard(this));

       }
    }

}
