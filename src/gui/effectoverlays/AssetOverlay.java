package gui.effectoverlays;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.effects.AssetFromReserve;
import model.effects.GainAsset;

public class AssetOverlay extends Overlay {


    public AssetOverlay(GainAsset effect) {
        super(effect);
        ImageView img = new ImageView(new Image("images/effect/Asset.png", 200, 200, true, true, false));

        setCord(effect.getInvestigator());
        this.getChildren().addAll(img);
        this.setMouseTransparent(true);
    }

}
