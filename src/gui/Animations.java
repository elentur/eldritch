package gui;

import Service.GameService;
import gui.buttons.Button;
import gui.effectoverlays.Overlay;
import gui.interfaceelements.OmenTrackGUI;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.SVGPath;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Effect;
import model.Field;
import model.effects.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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


    public static void effectOverlayAnimations(Overlay overlay, Stage activeStage, Effect effect) {

        if (!(activeStage.getScene().getRoot() instanceof StackPane) || effectOverlayIsRunning) {
            return;
        }

        effectOverlayIsRunning = true;
        int delay = overlay.init();
        if (delay < 0) {
            effectOverlayIsRunning = false;
            Platform.runLater(() ->
                    GameService.getInstance().getInsertions().remove(effect));
            return;
        }


        StackPane root = (StackPane) activeStage.getScene().getRoot();
        EffectLayer pane = (EffectLayer) root.getChildren().get(root.getChildren().size() - 1);

        PauseTransition st0 = new PauseTransition(Duration.millis(1));
        st0.setOnFinished(e -> {
            effect.execute();
            overlay.setEffect(Effects.dropShadow);
            pane.getChildren().add(overlay);
        });
        ScaleTransition st2 = new ScaleTransition(Duration.millis(200), overlay);
        st2.setFromX(5);
        st2.setToX(0.5);
        st2.setFromY(5);
        st2.setToY(0.5);

        TranslateTransition tt = new TranslateTransition(Duration.millis(200), overlay);
        tt.setFromX(0);
        tt.setToX(overlay.getX());
        tt.setFromY(0);
        tt.setToY(overlay.getY());

        FadeTransition st1 = new FadeTransition(Duration.millis(400), overlay);
        st1.setDelay(Duration.millis(100));
        st1.setFromValue(1);
        st1.setToValue(0);


        ParallelTransition t = new ParallelTransition(st0, st2, tt, st1);
        t.setDelay(Duration.millis(delay));
        t.setOnFinished(a -> {
            effectOverlayIsRunning = false;
            pane.getChildren().remove(overlay);
            GameService.getInstance().getInsertions().remove(effect);

        });
        t.playFromStart();


    }

    public static void effectOverlayMove(Overlay overlay, Stage activeStage, Move effect) {

        if (!(activeStage.getScene().getRoot() instanceof StackPane) || effectOverlayIsRunning) {
            return;
        }

        effectOverlayIsRunning = true;
        List<Field> path = GameService.getInstance().getGameBoard().getPath(
                GameService.getInstance().getFieldOfInvestigator(effect.getInvestigator()),
                effect.getField(),
                effect.getInvestigator());
        int delay = overlay.init();


        Map<SVGPath, Boolean> cords = InterfaceLinking.gameBoardGUI.getMap().getSvgPaths(path);


        overlay.setEffect(Effects.dropShadow);
        InterfaceLinking.gameBoardGUI.getMap().getChildren().add(overlay);
        List<SVGPath> svgPaths = new ArrayList<>(cords.keySet());

        PauseTransition p0 = new PauseTransition(Duration.millis(100));
        p0.setOnFinished(e0 -> {
            effect.execute();
            effectOverlayIsRunning = false;
            InterfaceLinking.gameBoardGUI.getMap().getChildren().remove(overlay);
            GameService.getInstance().getInsertions().remove(effect);
        });
        PathTransition p1 = new PathTransition(Duration.millis(1000), svgPaths.get(0));
        p1.setNode(overlay);
        if (cords.size() > 1) {
            p1.setOnFinished(e -> {
                PathTransition p2 = new PathTransition(Duration.millis(1000), svgPaths.get(1));
                p2.setNode(overlay);
                p2.setOnFinished(e1 -> p0.play());
                if (cords.get(svgPaths.get(1))) {
                    p2.setRate(-1);
                    p2.playFrom(Duration.millis(1000));
                } else {
                    p2.play();
                }
            });
        } else {
            p1.setOnFinished(e1 -> p0.play());
        }
        if (cords.get(svgPaths.get(0))) {
            p1.setRate(-1);
            p1.playFrom(Duration.millis(1000));
        } else {
            p1.play();
        }


    }

    public static void effectOverlayPhaseSwitch(Overlay overlay, Stage activeStage, Effect effect) {

        if (!(activeStage.getScene().getRoot() instanceof StackPane) || effectOverlayIsRunning) {
            return;
        }

        effectOverlayIsRunning = true;
        int delay = overlay.init();


        StackPane root = (StackPane) activeStage.getScene().getRoot();
        EffectLayer pane = (EffectLayer) root.getChildren().get(root.getChildren().size() - 1);

        ScaleTransition st0 = new ScaleTransition(Duration.millis(1), overlay);
        st0.setOnFinished(e -> {

            overlay.setEffect(Effects.dropShadow);
            pane.getChildren().add(overlay);
        });
        FadeTransition ft0 = new FadeTransition(Duration.millis(500), overlay);
        ft0.setFromValue(0);
        ft0.setToValue(1);

        TranslateTransition tt = new TranslateTransition(Duration.millis(2000), overlay);
        tt.setFromX(0);
        tt.setToX(-100);

        FadeTransition st1 = new FadeTransition(Duration.millis(2000), overlay);
        st1.setDelay(Duration.millis(500));
        st1.setFromValue(1);
        st1.setToValue(0);
        st1.setOnFinished(a -> {
            effectOverlayIsRunning = false;
            pane.getChildren().remove(overlay);
            GameService.getInstance().getInsertions().remove(effect);
        });

        ParallelTransition t = new ParallelTransition(st0, ft0, tt, st1);
        t.setDelay(Duration.millis(delay));
        t.setOnFinished(a -> Platform.runLater(effect::execute));

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
        if(node==null){
            return;
        }
        AnimationTimer timer = new AnimationTimer() {

            private long lastUpdate = 0;

            double scaleX = InterfaceLinking.gameBoardGUI.getZoomGroup().getScaleX();
            double scaleY = InterfaceLinking.gameBoardGUI.getZoomGroup().getScaleY();


            double x;
            double y;

            private int speed = 20;
            private double speedX;
            private double speedY;
            private int count = 0;


            @Override
            public void handle(long time) {
                if (lastUpdate == 0) {
                    Point2D nodePoint = InterfaceLinking.gameBoardGUI.getMap().getLocalToParentTransform().transform(node.getTranslateX(), node.getTranslateY());
                    x = clamp(((nodePoint.getX() + 100) * scaleX - 960) / (InterfaceLinking.gameBoardGUI.getZoomGroup().getLayoutBounds().getWidth() * scaleX - 1920), 0, 1);
                    y = clamp(((nodePoint.getY() + 100) * scaleY - 640) / (InterfaceLinking.gameBoardGUI.getZoomGroup().getLayoutBounds().getHeight() * scaleY - 1280), 0, 1);
                    speedX = (x - InterfaceLinking.gameBoardGUI.getScrollPane().getHvalue()) / speed;
                    speedY = (y - InterfaceLinking.gameBoardGUI.getScrollPane().getVvalue()) / speed;
                }
                if (lastUpdate > 0) {
                    InterfaceLinking.gameBoardGUI.getScrollPane().setVvalue(InterfaceLinking.gameBoardGUI.getScrollPane().getVvalue() + speedY);
                    InterfaceLinking.gameBoardGUI.getScrollPane().setHvalue(InterfaceLinking.gameBoardGUI.getScrollPane().getHvalue() + speedX);
                    if (count >= speed) {
                        stop();
                    }
                    count++;
                }
                lastUpdate = time;
            }
        };

        timer.start();
    }

    public static void spawnEffect(Node node) {
        AnimationTimer timer = new AnimationTimer() {

            private long lastUpdate = 0;
            // private  ColorAdjust glow = new ColorAdjust(0.0, 0.1, 0.3, 0.0);
            private Glow glow = new Glow(0);
            private double speed = 0.1;


            @Override
            public void handle(long time) {
                if (lastUpdate > 0) {
                    glow.setLevel(glow.getLevel() + speed);
                    node.setEffect(glow);
                    if (glow.getLevel() >= 1.5) {
                        speed = -0.05;
                    } else if (glow.getLevel() <= 0) {
                        node.setEffect(null);
                        stop();
                    }

                }
                lastUpdate = time;
            }
        };

        timer.start();
    }

    public static void rotateOmen(Group circle, Node oldOne, Node newOne, int rotate) {

        RotateTransition rt = new RotateTransition(Duration.millis(200), circle);
        if (circle.getRotate() == 0 && rotate == 270) {
            rt.setFromAngle(360);
        } else if (circle.getRotate() == 270 && rotate == 0) {
            rt.setFromAngle(-90);
        }
        rt.setToAngle(rotate);

        ScaleTransition st1 = new ScaleTransition(Duration.millis(200), oldOne);
        st1.setToX(0.5);
        st1.setToY(0.5);
        ScaleTransition st2 = new ScaleTransition(Duration.millis(200), newOne);
        st2.setToX(1.0);
        st2.setToY(1.0);

        ParallelTransition t = new ParallelTransition(rt, st1, st2);

        t.playFromStart();
    }

    public static void setOmenEditable(OmenTrackGUI omenTrackGUI, Runnable r) {
        ScaleTransition st1 = new ScaleTransition(Duration.millis(400), omenTrackGUI);
        st1.setToX(2);
        st1.setToY(2);
        TranslateTransition tt = new TranslateTransition(Duration.millis(400), omenTrackGUI);
        tt.setToX(omenTrackGUI.getParent().getLayoutBounds().getWidth() / 2);
        tt.setToY(omenTrackGUI.getParent().getLayoutBounds().getHeight() / 2);

        ParallelTransition t = new ParallelTransition(tt, st1);
        t.setOnFinished(e -> r.run());

        t.playFromStart();

    }


    public static void setOmenUnEditable(OmenTrackGUI omenUnEditable, Runnable r) {

        ScaleTransition st1 = new ScaleTransition(Duration.millis(400), omenUnEditable);
        st1.setToX(1);
        st1.setToY(1);
        TranslateTransition tt = new TranslateTransition(Duration.millis(400), omenUnEditable);
        tt.setToX(Screen.getPrimary().getBounds().getWidth() - 150);
        tt.setToY(150);

        ParallelTransition t = new ParallelTransition(tt, st1);
        t.setOnFinished(e -> r.run());
        t.playFromStart();
    }

    public static void removeEffect(Node node, Runnable remove) {
        FadeTransition t = new FadeTransition(Duration.millis(500), node);
        t.setFromValue(1);
        t.setToValue(0);
        t.setOnFinished(e -> remove.run());
        t.play();
    }
}
