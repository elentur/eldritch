package gui.effectoverlays;

import Service.GameService;
import gui.Animations;
import gui.InterfaceLinking;
import gui.buttons.FieldButton;
import model.Field;
import model.effects.CloseGate;
import model.effects.SpawnGate;

public class CloseGateEffectOverlay extends Overlay {
     private final CloseGate closeGate;

    public CloseGateEffectOverlay(CloseGate closeGate) {
        super(closeGate);
        this.closeGate = closeGate;
        this.setMouseTransparent(true);
    }


    @Override
    public int init() {
        super.init();
        if(closeGate.getToken()== null){
            return 0;
        }
        Field field= GameService.getInstance().getGameBoard().getField(closeGate.getToken().getFieldID());
        FieldButton fieldButton = InterfaceLinking.gameBoardGUI.getFieldButton(field);
        Animations.zoomTo(fieldButton);
        return 500;
    }
}
