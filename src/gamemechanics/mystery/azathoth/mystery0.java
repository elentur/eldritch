package gamemechanics.mystery.azathoth;

import Service.GameService;
import enums.OldOnes;
import gamemechanics.Mystery;
import model.Item.token.EldritchToken;

public class mystery0 extends Mystery {


    public mystery0() {
        super(0, OldOnes.AZATHOTH);
    }

    @Override
    public void init() {
        addToken(new EldritchToken(null));
        addToken(new EldritchToken(null));
    }

    @Override
    public boolean isFinished() {
        return getTokens().size()>= Math.ceil(GameService.getInstance().getInvestigators().size()/2);
    }
}
