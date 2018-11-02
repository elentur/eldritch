package model.Item.assets;

import Service.GameService;
import enums.ConditionType;
import enums.ItemType;
import enums.SituationType;
import enums.TestType;
import gamemechanics.ActionEncounter;
import gamemechanics.choice.InvestigatorChoice;
import gamemechanics.encounter.Encounter;
import model.Effect;
import model.Item.Asset;
import model.Item.Investigator;
import model.Item.ItemBonus;
import model.Item.boni.ItemBonus_GainDice;
import model.effects.And;
import model.effects.Discard;
import model.effects.GainCondition;
import model.effects.NullEffect;

import java.util.ArrayList;
import java.util.List;


public class HolyWater extends Asset {

    public HolyWater() {
        super(ItemType.ITEM_MAGICAL, 2);
    }

    @Override
    public String getId() {
        return "&holyWater";
    }

    @Override
    public String getNameId() {
        return "${holy_water}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        ItemBonus_GainDice bonus1 = new ItemBonus_GainDice(1,TestType.STRENGTH,SituationType.COMBAT_ENCOUNTER,this);
        boni.add(bonus1);
        ItemBonus_GainDice bonus2 = new ItemBonus_GainDice(1,TestType.WILL,SituationType.COMBAT_ENCOUNTER,this);
        boni.add(bonus2);
        return boni;
    }

    @Override
    public Encounter getEncounter() {

        Investigator inv = GameService.getInstance().getEncounteringInvestigator();
        Effect effect1 = new Discard(this);
        Effect effect2 = new GainCondition(ConditionType.BLESSED,new InvestigatorChoice(GameService.getInstance().getFieldOfInvestigator(inv).getFieldID(),1));
        Effect effect = new And(effect1,effect2);

        ActionEncounter encounter =  new ActionEncounter(inv,
                "HolyWater",
                new NullEffect(),
                effect,
                new NullEffect(),
                TestType.NONE,
                0,
                0,
                SituationType.ACTION
        );

        return encounter;

    }

}
