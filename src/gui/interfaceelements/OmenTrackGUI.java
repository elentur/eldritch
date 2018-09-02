package gui.interfaceelements;

import Service.GameService;
import gui.Animations;
import gui.Effects;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class OmenTrackGUI extends Group {
    private final static Image greenCometImage = new Image("images/interface/Green_Comet.png", 100, 100, true, true, true);
    private final static Image blueStarsImage = new Image("images/interface/Blue_Stars.png", 100, 100, true, true, true);
    private final static Image redEclipseImage = new Image("images/interface/Red_Eclipse.png", 100, 100, true, true, true);

    private final UpdateListener listener;
    private final ImageView greenComet;
    private final ImageView blueStars1;
    private final ImageView redEclipse;
    private final ImageView blueStars2;
    private ImageView bigOne;
    private final Group circle;

    public OmenTrackGUI() {
        circle = new Group();
        greenComet = new ImageView(greenCometImage);
        greenComet.setScaleX(0.5);
        greenComet.setScaleY(0.5);
        greenComet.setTranslateX(-50);
        greenComet.setTranslateY(-125);
        blueStars1 = new ImageView(blueStarsImage);
        blueStars1.setScaleX(0.5);
        blueStars1.setScaleY(0.5);
        blueStars1.setTranslateX(-125);
        blueStars1.setTranslateY(-50);
        blueStars1.setRotate(90);
        redEclipse = new ImageView(redEclipseImage);
        redEclipse.setTranslateY(25);
        redEclipse.setTranslateX(-50);
        redEclipse.setRotate(180);
        redEclipse.setScaleX(0.5);
        redEclipse.setScaleY(0.5);
        blueStars2 = new ImageView(blueStarsImage);
        blueStars2.setTranslateX(25);
        blueStars2.setTranslateY(-50);
        blueStars2.setRotate(-90);
        blueStars2.setScaleX(0.5);
        blueStars2.setScaleY(0.5);
        Circle background = new Circle(125, Color.TRANSPARENT);
        circle.getChildren().addAll(background,greenComet,blueStars1,redEclipse,blueStars2);
        this.getChildren().addAll(circle);
        this.setEffect(Effects.dropShadow);
        listener = new UpdateListener();
        GameService.getInstance().getOmenTrack().updateProperty().addListener(listener);
        bigOne=greenComet;
        update();


    }

    public void update() {


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
