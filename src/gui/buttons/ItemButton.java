package gui.buttons;

import gui.Animations;
import gui.Effects;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import model.Item.Item;

public class ItemButton extends Group {

    protected final static Image backgroundImage = new Image("images/ItemBack.png", 200, 150, true, true, true);
    ImageView backside;
    ImageView frontside;
    protected final Item item;


    protected Node actualNode;
    protected Node oldNode;

    public ItemButton(Item item) {
        this.item = item;
         backside = new ImageView(backgroundImage);

        Image itemImage = new Image("images/"+item.getClass().getSimpleName()+"/" + item.getId() + ".jpg", 200, 150, true, true, true);
         frontside = new ImageView(itemImage);
        ImageView shape = new ImageView(backgroundImage);
        frontside.setClip(shape);

        actualNode = createFrontsideInfo(frontside);


        oldNode = createBacksideInfo(backside);

        this.setEffect(Effects.dropShadow);
        this.getChildren().add(actualNode);


        setEventsForButton(actualNode);
        setEventsForButton(oldNode);

    }

    private void setEventsForButton(Node node) {
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
