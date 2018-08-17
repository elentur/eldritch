package gui.buttons;

import gui.Animations;
import gui.Effects;
import gui.Fonts;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.text.TextAlignment;
import model.Item.Item;

public class ItemButton extends Group {

    protected final static Image backgroundImage = new Image("images/ItemBack.png", 200, 150, true, true, true);
    ImageView backside;
    ImageView frontside;
    protected final Item item;
    private final Tooltip tooltip;


    protected Node actualNode;
    protected Node oldNode;

    public ItemButton(String type, Item item) {
        this.item = item;
         backside = new ImageView(backgroundImage);

        Image itemImage = new Image("images/"+type+"/" + item.getId() + ".jpg", 200, 150, true, true, true);
         frontside = new ImageView(itemImage);
        ImageView shape = new ImageView(backgroundImage);
        frontside.setClip(shape);

        actualNode = createFrontsideInfo(frontside);


        oldNode = createBacksideInfo(backside);

        this.setEffect(Effects.dropShadow);
        this.getChildren().add(actualNode);


        setEventsForButton(actualNode);
        setEventsForButton(oldNode);

        tooltip = new Tooltip();
        tooltip.getStyleClass().add("tooltip");
        tooltip.styleProperty().bind(Fonts.getFont(0.17,Fonts.DARK,Fonts.FontTyp.NORMAL));
        tooltip.setTextAlignment(TextAlignment.CENTER);
        tooltip.setWrapText(true);
        tooltip.setMaxWidth(200);

    }
    protected void setTooltipText(String text) {
        Tooltip.install(frontside, tooltip);
        tooltip.setText(text);
    }
    protected void setEventsForButton(Node node) {
        node.setOnMouseEntered(e -> actualNode.setEffect(Effects.hover));
        node.setOnMouseExited(e -> {
            actualNode.setEffect(null);
            this.setEffect(Effects.dropShadow);
        });
        node.setOnMouseClicked(e -> {
            if (e.getButton().equals(MouseButton.SECONDARY)) {
                Animations.startRotateFromTo(actualNode, oldNode, this);
                Node temp = actualNode;
                actualNode = oldNode;
                oldNode = temp;
            }
        });

        node.setOnMousePressed(e -> {
            if (e.getButton().equals(MouseButton.PRIMARY)) {
                this.setEffect(null);
            }
        });
        node.setOnMouseReleased(e -> {
            if (e.getButton().equals(MouseButton.PRIMARY)) {
                this.setEffect(Effects.dropShadow);
            }
        });
    }

    protected Group createFrontsideInfo(ImageView backside) {
        Group backsideInfo = new Group(backside);

        return backsideInfo;
    }
    protected Group createBacksideInfo(ImageView backside) {
        Group backsideInfo = new Group(backside);


        return backsideInfo;
    }

}
