package gui.effectoverlays;

import Service.GameService;
import gui.Animations;
import gui.InterfaceLinking;
import gui.buttons.FieldButton;
import model.Field;
import model.effects.DiscardMonster;
import model.effects.RemoveEldritchToken;

public class DiscardMonsterOverlay extends Overlay {
     private final DiscardMonster discardMonster;

    public DiscardMonsterOverlay(DiscardMonster discardMonster) {
        super(discardMonster);
        this.discardMonster = discardMonster;
        this.setMouseTransparent(true);
    }


    @Override
    public int init() {
        super.init();
        discardMonster.init();
        Field field= GameService.getInstance().getFieldOfMonster(discardMonster.getMonster().get(0));
        FieldButton fieldButton = InterfaceLinking.gameBoardGUI.getFieldButton(field);
        Animations.zoomTo(fieldButton);
        return 500;
    }
}
