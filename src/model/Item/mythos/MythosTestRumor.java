package model.Item.mythos;

import enums.Dificulty;
import enums.ItemType;
import gamemechanics.encounter.rumorencounter.RumorEncounter0;
import model.Item.Item;
import model.Item.MythosBlue;
import model.Item.MythosGreen;
import model.effects.Reckoning;

import java.util.function.Function;

public class MythosTestRumor extends MythosBlue {

    public MythosTestRumor() {
        super(new RumorEncounter0(),Dificulty.EASY);
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
