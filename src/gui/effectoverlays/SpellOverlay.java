package gui.effectoverlays;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.effects.GainArtifact;
import model.effects.GainSpell;

public class SpellOverlay extends Overlay {


    public SpellOverlay(GainSpell effect) {
        super(effect);
        ImageView img = new ImageView(new Image("images/effect/Spell.png", 200, 200, true, true, false));

        setCord(effect.getInvestigator());
        this.getChildren().addAll(img);
        this.setMouseTransparent(true);
    }

}
