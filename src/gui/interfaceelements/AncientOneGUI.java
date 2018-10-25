package gui.interfaceelements;

import Service.GameService;
import gui.Effects;
import gui.Fonts;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;


public class AncientOneGUI extends Group {
    private final static Image frameImage = new Image("images/interface/AncientOneInterface.png", 250, 250, true, true, true);
    private final static Image clipShape = new Image("images/interface/AncientOneInterfaceBack.png", 250, 250, true, true, true);

    private final ImageView portrait;
    private final Label doom;

    private final UpdateListener listener;
    public AncientOneGUI() {
        ImageView frame = new ImageView(frameImage);

        portrait = new ImageView();
        portrait.setTranslateY(5);
        portrait.setX(-80);
        portrait.setClip(new ImageView(clipShape));
        portrait.setImage(new Image("images/ancientones/AzathothBig.png", 212, 104, true, true, true));

        doom= new Label();
        doom.styleProperty().bind(Fonts.getFont(0.22, Fonts.WHITE, Fonts.FontTyp.BOLD));
        doom.setPrefWidth(50);
        doom.setPrefHeight(50);
        doom.setAlignment(Pos.CENTER);
        doom.setTranslateX(145);
        doom.setTranslateY(110);
        doom.setTextAlignment(TextAlignment.CENTER);

        this.getChildren().addAll(portrait, frame,doom);
        this.setEffect(Effects.dropShadow);
        listener = new UpdateListener();
        GameService.getInstance().getDoomTrack().updateProperty().addListener(listener);
        update();
    }

    public void update() {
        doom.setText(GameService.getInstance().getDoomTrack().getDoom()+"");
    }

    private class UpdateListener implements ChangeListener<Boolean> {
        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if (newValue) {
                update();
                GameService.getInstance().getDoomTrack().updateProperty().setValue(false);
            }
        }
    }
}
