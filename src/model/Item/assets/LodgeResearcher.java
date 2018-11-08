package model.Item.assets;

import Service.GameService;
import enums.EffectSelector;
import enums.EncounterType;
import enums.ItemType;
import enums.SpendType;
import gamemechanics.encounter.CombatEncounter;
import gamemechanics.encounter.Encounter;
import model.Effect;
import model.Item.Asset;
import model.Item.Investigator;
import model.Item.ItemBonus;
import model.effects.And;
import model.effects.GainClue;
import model.effects.LooseOrGainHealthSanity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


public class LodgeResearcher extends Asset {

    private Function<Encounter,Encounter> listener;
    public LodgeResearcher() {
        super(ItemType.ALLEY, 3);
    }

    @Override
    public String getId() {
        return "&lodgeResearcher";
    }

    @Override
    public String getNameId() {
        return "${lodge_researcher}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        return new ArrayList<>();
    }

    @Override
    public List<Effect> getDrawEffects(Investigator investigator) {
        init(investigator);
        return super.getDrawEffects(investigator);
    }

    private void init(Investigator investigator) {
        GameService game = GameService.getInstance();
        listener =(encounter) -> {
            if (encounter!=null && encounter.getEncounterType().equals(EncounterType.COMBAT_ENCOUNTER) &&
                    investigator.getInventory().contains(LodgeResearcher.this)) {
                encounter.addEndEvent(e -> {
                    if (((CombatEncounter)encounter).getActiveMonster().getActualToughness()<=0) {
                        Effect effect1 = new GainClue(EffectSelector.RANDOM,1,investigator);
                        Effect effect2 = new LooseOrGainHealthSanity(SpendType.SANITY,1,investigator);
                        Effect effect = new And(effect1,effect2);
                        game.addEffect(effect);
                    }
                    return null;
                });
            }

            return encounter;
        };
        game.addEncounterListener(listener);
    }

    @Override
    public void discard() {
        GameService.getInstance().removeEncounterListener(listener);
        super.discard();
    }
}
