package gamemechanics.mythos.blue;

import enums.Dificulty;
import gamemechanics.encounter.rumorencounter.RumorEncounter0;
import gamemechanics.MythosBlue;

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
