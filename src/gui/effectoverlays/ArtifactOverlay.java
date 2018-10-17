package gui.effectoverlays;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.effects.GainArtifact;
import model.effects.GainAsset;

public class ArtifactOverlay extends Overlay {


    public ArtifactOverlay(GainArtifact effect) {
        super(effect);
        ImageView img = new ImageView(new Image("images/effect/Artifact.png", 200, 200, true, true, false));

        setCord(effect.getInvestigator());
        this.getChildren().addAll(img);
        this.setMouseTransparent(true);
    }

}
