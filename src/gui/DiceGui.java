package gui;

import container.Die;
import container.Result;
import javafx.animation.AnimationTimer;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import model.Effect;
import oldVersion.gameItems.Dice;
import org.w3c.dom.css.Rect;

import java.util.Random;

class DiceGui extends Group {

    private static TriangleMesh mesh;
    private final MeshView meshView;
    private final static Image backgroundImage = new Image("images/dice.png");
    private final static Image rerollImage = new Image("images/flip.png");
    private final static Image shiftImage = new Image("images/flip.png");
    private final Label label;

    private IntegerProperty animationDone=new SimpleIntegerProperty(0);
    static double cubeWidth = 50;

    public Button rerollButton;
    public Button shiftButton;

    static {
        float hw = (float) (cubeWidth / 2f);
        float hh = (float) (cubeWidth / 2f);
        float hd = (float) (cubeWidth / 2f);

        float points[] = {
                hw, hh, hd,
                hw, hh, -hd,
                hw, -hh, hd,
                hw, -hh, -hd,
                -hw, hh, hd,
                -hw, hh, -hd,
                -hw, -hh, hd,
                -hw, -hh, -hd};

        float tex[] = {
                0.25f, 0,
                0.5f, 0,
                0, 0.33f,
                0.25f, 0.33f,
                0.5f, 0.33f,
                0.75f, 0.33f,
                1, 0.33f,
                0, 0.66f,
                0.25f, 0.66f,
                0.5f, 0.66f,
                0.75f, 0.66f,
                1, 0.66f,
                0.25f, 1f,
                0.5f, 1f};

        int faces[] = {
                0, 10, 2, 5, 1, 9,
                2, 5, 3, 4, 1, 9,
                4, 7, 5, 8, 6, 2,
                6, 2, 5, 8, 7, 3,
                0, 13, 1, 9, 4, 12,
                4, 12, 1, 9, 5, 8,
                2, 1, 6, 0, 3, 4,
                3, 4, 6, 0, 7, 3,
                0, 10, 4, 11, 2, 5,
                2, 5, 4, 11, 6, 6,
                1, 9, 3, 4, 5, 8,
                5, 8, 3, 4, 7, 3};

        mesh = new TriangleMesh();
        mesh.getPoints().addAll(points);
        mesh.getTexCoords().addAll(tex);
        mesh.getFaces().addAll(faces);
    }


    DiceGui() {
         rerollButton = new Button(new Rectangle(20,20,new ImagePattern(rerollImage)));
        rerollButton.setTranslateZ(-100);
        rerollButton.setVisible(false);
         shiftButton = new Button(new Rectangle(20,20,new ImagePattern(shiftImage)));
        shiftButton.setTranslateZ(-100);
        shiftButton.setTranslateY(-40);
        shiftButton.setVisible(false);
        PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setSpecularColor(Color.WHITE);
        redMaterial.setDiffuseMap(backgroundImage);
        meshView = new MeshView(mesh);
        meshView.setMaterial(redMaterial);
        label = new Label();
        label.setMouseTransparent(true);
        label.setTranslateZ(-100);
        label.setTranslateY(-35);
        label.setTranslateX(-8);
        label.getStyleClass().add("text-stroke-black");
        this.getChildren().addAll(meshView, label,rerollButton,shiftButton);


    }



    private Point2D setDie(Die die) {
        // 0 0 0 1
        // 0 90 0 2
        // 0 180 0 3
        // 0 270 0 4
        // 90 0 0 5
        // 270 0 0 6

        label.setText(die.getValue() + "");
        Color color = Fonts.WHITE;
        double size = 0.4;
        label.styleProperty().bind(Fonts.getFont(size,color, Fonts.FontTyp.BOLD));

        switch (die.getValue()) {
            case 1:
                return new Point2D(0, 0);
            case 2:
                return new Point2D(0, 90);
            case 3:
                return new Point2D(0, 180);
            case 4:
                return new Point2D(0, 270);
            case 5:
                return new Point2D(90, 0);
            default:
                return new Point2D(270, 0);

        }
    }

    public void rollDie(Die die) {
        Point2D p = setDie(die);
        animationDone.setValue(0);
        label.setVisible(false);
        Rotate rX = new Rotate(0, Rotate.X_AXIS);
        Rotate rY = new Rotate(0, Rotate.Y_AXIS);
        Rotate rZ = new Rotate(0, Rotate.Z_AXIS);
        meshView.getTransforms().clear();
        meshView.getTransforms().addAll(rX, rY, rZ);
        startAnimation(rX, p.getX(),false);
        startAnimation(rY, p.getY(),false);
        startAnimation(rZ, 0,false);
        animationDone.addListener(e->{
            if(animationDone.getValue()==3){
               label.setVisible(true);
            }
        });
    }
    public void shiftDie(Die die) {
        Point2D p = setDie(die);
        animationDone.setValue(0);
        label.setVisible(false);
        Rotate rX = new Rotate(0, Rotate.X_AXIS);
        Rotate rY = new Rotate(0, Rotate.Y_AXIS);
        Rotate rZ = new Rotate(0, Rotate.Z_AXIS);
        meshView.getTransforms().clear();
        meshView.getTransforms().addAll(rX, rY, rZ);
        startAnimation(rX, p.getX(),true);
        startAnimation(rY, p.getY(),true);
        startAnimation(rZ, 0,true);
        animationDone.addListener(e->{
            if(animationDone.getValue()==3){
                label.setVisible(true);
            }
        });
    }

    private void startAnimation(Rotate rotate, double value, boolean shift) {
        final Random r = new Random();
        final double rotationSpeed = 500;
        AnimationTimer timer = new AnimationTimer() {

            double pos = shift?value:r.nextInt(360);
            int step = shift?2:0;

            private long lastUpdate = 0;

            @Override
            public void handle(long time) {
                if (lastUpdate > 0) {
                    long elapsedNanos = time - lastUpdate;
                    double elapsedSeconds = elapsedNanos / 1_000_000_000.0;
                    double delta = 0;
                    if (step < 3) {
                        delta = rotationSpeed * elapsedSeconds;
                    } else {
                        animationDone.setValue(animationDone.getValue()+1);
                        stop();
                    }
                    rotate.setAngle(clamp(rotate.getAngle() + delta, 0, pos));
                    if (rotate.getAngle() == pos) {
                        step++;
                        if (step == 1) {
                            pos = r.nextInt(360);
                        } else if (step == 2) {
                            pos = value;

                        }
                    }
                }

                lastUpdate = time;
            }
        };

        timer.start();
    }

    private double clamp(double value, double min, double max) {
        return Math.min(max, Math.max(min, value));
    }
}
