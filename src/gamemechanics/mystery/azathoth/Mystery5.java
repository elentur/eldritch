package gamemechanics.mystery.azathoth;

import Service.GameService;
import enums.FieldID;
import enums.OldOnes;
import gamemechanics.Mystery;
import model.Effect;
import model.Item.epicmonster.Tulzscha;
import model.effects.SpawnMonster;

public class Mystery5 extends Mystery {

    private Tulzscha tulzscha;
    public Mystery5() {
        super(5, OldOnes.AZATHOTH);
    }


    @Override
    public void init() {
    tulzscha = new Tulzscha();
        Effect effect = new SpawnMonster(tulzscha,GameService.getInstance().getRandomField());
        GameService.getInstance().addEffect(effect);
    }

    @Override
    public boolean isFinished() {
        return tulzscha.getActualToughness()<=0;
    }
}
