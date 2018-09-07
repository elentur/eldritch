package gui.buttons;

import gui.Effects;
import gui.Fonts;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import lombok.Setter;

public class Button extends StackPane {
    private final Tooltip tooltip;
    @Setter
    private Effect disabledEffect;
    private BooleanProperty enabled = new SimpleBooleanProperty(true);
    private BooleanProperty armed = new SimpleBooleanProperty(false);
    final ImageView imageView;

    protected Node actualNode;


    public Button(Image image) {
        disabledEffect = Effects.disabled;
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
                this.imageView.setEffect(disabledEffect);
            } else {
                this.imageView.setEffect(null);
            }
        });
        tooltip = new Tooltip();
        tooltip.getStyleClass().add("tooltip");
        tooltip.styleProperty().bind(Fonts.getFont(0.17,Fonts.DARK,Fonts.FontTyp.NORMAL));
        tooltip.setTextAlignment(TextAlignment.CENTER);
        tooltip.setWrapText(true);
        tooltip.setMaxWidth(200);

    }
    protected void setTooltipText(String text) {
        Tooltip.install(imageView, tooltip);
        tooltip.setText(text);
    }

    protected void hoverOut() {

        imageView.setEffect(null);
        actualNode.setEffect(Effects.dropShadow);

    }

    protected void hoverIn() {

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

    protected void setArmed(boolean v) {
        armed.setValue(v);
    }


}
