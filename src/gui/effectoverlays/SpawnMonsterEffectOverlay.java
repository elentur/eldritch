package gui.effectoverlays;

import Service.GameService;
import gui.Animations;
import gui.InterfaceLinking;
import gui.buttons.FieldButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Field;
import model.effects.SpawnGate;
import model.effects.SpawnMonster;

public class SpawnMonsterEffectOverlay extends Overlay {
    private final SpawnMonster spawnMonster;

    public SpawnMonsterEffectOverlay(SpawnMonster spawnMonster) {
        super(spawnMonster);
        this.spawnMonster = spawnMonster;
        this.setMouseTransparent(true);
    }

    @Override
    public int init() {
        super.init();
        spawnMonster.init();
        if (spawnMonster.getMonster() == null) {
            return 0;
        }
        Field field = GameService.getInstance().getGameBoard().getField(spawnMonster.getFieldID());
        FieldButton fieldButton = InterfaceLinking.gameBoardGUI.getFieldButton(field);
        Animations.zoomTo(fieldButton);
        ImageView img = new ImageView(new Image("images/monster/" + spawnMonster.getMonster().getId() + ".jpg", 190, 130, true, true, false));
       this.getChildren().add(img);
        return 500;
    }
}
