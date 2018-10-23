package gui.effectoverlays;

import Service.GameService;
import gui.Animations;
import gui.InterfaceLinking;
import gui.buttons.FieldButton;
import model.Field;
import model.effects.CloseGate;
import model.effects.RemoveEldritchToken;

public class RemoveEldritchTokenEffectOverlay extends Overlay {
     private final RemoveEldritchToken removeEldritchToken;

    public RemoveEldritchTokenEffectOverlay(RemoveEldritchToken removeEldritchToken) {
        super(removeEldritchToken);
        this.removeEldritchToken = removeEldritchToken;
        this.setMouseTransparent(true);
    }


    @Override
    public int init() {
        super.init();
        if(removeEldritchToken.getToken()== null){
            return 0;
        }
        Field field=  GameService.getInstance().getGameBoard().getField(removeEldritchToken.getFieldID());
        FieldButton fieldButton = InterfaceLinking.gameBoardGUI.getFieldButton(field);
        Animations.zoomTo(fieldButton);
        return 500;
    }
}
