package gamemechanics.mystery.azathoth;

import Service.GameService;
import enums.FieldID;
import enums.OldOnes;
import gamemechanics.Mystery;
import gamemechanics.encounter.mysterieencounter.azathoth.MysteryEncounter1;
import model.Effect;
import model.Field;
import model.Item.epicmonster.Tulzscha;
import model.Item.token.EldritchToken;
import model.effects.SpawnEldritchToken;
import model.effects.SpawnMonster;
import oldVersion.gameBuild.Game;

import java.util.HashSet;

public class Mystery5 extends Mystery {

    private Tulzscha tulzscha;
    public Mystery5() {
        super(5, OldOnes.AZATHOTH);
    }


    @Override
    public void init() {
    tulzscha = new Tulzscha();
        Effect effect = new SpawnMonster(tulzscha,GameService.getInstance().getGameBoard().getField(FieldID.LONDON)); //GameService.getInstance().getRandomField());
        GameService.getInstance().addEffect(effect);
    }

    @Override
    public boolean isFinished() {
        return tulzscha.getActualToughness()<=0;
    }
}
