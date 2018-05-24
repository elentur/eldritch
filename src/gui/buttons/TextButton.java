package gui.buttons;

import gui.Effects;
import gui.Fonts;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import utils.ResourceUtil;

class TextButton extends Button {


    Label label;

    TextButton(String key, Image background) {
        super( background);
        label=new Label(ResourceUtil.get(key,"ui"));
        label.styleProperty().bind(Fonts.getFont(0.4, Fonts.DARK, Fonts.FontTyp.BOLD));
        label.setEffect(Effects.innerShadow);
        label.setMouseTransparent(true);

        this.getChildren().add(label);

    }
    @Override
    void hoverOut() {
        super.hoverOut();
        label.setEffect(Effects.innerShadow);


    }
@Override
    void hoverIn() {
        super.hoverIn();
        label.setEffect(Effects.hover);

    }

}
