package gui.effectoverlays;

import Service.GameService;
import gui.Animations;
import gui.InterfaceLinking;
import gui.buttons.FieldButton;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Field;
import model.effects.Move;
import model.effects.SpawnClue;

public class SpawnClueEffectOverlay extends Overlay {
     private final SpawnClue spawnClue;

    public SpawnClueEffectOverlay(SpawnClue spawnClue) {
        super(spawnClue);
        this.spawnClue = spawnClue;
        this.setMouseTransparent(true);
    }

    @Override
    public int init() {
        super.init();
        spawnClue.init();
        Field field= GameService.getInstance().getGameBoard().getField(spawnClue.getToken().getFieldID());
        FieldButton fieldButton = InterfaceLinking.gameBoardGUI.getFieldButton(field);
        Animations.zoomTo(fieldButton);
        return 500;
    }
}
