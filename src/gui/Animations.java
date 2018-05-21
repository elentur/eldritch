package gui;

import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.util.Duration;


public class Animations {

	public static void startRotateFromTo(Node oldNode, Node node, Group group) {
		ScaleTransition st1 = new ScaleTransition(Duration.millis(70), group);
		st1.setByX(-1);
		st1.playFromStart();
		st1.setOnFinished(a -> {
			group.getChildren().remove(oldNode);
			group.getChildren().add( node);
			// st1.setRate(-1.0);
			st1.setByX(1.0f);
			st1.play();
			st1.setOnFinished(null);
		});
	}

	public static void scroll(ScrollPane pane, double v) {
		double h = pane.getContent().getBoundsInLocal().getHeight();
		double value = pane.getVvalue()+(400*v)/h;
		int duration = 1000;
		if(value > 1){
			duration=(int)(duration*(value-1));
			value=1;
		}
		else if(value<0 ){
			duration=(int)(duration*(1+value));
			value=0;
		}
		final Timeline timeline = new Timeline();
		timeline.setCycleCount(1);
		timeline.setAutoReverse(true);
		final KeyValue kv = new KeyValue(pane.vvalueProperty(), value, Interpolator.EASE_BOTH);
		final KeyFrame kf = new KeyFrame(Duration.millis(duration), kv);
		timeline.getKeyFrames().add(kf);
		timeline.playFromStart();
	}

	public static void scroll2(ScrollPane pane,double v, ArrowButton button){
		final double scrollSpeed = 1.5 ;

		AnimationTimer timer = new AnimationTimer() {

			private long lastUpdate = 0 ;
			@Override
			public void handle(long time) {
				if (lastUpdate > 0) {
					long elapsedNanos = time - lastUpdate ;
					double elapsedSeconds = elapsedNanos / 1_000_000_000.0 ;
					double delta = 0 ;

					if(button.isArmed()) {
						delta = v * scrollSpeed * elapsedSeconds;

					}else{
						stop();
					}
					double newValue =
							clamp(pane.getVvalue() + delta, pane.getVmin(), pane.getVmax());
					pane.setVvalue(newValue);
				}
				lastUpdate = time ;
			}
		};

		timer.start();
	}

	private static double clamp(double value, double min, double max) {
		return Math.min(max, Math.max(min, value));
	}
}
