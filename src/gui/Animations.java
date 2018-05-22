package gui;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.concurrent.Callable;


class Animations {

    static void startRotateFromTo(Node oldNode, Node node, Group group) {
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
        startRotateFromTo(oldNode,node,pane,null);
    }

    static void startRotateFromTo(Node oldNode, Node node, Pane pane, Callable<Void> func) {
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


}
