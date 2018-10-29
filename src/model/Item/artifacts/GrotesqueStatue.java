package model.Item.artifacts;

import Service.GameService;
import enums.*;
import gamemechanics.Test;
import gamemechanics.choice.YesNoChoice;
import gamemechanics.encounter.OtherWorldEncounter;
import javafx.beans.value.ChangeListener;
import model.Effect;
import model.Item.Artifact;
import model.Item.ItemBonus;
import model.Item.boni.ItemBonus_AdditionalDice;
import model.Item.boni.ItemBonus_PreventLossOfHealthOrSanity;
import model.effects.GainClue;
import model.effects.Spend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class GrotesqueStatue extends Artifact {


    public GrotesqueStatue() {
        super(ItemType.ITEM_MAGICAL);
    }

    @Override
    public String getId() {
        return "&grotesqueStatue";
    }

    @Override
    public String getNameId() {
        return "${grotesque_statue}";
    }

    @Override
    public void discard() {
        super.discard();
    }

    @Override
    public List<Effect> getDrawEffects() {
        GameService.getInstance().addEffect(new GainClue(EffectSelector.RANDOM, 5, GameService.getInstance().getEncounteringInvestigator()));
        return super.getDrawEffects();
    }

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();

        ItemBonus_PreventLossOfHealthOrSanity bonus1 = new ItemBonus_PreventLossOfHealthOrSanity(-1, SpendType.SANITY, SituationType.SPELL_EFFECT, RangeType.SELF, null, this);
        Spend spendClue = new Spend(SpendType.CLUE, 1, GameService.getInstance().getEncounteringInvestigator());
        YesNoChoice yesNoChoice = new YesNoChoice(this.getName(), spendClue.getText() + "\n" + bonus1.getText(), Collections.singletonList(spendClue), null);
        bonus1.setCondition(yesNoChoice);
        boni.add(bonus1);
        return boni;
    }


}
