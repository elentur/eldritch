package gui;

import Service.GameService;
import gui.buttons.Button;
import gui.effectoverlays.Overlay;
import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Effect;

import java.util.concurrent.Callable;


public class Animations {

    private static boolean effectOverlayIsRunning;

    public static void startRotateFromTo(Node oldNode, Node node, Group group) {
        ScaleTransition st1 = new ScaleTransition(Duration.millis(70), group);
        st1.setByX(-1);
        st1.playFromStart();
        st1.setOnFinished(a -> {
            group.getChildren().remove(oldNode);
            group.getChildren().add(node);
            // st1.setRate(-1.0);
            st1.setByX(1.0f);
            st1.play();
            st1.setOnFinished(null);
        });
    }

    static void startRotateFromTo(Node oldNode, Node node, Pane pane) {
        startRotateFromTo(oldNode, node, pane, null);
    }

    public static void startRotateFromTo(Node oldNode, Node node, Pane pane, Callable<Void> func) {
        ScaleTransition st1 = new ScaleTransition(Duration.millis(70), pane);
        st1.setByX(-1);
        st1.playFromStart();
        st1.setOnFinished(a -> {
            pane.getChildren().remove(oldNode);
            pane.getChildren().add(node);
            // st1.setRate(-1.0);
            st1.setByX(1.0f);
            st1.play();
            st1.setOnFinished(e -> {
                if (func != null) {
                    try {
                        func.call();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
        });
    }

    static void fadeIn(Node node, double duration) {
        FadeTransition st1 = new FadeTransition(Duration.millis(duration), node);
        st1.setFromValue(0);
        st1.setToValue(1);
        st1.playFromStart();

    }

    static void scroll(ScrollPane pane, double v, Button button) {
        final double scrollSpeed = 1.5;

        AnimationTimer timer = new AnimationTimer() {

            private long lastUpdate = 0;

            @Override
            public void handle(long time) {
                if (lastUpdate > 0) {
                    long elapsedNanos = time - lastUpdate;
                    double elapsedSeconds = elapsedNanos / 1_000_000_000.0;
                    double delta = 0;

                    if (button.isArmed()) {
                        delta = v * scrollSpeed * elapsedSeconds;

                    } else {
                        stop();
                    }
                    double newValue =
                            clamp(pane.getVvalue() + delta, pane.getVmin(), pane.getVmax());
                    pane.setVvalue(newValue);
                }
                lastUpdate = time;
            }
        };

        timer.start();
    }

    private static double clamp(double value, double min, double max) {
        return Math.min(max, Math.max(min, value));
    }


    public static void effectOverlayAnimations(Overlay group, Stage activeStage, Effect effect) {
        if (!(activeStage.getScene().getRoot() instanceof StackPane) || effectOverlayIsRunning) {
            return;
        }
        effectOverlayIsRunning = true;
        StackPane root = (StackPane) activeStage.getScene().getRoot();
        EffectLayer pane = (EffectLayer)root.getChildren().get(root.getChildren().size()-1);
        group.setEffect(Effects.dropShadow);
        pane.getChildren().add(group);

        ScaleTransition st2 = new ScaleTransition(Duration.millis(200), group);
        st2.setFromX(5);
        st2.setToX(0.5);
        st2.setFromY(5);
        st2.setToY(0.5);

        TranslateTransition tt = new TranslateTransition(Duration.millis(200), group);
        tt.setFromX(0);
        tt.setToX(group.getX());
        tt.setFromY(0);
        tt.setToY(group.getY());

        FadeTransition st1 = new FadeTransition(Duration.millis(1000), group);
        st1.setDelay(Duration.millis(500));
        st1.setFromValue(1);
        st1.setToValue(0);
        st1.setOnFinished(a -> {
            effectOverlayIsRunning = false;
            pane.getChildren().remove(group);
            GameService.getInstance().getInsertions().remove(effect);

        });

        ParallelTransition t = new ParallelTransition(st2,tt, st1);
        t.playFromStart();


    }

    public static void startInvestigatorScroll(Node node1, Node node2, double w) {
        AnimationTimer timer = new AnimationTimer() {

            private long lastUpdate = 0;
            private double speed = 10;

            @Override
            public void handle(long time) {
                if (lastUpdate > 0) {
                    node1.setLayoutY(node1.getLayoutY() + speed);
                    node2.setLayoutY(node2.getLayoutY() - speed);
                    if (node2.getLayoutY() <= 0) {
                        node1.setLayoutY(0);
                        node2.setLayoutY(0);
                        stop();
                    }
                }
                lastUpdate = time;
            }
        };

        timer.start();
    }

    public static void zoomTo(Node node) {
        AnimationTimer timer = new AnimationTimer() {

            private long lastUpdate = 0;

            double scaleX =  InterfaceLinking.gameBoardGUI.getZoomGroup().getScaleX();
            double scaleY =  InterfaceLinking.gameBoardGUI.getZoomGroup().getScaleY();

            double x = ((node.getTranslateX()+100)*scaleX-960) /(InterfaceLinking.gameBoardGUI.getMap().getLayoutBounds().getWidth()*scaleX-1920);
            double y = ((node.getTranslateY()+100)*scaleY-640) /(InterfaceLinking.gameBoardGUI.getMap().getLayoutBounds().getHeight()*scaleY-1280);
           private int speed =20;
            private double speedX = (x-InterfaceLinking.gameBoardGUI.getScrollPane().getHvalue())/speed;
            private double speedY = (y-InterfaceLinking.gameBoardGUI.getScrollPane().getVvalue())/speed;
            private int count=0;
            @Override
            public void handle(long time) {
                if (lastUpdate > 0) {
                    InterfaceLinking.gameBoardGUI.getScrollPane().setVvalue(InterfaceLinking.gameBoardGUI.getScrollPane().getVvalue()+speedY);
                    InterfaceLinking.gameBoardGUI.getScrollPane().setHvalue(InterfaceLinking.gameBoardGUI.getScrollPane().getHvalue()+speedX);
                    if (count>=speed) {
                        stop();
                    }
                    count++;
                }
                lastUpdate = time;
            }
        };

        timer.start();
    }
}
