package model.Item.artifacts;

import Service.GameService;
import enums.*;
import gamemechanics.Action;
import gamemechanics.choice.InformationChoice;
import gamemechanics.choice.MonsterChoice;
import gamemechanics.choice.YesNoChoice;
import gamemechanics.encounter.Encounter;
import model.Effect;
import model.Field;
import model.Item.Artifact;
import model.Item.Investigator;
import model.Item.ItemBonus;
import model.Item.Monster;
import model.effects.*;
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
        List<Monster> monsters = new ArrayList<>();
        for (Field field : GameService.getInstance().getGameBoard().getFields()) {
            if (field.hasGate()) {
                monsters.addAll(field.getMonster());
            }
        }
        if (monsters.isEmpty()) {
            return null;
        }
        MonsterChoice choice = new MonsterChoice(monsters, ChoiceType.MONSTER_CHOICE);
        Effect effect2 = new DiscardMonster(choice);
        Effect effect = new And(effect2, effect1);
        // effect.setCondition(new YesNoChoice(ResourceUtil.get("${do_you_want}","ui"),effect.getText(), null,null));

        Action encounter = new Action(inv,
                "massaDiRequiemPerShuggay",
                new NullEffect(),
                effect,
                new NullEffect(),
                TestType.LORE,
                -1,
                1,
                SituationType.ACTION
        );
        return encounter;
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
