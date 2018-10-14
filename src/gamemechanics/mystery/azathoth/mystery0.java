package gamemechanics.mystery.azathoth;

import Service.GameService;
import enums.EffectTyps;
import enums.OldOnes;
import enums.SpendType;
import gamemechanics.Mystery;
import gamemechanics.choice.YesNoChoice;
import javafx.collections.ListChangeListener;
import model.Effect;
import model.Item.token.GateToken;
import model.effects.AddEldritchToMystery;
import model.effects.Spend;
import utils.ResourceUtil;

import java.util.Arrays;


public class mystery0 extends Mystery {
    private ListChangeListener<? super Effect> listener;


    public mystery0() {
        super(0, OldOnes.AZATHOTH);
    }

    @Override
    public void init() {
        GameService game = GameService.getInstance();
        listener = e -> {
            if (!game.getInsertions().isEmpty() &&
                    game.getInsertions().get(0).getEffectType().equals(EffectTyps.CLOSE_GATE)
                    && game.getEncounteringInvestigator().getClues().size() > 0) {
               GateToken gate = game.getFieldOfInvestigator(GameService.getInstance().getEncounteringInvestigator()).getGate();
               if(gate != null && gate.getOmenState().equals(game.getOmenTrack().getOmen())) {
                   game.getEncounterProperty().get().getDiscardEffects().add(() -> {
                       Effect effect1 = new Spend(SpendType.CLUE, 1, game.getEncounteringInvestigator());
                       Effect effect2 = new AddEldritchToMystery(1);
                       YesNoChoice choice = new YesNoChoice(ResourceUtil.get("${mystery}", "ui"), this.getText()
                               , Arrays.asList(effect1, effect2), null);
                       game.addChoice(choice);
                   });
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
