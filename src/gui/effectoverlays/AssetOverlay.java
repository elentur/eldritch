package gui.effectoverlays;

import gui.Fonts;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.effects.AssetFromReserve;
import model.effects.Loose;
import model.effects.RandomAsset;

public class AssetOverlay extends Overlay {


    public AssetOverlay(RandomAsset effect) {
        super(effect);
        ImageView img = new ImageView(new Image("images/effect/Asset.jpg", 200, 200, true, true, false));

        setCord(effect.getInvestigator());
        this.getChildren().addAll(img);
        this.setMouseTransparent(true);
    }
    public AssetOverlay(AssetFromReserve effect) {
        super(effect);
        ImageView img = new ImageView(new Image("images/effect/Asset.jpg", 200, 200, true, true, false));
        setCord(effect.getInvestigator());
        this.getChildren().addAll(img);
        this.setMouseTransparent(true);
    }
}
