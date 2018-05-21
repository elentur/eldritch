package gui;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class Button extends StackPane {

    private final static Image backgroundImage = new Image("images/arrow.png");
    private BooleanProperty enabled = new SimpleBooleanProperty(true);
    private BooleanProperty armed = new SimpleBooleanProperty(false);
    final Rectangle image;

    private Node actualNode;


    Button(Rectangle image) {
        this.image = image;

        actualNode = new Group(image);
        actualNode.setEffect(Effects.dropShadow);
        this.getChildren().add(actualNode);

        actualNode.setOnMouseEntered(e -> {
            if(isEnabled()) image.setEffect(Effects.hover);

        });
        actualNode.setOnMouseExited(e -> {
            if (isEnabled()) {
                image.setEffect(null);
                actualNode.setEffect(Effects.dropShadow);
            }
        });


        actualNode.setOnMousePressed(e -> {
            if (e.getButton().equals(MouseButton.PRIMARY)) {
                actualNode.setEffect(null);
            }
            setArmed(true);
        });
        actualNode.setOnMouseReleased(e -> {
            if (e.getButton().equals(MouseButton.PRIMARY)) {
                if (isEnabled()) {
                    actualNode.setEffect(Effects.dropShadow);
                }
                setArmed(false);
            }
        });
        //this.setBorder(new Border(new BorderStroke(Color.YELLOW, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.MEDIUM)));
        this.setPadding(new Insets(12));
        this.setDisable(true);
        enabled.addListener(e -> {
            this.setDisable(!isEnabled());
            if (isDisabled()) {
                image.setEffect(Effects.disabled);
            } else {
                image.setEffect(null);
            }
        });
    }

    BooleanProperty enabledProperty() {
        return enabled;
    }

    private boolean isEnabled() {
        return enabled.getValue();
    }

    public void setEnabled(boolean v) {
        enabled.setValue(v);
    }

    boolean isArmed() {
        return armed.getValue();
    }

    private void setArmed(boolean v) {
        armed.setValue(v);
    }


}
