package model.Item.artifacts;

import Service.GameService;
import enums.*;
import gamemechanics.encounter.CombatEncounter;
import javafx.beans.value.ChangeListener;
import model.Effect;
import model.Item.Artifact;
import model.Item.Investigator;
import model.Item.ItemBonus;
import model.Item.boni.ItemBonus_GainDice;
import model.effects.LooseOrGainHealthSanity;

import java.util.ArrayList;
import java.util.List;


public class SwordOfSaintJerome extends Artifact {

    public SwordOfSaintJerome() {
        super(ItemType.ITEM_WEAPON_MAGICAL);
    }

    private ChangeListener listener;

    @Override
    public String getId() {
        return "&swordOfSaintJerome";
    }

    @Override
    public String getNameId() {
        return "${sword_of_saint_jerome}";
    }
    @Override
    public void discard() {
        super.discard();
        GameService.getInstance().getEncounterProperty().removeListener(listener);
    }

    @Override
    public List<Effect> getDrawEffects(Investigator investigator) {
        listener = (a, oldValue, newValue) -> {
            if (oldValue instanceof CombatEncounter) {
                CombatEncounter encounter = (CombatEncounter) oldValue;
                if (encounter.getActiveMonster().getActualToughness()<=0) {
                    GameService.getInstance().addEffect(new LooseOrGainHealthSanity(SpendType.SANITY, 1, investigator));
                }
            }
        };
        GameService.getInstance().getEncounterProperty().addListener(listener);
        return super.getDrawEffects(investigator);
    }
    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        ItemBonus_GainDice bonus1 = new ItemBonus_GainDice(5,TestType.STRENGTH,SituationType.COMBAT_ENCOUNTER,this);
        boni.add(bonus1);
        ItemBonus_GainDice bonus2 = new ItemBonus_GainDice(2,TestType.WILL,SituationType.COMBAT_ENCOUNTER,this);
        boni.add(bonus2);
        return boni;
    }


}
