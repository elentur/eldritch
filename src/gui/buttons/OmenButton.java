package gui.buttons;

import javafx.scene.image.Image;

public class OmenButton extends Button {
    public OmenButton(Image image) {
        super(image);
    actualNode.setPickOnBounds(true);
    setDisabledEffect(null);
    setEnabled(false);

    }
}
