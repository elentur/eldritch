package gui.effectoverlays;

import Service.GameService;
import gui.Animations;
import gui.InterfaceLinking;
import gui.buttons.FieldButton;
import model.Field;
import model.effects.SpawnClue;
import model.effects.SpawnEldritchToken;

public class SpawnEldritchTokenEffectOverlay extends Overlay {
     private final SpawnEldritchToken spawnEldritchToken;

    public SpawnEldritchTokenEffectOverlay(SpawnEldritchToken spawnEldritchToken) {
        super(spawnEldritchToken);
        this.spawnEldritchToken = spawnEldritchToken;
        this.setMouseTransparent(true);
    }

    @Override
    public int init() {
        super.init();
        spawnEldritchToken.init();
        Field field= spawnEldritchToken.getField();
        FieldButton fieldButton = InterfaceLinking.gameBoardGUI.getFieldButton(field);
        Animations.zoomTo(fieldButton);
        return 500;
    }
}
