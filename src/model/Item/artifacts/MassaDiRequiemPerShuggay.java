package model.Item.artifacts;

import Service.GameService;
import enums.*;
import gamemechanics.Action;
import gamemechanics.choice.InformationChoice;
import gamemechanics.choice.MonsterChoice;
import gamemechanics.choice.YesNoChoice;
import gamemechanics.encounter.Encounter;
import model.Effect;
import model.Item.Artifact;
import model.Item.Investigator;
import model.Item.ItemBonus;
import model.effects.AdvanceDoom;
import model.effects.LooseOrGainHealthSanity;
import model.effects.NullEffect;
import utils.ResourceUtil;

import java.util.*;


public class MassaDiRequiemPerShuggay extends Artifact {

    public MassaDiRequiemPerShuggay() {
        super(ItemType.ITEM_TOME);
    }

    @Override
    public String getId() {
        return "&massaDiRequiemPerShuggay";
    }

    @Override
    public String getNameId() {
        return "${massa_di_requiem_per_shuggay}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        return boni;
    }

    @Override
    public Encounter getEncounter() {

        Investigator inv = GameService.getInstance().getEncounteringInvestigator();
        Effect effect1 = new LooseOrGainHealthSanity(SpendType.SANITY, -1, inv);
        Effect effect2 = new LooseOrGainHealthSanity(SpendType.SANITY, -1, inv);
        YesNoChoice
        Action encounter = new Action(inv,
                "massaDiRequiemPerShuggay",
                new NullEffect(),
                effect,
                new NullEffect(),
                TestType.LORE,
                -1,
                1
        );
        if (GameService.getInstance().getPhases().getActualPhase().equals(PhaseTypes.ACTION)) {

            if (!GameService.getInstance().getEncounteringInvestigator().getDoneActions().contains(encounter)) {
                GameService.getInstance().addUsedSpell(this);
                return encounter;

            }
        }
        return null;
    }

    @Override
    public void executeReckoning(Investigator inv, boolean autoFail) {
        Random r = new Random();
        int i = r.nextInt(6);
        if (i == 0) {
            Effect effect = new AdvanceDoom(1);
            InformationChoice choice = new InformationChoice(getName(),
                    effect.getText(),
                    Collections.singletonList(effect));
            GameService.getInstance().addChoice(choice);
        }
    }
}
