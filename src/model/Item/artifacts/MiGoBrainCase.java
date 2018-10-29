package model.Item.artifacts;

import Service.GameService;
import enums.*;
import gamemechanics.Action;
import gamemechanics.choice.TradeChoice;
import gamemechanics.encounter.Encounter;
import model.Effect;
import model.Item.Artifact;
import model.Item.Investigator;
import model.Item.ItemBonus;
import model.Item.Monster;
import model.Item.boni.ItemBonus_GainDice;
import model.effects.*;

import java.util.ArrayList;
import java.util.List;


public class MiGoBrainCase extends Artifact {

    public MiGoBrainCase() {
        super(ItemType.ITEM_MAGICAL_TEAMWORK);
    }

    @Override
    public String getId() {
        return "&miGoBrainCase";
    }

    @Override
    public String getNameId() {
        return "${mi_go_brain_case}";
    }
    @Override
    public Encounter getEncounter() {

        Investigator inv = GameService.getInstance().getEncounteringInvestigator();

        //TODO Plätze tauschen
        Trade trade =new Trade(inv, FieldID.CHOSEN_FIELD, TradeChoice.BOTH);
        Effect chooseSpace = new ChooseSpace(trade);

        Action encounter = new Action(inv,
                "lightningGun",
                chooseSpace,
                SituationType.ACTION
        );

        if ( GameService.getInstance().getPhases().getActualPhase().equals(PhaseTypes.ACTION)) {
            return encounter;
        }
        return null;
    }
    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        ItemBonus_GainDice bonus1 = new ItemBonus_GainDice(6, TestType.STRENGTH,SituationType.COMBAT_ENCOUNTER,this);
        boni.add(bonus1);
        return boni;
    }


}
