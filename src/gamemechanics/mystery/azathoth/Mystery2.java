package gamemechanics.mystery.azathoth;

import Service.GameService;
import enums.EffectTyps;
import enums.EncounterType;
import enums.OldOnes;
import enums.SpendType;
import gamemechanics.Mystery;
import gamemechanics.choice.YesNoChoice;
import gamemechanics.encounter.Encounter;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import model.Effect;
import model.Item.token.GateToken;
import model.effects.AddEldritchToMystery;
import model.effects.And;
import model.effects.Spend;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Function;


public class Mystery2 extends Mystery {
    private Function<Encounter,Void> listener;


    public Mystery2() {
        super(2, OldOnes.AZATHOTH);
    }

    @Override
    public void init() {
        GameService game = GameService.getInstance();
        listener =(encounter) -> {
            if (encounter!=null && encounter.getEncounterType().equals(EncounterType.RESEARCH_ENCOUNTER)) {
                encounter.addEndEvent(e -> {
                    if (e.getResult().isSuccess() && game.getEncounteringInvestigator().getClues().size() > 0) {
                        Effect effect1 = new Spend(SpendType.CLUE,1,GameService.getInstance().getEncounteringInvestigator());
                        Effect effect2 = new AddEldritchToMystery(1);
                        Effect effect = new And(effect1,effect2);
                        YesNoChoice choice = new YesNoChoice(ResourceUtil.get("${do_you_want}","ui"),effect.getText(), Collections.singletonList(effect),null);
                        game.addChoice(choice);
                    }
                    return null;
                });
            }

            return null;
        };
        game.addEncounterListener(listener);
    }

    @Override
    public boolean isFinished() {
        boolean isFinished = getTokens().size() >= Math.ceil(GameService.getInstance().getInvestigators().size() );
        if (isFinished) {
            GameService.getInstance().removeEncounterListener(listener);
        }
        return isFinished;
    }
}
