package model.Item.assets;

import Service.GameService;
import enums.ItemType;
import enums.SituationType;
import enums.SpendType;
import gamemechanics.Action;
import gamemechanics.choice.InformationChoice;
import gamemechanics.encounter.Encounter;
import model.Effect;
import model.Item.Asset;
import model.Item.Investigator;
import model.Item.ItemBonus;
import model.Item.Monster;
import model.effects.*;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;


public class Dynamite extends Asset {

    public Dynamite() {
        super(ItemType.ITEM_WEAPON, 3);
    }

    @Override
    public String getId() {
        return "&dynamite";
    }

    @Override
    public String getNameId() {
        return "${dynamite}";
    }
    @Override
    public Encounter getEncounter() {

        Investigator inv = GameService.getInstance().getEncounteringInvestigator();
        List<Effect> effects = new ArrayList<>();
        for(Monster monster: GameService.getInstance().getFieldOfInvestigator(inv).getMonster()){
            effects.add(new LooseOrGainHealthSanity(SpendType.HEALTH,-3,monster));
        }
        Effect effect = new NullEffect();
        if(!effects.isEmpty()) {
            effects.add(new Discard(this));
             effect = new And(effects.toArray(new Effect[]{}));
        }
        return new Action(inv,
                "dynamite",
                    effect,
                SituationType.ACTION
                );
    }
    @Override
    public List<ItemBonus> createBonus() {
        return new ArrayList<>();
    }


}
