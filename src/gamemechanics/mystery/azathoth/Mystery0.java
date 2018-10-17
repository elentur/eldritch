package gamemechanics.mystery.azathoth;

import Service.GameService;
import enums.EffectTyps;
import enums.OldOnes;
import enums.SpendType;
import gamemechanics.Mystery;
import gamemechanics.choice.YesNoChoice;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import model.Effect;
import model.Item.token.GateToken;
import model.effects.AddEldritchToMystery;
import model.effects.And;
import model.effects.CloseGate;
import model.effects.Spend;
import utils.ResourceUtil;

import java.util.Arrays;


public class Mystery0 extends Mystery {
    private ListChangeListener<? super Effect> listener;


    public Mystery0() {
        super(0, OldOnes.AZATHOTH);
    }

    @Override
    public void init() {
        GameService game = GameService.getInstance();
        listener = e -> {
            e.next();
            if (e.wasAdded() &&
                    e.getAddedSubList().get(0).getEffectType().equals(EffectTyps.CLOSE_GATE)
                    && game.getEncounteringInvestigator().getClues().size() > 0) {
                GateToken token = ((CloseGate) e.getAddedSubList().get(0)).getToken();
                if (token != null && token.getOmenState().equals(game.getOmenTrack().getOmen())) {
                    Effect effect1 = new Spend(SpendType.CLUE, 1, game.getEncounteringInvestigator());
                    Effect effect2 = new AddEldritchToMystery(1);
                    Effect effect = new And(effect1, effect2);
                    YesNoChoice choice = new YesNoChoice(ResourceUtil.get("${mystery}", "ui"), this.getText()
                            , null, null);
                    effect.setCondition(choice);
                    if (game.getEncounterProperty().getValue() != null) {
                        game.getEncounterProperty().getValue().addEndEvent(a -> {
                            game.addEffect(effect);
                            return null;
                        });
                    } else {
                        Platform.runLater(()->game.addEffect(effect));
                    }

                }


            }
        };
        game.getInsertions().addListener(listener);
    }

    @Override
    public boolean isFinished() {
        boolean isFinished = getTokens().size() >= Math.ceil(GameService.getInstance().getInvestigators().size() / 2);
        if (isFinished) {
            GameService.getInstance().getInsertions().removeListener(listener);
        }
        return isFinished;
    }
}
