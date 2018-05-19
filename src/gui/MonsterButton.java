package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import model.Monster;

public class MonsterButton extends Group {

    private final static Image backgroundImage = new Image("images/ItemBack.png");
    private final static Image sanityImage = new Image("images/sanity.png");
    private final static Image healthImage = new Image("images/health.png");

    private static ColorAdjust colorAdjust = new ColorAdjust(0.0, 0.1, 0.3, 0.0);
    private static DropShadow dropShadow = new DropShadow();

    private final Monster monster;


    private Node actualNode;
    private Node oldNode;

    public MonsterButton(Monster monster) {
        this.monster = monster;
        Rectangle backside = new Rectangle(200, 150, new ImagePattern(backgroundImage));
        Image monsterImage = new Image("images/monster/" + monster.getId() + ".jpg");
        Rectangle frontside = new Rectangle(200, 150, new ImagePattern(monsterImage));
        frontside.setClip(new Rectangle(200, 150, new ImagePattern(backgroundImage)));

        actualNode = new Group(frontside);


        oldNode = createBacksideInfo(backside);

        this.setEffect(dropShadow);
        this.getChildren().add(actualNode);


        this.setOnMouseEntered(e -> actualNode.setEffect(colorAdjust));
        this.setOnMouseExited(e -> {
            actualNode.setEffect(null);
            this.setEffect(dropShadow);
        });
        this.setOnMouseClicked(e -> {
            if (e.getButton().equals(MouseButton.SECONDARY)) {
                Animations.startRotateFromTo(actualNode, oldNode, this);
                Node temp = actualNode;
                actualNode = oldNode;
                oldNode = temp;
            }
        });
        this.setOnMousePressed(e -> {
            if (e.getButton().equals(MouseButton.PRIMARY)) {
                this.setEffect(null);
            }
        });
        this.setOnMouseReleased(e -> {
            if (e.getButton().equals(MouseButton.PRIMARY)) {
                this.setEffect(dropShadow);
            }
        });

    }

    private Group createBacksideInfo(Rectangle backside) {
        Group backsideInfo = new Group(backside);
        VBox main = new VBox();
        main.setPadding(new Insets(5,10,5,10));
        main.setSpacing(5);
        main.setMinHeight(backside.getHeight());
        main.setPrefWidth(backside.getWidth());
        main.setAlignment(Pos.CENTER);
        Label name = new Label(monster.getName());
        name.styleProperty().bind(Fonts.getFont(0.2, Fonts.DARK, Fonts.FontTyp.BOLD));
        name.setTextAlignment(TextAlignment.CENTER);
        name.setWrapText(true);



        Label horrorSymbol = new Label("\"     " + monster.getWillTest());
        horrorSymbol.styleProperty().bind(Fonts.getFont(0.2, Fonts.BLUE, Fonts.FontTyp.NORMAL));
        horrorSymbol.setPadding(new Insets(0,10,0,10));
        Label horrorValue = new Label(monster.getHorror()+"");
        horrorValue.setPadding(new Insets(0,10,0,10));
        horrorValue.styleProperty().bind(Fonts.getFont(0.2, Fonts.BLUE, Fonts.FontTyp.NORMAL));
        Rectangle sanity = new Rectangle(30,30,new ImagePattern(sanityImage));
        HBox horror = new HBox(horrorSymbol,horrorValue,sanity);
        horror.setSpacing(5);
        horror.setAlignment(Pos.CENTER);


        Label attackSymbol = new Label("§     " + monster.getStrengthTest());
        attackSymbol.styleProperty().bind(Fonts.getFont(0.2, Fonts.RED, Fonts.FontTyp.NORMAL));
        attackSymbol.setPadding(new Insets(0,10,0,10));
        Label attackValue = new Label(monster.getDamage()+"");
        attackValue.setPadding(new Insets(0,10,0,10));
        attackValue.styleProperty().bind(Fonts.getFont(0.2, Fonts.RED, Fonts.FontTyp.NORMAL));
        Rectangle health = new Rectangle(30,30,new ImagePattern(healthImage));
        HBox attack = new HBox(attackSymbol,attackValue,health);
        attack.setSpacing(5);
        attack.setAlignment(Pos.CENTER);

        Label special = new Label("Test text fsf fsd sd sdfggd g dfg df dfggdf gdfgfd sfdfsd sdfdsfds sdfdsfd");
        special.setWrapText(true);
        special.styleProperty().bind(Fonts.getFont(0.18, Fonts.DARK, Fonts.FontTyp.ITALIC));
        main.getChildren().addAll(name,horror,attack,special);
        backsideInfo.getChildren().add(main);
        backside.heightProperty().bind(main.heightProperty());
        return backsideInfo;
    }

}
