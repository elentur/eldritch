package model.Item.artifacts;

import Service.GameService;
import enums.*;
import gamemechanics.encounter.OtherWorldEncounter;
import javafx.beans.value.ChangeListener;
import model.Effect;
import model.Item.Artifact;
import model.Item.ItemBonus;
import model.Item.boni.ItemBonus_AdditionalDice;
import model.effects.GainClue;

import java.util.ArrayList;
import java.util.List;


public class GateBox extends Artifact {

    private ChangeListener listener;

    public GateBox() {
        super(ItemType.ITEM_MAGICAL);
    }

    @Override
    public String getId() {
        return "&gateBox";
    }

    @Override
    public String getNameId() {
        return "${gate_box}";
    }

    @Override
    public void discard() {
        super.discard();
        GameService.getInstance().getEncounterProperty().removeListener(listener);
    }

    @Override
    public List<Effect> getDrawEffects() {
        listener = (a, oldValue, newValue) -> {
            if (oldValue instanceof OtherWorldEncounter) {
                OtherWorldEncounter encounter = (OtherWorldEncounter) oldValue;
                if (encounter.isGateClosed()) {
                    GameService.getInstance().addEffect(new GainClue(EffectSelector.RANDOM, 1, GameService.getInstance().getEncounteringInvestigator()));
                }
            }
        };
        GameService.getInstance().getEncounterProperty().addListener(listener);
        return super.getDrawEffects();
    }

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        ItemBonus_AdditionalDice bonus1 = new ItemBonus_AdditionalDice(1, TestType.ALL, SituationType.OTHER_WORLD_ENCOUNTER, RangeType.LOCAL, FieldType.ALL, this);
        boni.add(bonus1);
        return boni;
    }


}
