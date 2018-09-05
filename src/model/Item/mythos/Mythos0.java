package model.Item.mythos;

import Service.GameService;
import enums.*;
import model.Item.Mythos;
import model.effects.AdvanceOmen;
import model.effects.MonsterFlood;
import model.effects.SpawnClue;

public class Mythos0 extends Mythos {

    public Mythos0() {
        super(MythosType.GREEN,Dificulty.EASY);
        getEffects().add(new AdvanceOmen(1));
        getEffects().add(new MonsterFlood());
        getEffects().add(new SpawnClue(1));
    }

    @Override
    public String getId() {
        return "&mythos0";
    }

    @Override
    public String getNameId() {
        return "${mythos_0}";
    }


}
