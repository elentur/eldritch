package gamemechanics.mystery.azathoth;

import Service.GameService;
import enums.FieldID;
import enums.OldOnes;
import gamemechanics.Mystery;
import gamemechanics.encounter.mysterieencounter.azathoth.MysteryEncounter0;
import model.Field;
import model.Item.token.MysteryToken;

public class mystery1 extends Mystery {


    public mystery1() {
        super(1, OldOnes.AZATHOTH);
    }

    @Override
    public void init() {
      Field field = GameService.getInstance().getGameBoard().getField(FieldID.TUNGUSKA);
        MysteryToken mysteryToken = new MysteryToken(new MysteryEncounter0());
      field.addMystery(mysteryToken);
    }

    @Override
    public boolean isFinished() {
        return getTokens().size()>= Math.ceil(GameService.getInstance().getInvestigators().size()/2);
    }
}
