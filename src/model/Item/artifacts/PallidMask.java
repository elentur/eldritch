package model.Item.artifacts;

import Service.GameService;
import enums.*;
import gamemechanics.ActionEncounter;
import gamemechanics.choice.EncounterChoice;
import gamemechanics.encounter.Encounter;
import gamemechanics.encounter.OtherWorldEncounter;
import javafx.beans.value.ChangeListener;
import model.Effect;
import model.Field;
import model.Item.Artifact;
import model.Item.Investigator;
import model.Item.ItemBonus;
import model.Item.boni.ItemBonus_AdditionalDice;
import model.effects.GainClue;
import model.effects.NullEffect;

import java.util.ArrayList;
import java.util.List;


public class PallidMask extends Artifact {



    public PallidMask() {
        super(ItemType.ITEM_MAGICAL);
    }

    @Override
    public String getId() {
        return "&pallidMask";
    }

    @Override
    public String getNameId() {
        return "${pallid_mask}";
    }
    @Override
    public Encounter getEncounter() {

        Investigator inv = GameService.getInstance().getEncounteringInvestigator();
        Effect effect = new Effect(EffectTyps.CUSTOM) {

            @Override
            public void execute() {
                Field field = GameService.getInstance().getFieldOfInvestigator(inv);
                GameService.getInstance().addChoice(new EncounterChoice(field));
            }

            @Override
            public String getText() {
                return "";
            }
        };

        ActionEncounter encounter =  new ActionEncounter(inv,
                "pallidMask",
                effect,
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
        return boni;
    }


}
