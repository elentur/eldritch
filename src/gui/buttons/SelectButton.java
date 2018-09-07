package gui.buttons;

import gui.Effects;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;

public class SelectButton extends Button {

    boolean isSelected;
    public SelectButton(Image image) {
        super(image);
        isSelected=true;
        actualNode.setOnMouseReleased(e -> {
            if (e.getButton().equals(MouseButton.PRIMARY)) {
                if (isEnabled()) {
                    isSelected=!isSelected;
                    if(isSelected) {
                        actualNode.setEffect(Effects.dropShadow);
                    }else{
                        actualNode.setEffect(Effects.disabled);
                    }
                }
                setArmed(false);
            }
        });
    }

    @Override
    protected void hoverOut() {

        imageView.setEffect(null);
        if(isSelected) {
            actualNode.setEffect(Effects.dropShadow);
        }else{
            actualNode.setEffect(Effects.disabled);
        }

    }

}
