package model.Item.conditions;

import Service.GameService;
import enums.EffectSelector;
import enums.FieldID;
import enums.ItemType;
import gamemechanics.choice.InformationChoice;
import gamemechanics.choice.MonsterChoice;
import model.Item.Condition;
import model.Item.Investigator;
import model.Item.ItemBonus;
import model.effects.ChooseSpace;
import model.effects.Discard;
import model.effects.DiscardMonster;
import model.effects.GainClue;
import utils.RNG;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Blessed2 extends Condition {

    public Blessed2() {
        super(ItemType.BLESSED_CONDITION);
    }

    @Override
    public String getId() {
        return "&blessedCondition";
    }


    @Override
    public String getNameId() {
        return "${blessed_condition}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        return boni;
    }



    @Override
    public void executeReckoning(Investigator inv, boolean autoFail) {
        super.executeReckoning(inv, autoFail);
       if(RNG.getInt(6)<=1) {
           InformationChoice choice = new InformationChoice(getName(), ResourceUtil.get(getNameId().replace("}", "_info}"), "condition"), Collections.singletonList(new Discard(this)));
           GameService.getInstance().addChoice(choice);

       }
    }

    @Override
    public void doubleEffect() {
        super.doubleEffect();
        InformationChoice choice = new InformationChoice(getName(), ResourceUtil.get(getNameId().replace("}", "_2}"), "condition"),
                Collections.singletonList(new ChooseSpace(new DiscardMonster(new MonsterChoice(FieldID.CHOSEN_FIELD)))));
        GameService.getInstance().addChoice(choice);

    }
}
