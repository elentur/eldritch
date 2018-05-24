package gui.buttons;

import gui.Animations;
import gui.Effects;
import gui.Fonts;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import model.Item.Bonus;
import model.Item.Item;

public class BonusButton extends Group {

    protected final static Image backgroundImage = new Image("images/ItemBack.png", 200, 150, true, true, true);
    private final Bonus bonus;
    ImageView backside;
    ImageView frontside;
    protected final Item item;


    protected Node actualNode;
    protected Node oldNode;

    public BonusButton(Bonus bonus) {
        this.bonus = bonus;
        this.item = bonus.getParentItem();
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
        VBox main = new VBox();
        main.setPadding(new Insets(5,10,5,10));
        main.setSpacing(5);
        main.setPrefWidth(200);
        main.setAlignment(Pos.CENTER);
        Label name = new Label(bonus.getParentName());
        name.styleProperty().bind(Fonts.getFont(0.2, Fonts.DARK, Fonts.FontTyp.BOLD));
        name.setTextAlignment(TextAlignment.CENTER);
        name.setWrapText(true);

        Label info = new Label(bonus.getText());
        info.setWrapText(true);
        info.styleProperty().bind(Fonts.getFont(0.18, Fonts.DARK, Fonts.FontTyp.ITALIC));
        main.getChildren().addAll(name,info);
        backsideInfo.getChildren().add(main);
        backside.fitHeightProperty().bind(main.heightProperty());
      //  main.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.MEDIUM)));
        return backsideInfo;
    }






}
