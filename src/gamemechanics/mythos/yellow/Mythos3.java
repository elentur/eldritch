package gamemechanics.mythos.yellow;

import Service.GameService;
import enums.Dificulty;
import enums.ItemType;
import gamemechanics.MythosYellow;
import model.effects.GainAsset;

public class Mythos3 extends MythosYellow {

    public Mythos3() {
        super(Dificulty.EASY);
    }

    @Override
    public void execute() {
        this.getEffects().add(new GainAsset(ItemType.ALLEY, GameService.getInstance().getStartInvestigator()));
        super.execute();
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