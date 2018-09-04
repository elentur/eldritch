package gui.interfaceelements;

import Service.GameService;
import enums.OmenStates;
import gui.Animations;
import gui.Effects;
import gui.buttons.Button;
import gui.buttons.OmenButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class OmenTrackGUI extends Group {
    private final static Image greenCometImage = new Image("images/interface/Green_Comet.png", 100, 100, true, true, true);
    private final static Image blueStarsImage = new Image("images/interface/Blue_Stars.png", 100, 100, true, true, true);
    private final static Image redEclipseImage = new Image("images/interface/Red_Eclipse.png", 100, 100, true, true, true);

    private final UpdateListener listener;
    private final OmenButton greenComet;
    private final OmenButton blueStars1;
    private final OmenButton redEclipse;
    private final OmenButton blueStars2;
    private OmenButton bigOne;
    private final Group circle;

    public OmenTrackGUI() {
        circle = new Group();
        greenComet = new OmenButton(greenCometImage);
        greenComet.setScaleX(0.5);
        greenComet.setScaleY(0.5);
        greenComet.setTranslateX(-50);
        greenComet.setTranslateY(-125);
        greenComet.setOnMouseClicked(event -> {
                    if (event.getButton().equals(MouseButton.PRIMARY)) {
                        deactivate(OmenStates.GREEN_COMET);
                    }
                });
        blueStars1 = new OmenButton(blueStarsImage);
        blueStars1.setScaleX(0.5);
        blueStars1.setScaleY(0.5);
        blueStars1.setTranslateX(-125);
        blueStars1.setTranslateY(-50);
        blueStars1.setRotate(90);
        blueStars1.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                deactivate(OmenStates.BLUE_STARS_1);
            }
        });
        redEclipse = new OmenButton(redEclipseImage);
        redEclipse.setTranslateY(25);
        redEclipse.setTranslateX(-50);
        redEclipse.setRotate(180);
        redEclipse.setScaleX(0.5);
        redEclipse.setScaleY(0.5);
        redEclipse.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                deactivate(OmenStates.RED_ECLIPSE);
            }
        });
        blueStars2 = new OmenButton(blueStarsImage);
        blueStars2.setTranslateX(25);
        blueStars2.setTranslateY(-50);
        blueStars2.setRotate(-90);
        blueStars2.setScaleX(0.5);
        blueStars2.setScaleY(0.5);
        blueStars2.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                deactivate(OmenStates.BULE_STARS_2);
            }
        });
        Circle background = new Circle(125, Color.TRANSPARENT);
        circle.getChildren().addAll(background,greenComet,blueStars1,redEclipse,blueStars2);
        this.getChildren().addAll(circle);
        this.setEffect(Effects.dropShadow);
        listener = new UpdateListener();
        GameService.getInstance().getOmenTrack().updateProperty().addListener(listener);
        bigOne=greenComet;
        update();


    }

    private void deactivate(OmenStates state) {
        GameService.getInstance().getOmenTrack().setEditable(false);
        greenComet.setEnabled(false);
        blueStars1.setEnabled(false);
        redEclipse.setEnabled(false);
        blueStars2.setEnabled(false);
        Animations.setOmenUnEditable(this,()->GameService.getInstance().getOmenTrack().setOmen(state));
    }

    public void update() {
        if(GameService.getInstance().getOmenTrack().isEditable()){
            Animations.setOmenEditable(this, ()->activateButtons());

        }
        switch (GameService.getInstance().getOmenTrack().getOmen()){
            case GREEN_COMET:
                Animations.rotateOmen(circle,bigOne,greenComet,0);
                bigOne=greenComet;
                break;
            case BLUE_STARS_1:
                Animations.rotateOmen(circle,bigOne,blueStars1,90);
                bigOne=blueStars1;
                break;
            case RED_ECLIPSE:
                Animations.rotateOmen(circle,bigOne,redEclipse,180);
                bigOne=redEclipse;
                break;
            case BULE_STARS_2:
                Animations.rotateOmen(circle,bigOne,blueStars2,270);
                bigOne=blueStars2;
                break;
        }

    }

    private void activateButtons() {
        greenComet.setEnabled(true);
        blueStars1.setEnabled(true);
        redEclipse.setEnabled(true);
        blueStars2.setEnabled(true);
    }

    private class UpdateListener implements ChangeListener<Boolean> {
        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if (newValue) {
                update();
                GameService.getInstance().getOmenTrack().updateProperty().setValue(false);
            }
        }
    }
}
