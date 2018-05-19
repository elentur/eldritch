package gui;

import javafx.animation.ScaleTransition;
import javafx.scene.Group;
import javafx.scene.Node;
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


}
