package gui.buttons;

import gui.Effects;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;

public class Button extends StackPane {
    private BooleanProperty enabled = new SimpleBooleanProperty(true);
    private BooleanProperty armed = new SimpleBooleanProperty(false);
    final ImageView imageView;

    protected Node actualNode;


    public Button(Image image) {
        imageView = new ImageView(image);
        this.maxWidthProperty().bind(image.widthProperty());
        this.maxHeightProperty().bind(image.heightProperty());

        actualNode = new Group( this.imageView);
        actualNode.setEffect(Effects.dropShadow);
        this.getChildren().add(actualNode);

        actualNode.setOnMouseEntered(e -> {
            if (isEnabled()) {
                hoverIn();
            }
        });
        actualNode.setOnMouseExited(e -> {
            if (isEnabled()) {
                hoverOut();
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

        enabled.addListener(e -> {
            this.setDisable(!isEnabled());
            if (isDisabled()) {
                this.imageView.setEffect(Effects.disabled);
            } else {
                this.imageView.setEffect(null);
            }
        });
    }

    void hoverOut() {

        imageView.setEffect(null);
        actualNode.setEffect(Effects.dropShadow);

    }

    void hoverIn() {

        imageView.setEffect(Effects.hover);

    }

    public BooleanProperty enabledProperty() {
        return enabled;
    }

    boolean isEnabled() {
        return enabled.getValue();
    }

    public void setEnabled(boolean v) {
        enabled.setValue(v);
    }

    public boolean isArmed() {
        return armed.getValue();
    }

    private void setArmed(boolean v) {
        armed.setValue(v);
    }


}
