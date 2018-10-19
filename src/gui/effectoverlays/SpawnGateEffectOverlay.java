package gui.effectoverlays;

import Service.GameService;
import gui.Animations;
import gui.InterfaceLinking;
import gui.buttons.FieldButton;
import model.Field;
import model.effects.CloseGate;
import model.effects.SpawnClue;
import model.effects.SpawnGate;

public class SpawnGateEffectOverlay extends Overlay {
     private final SpawnGate spawnGate;

    public SpawnGateEffectOverlay(SpawnGate spawnGate) {
        super(spawnGate);
        this.spawnGate = spawnGate;
        this.setMouseTransparent(true);
    }


    @Override
    public int init() {
        spawnGate.init();
        super.init();
        if(spawnGate.getToken()== null){
            return 0;
        }
        Field field= GameService.getInstance().getGameBoard().getField(spawnGate.getToken().getFieldID());
        FieldButton fieldButton = InterfaceLinking.gameBoardGUI.getFieldButton(field);
        Animations.zoomTo(fieldButton);
        return 500;
    }
}
