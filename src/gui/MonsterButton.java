package gui;

import enums.TestTyp;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import model.Monster;

class MonsterButton extends Group {

    private final static Image backgroundImage = new Image("images/ItemBack.png",200,150,true,true,true);
    private final static Image sanityImage = new Image("images/sanity.png",30,30,true,true,true);
    private final static Image healthImage = new Image("images/health.png",30,30,true,true,true);
    private final static Image toughnessImage = new Image("images/toughness.png",40,40,true,true,true);




    private final Monster monster;


    private Node actualNode;
    private Node oldNode;

    MonsterButton(Monster monster) {
        this.monster = monster;

        ImageView backside = new ImageView(backgroundImage);
        Image monsterImage = new Image("images/monster/" + monster.getId() + ".jpg", 200,150,true,true,true);
        ImageView frontside = new ImageView(monsterImage);
        ImageView shape = new ImageView(backgroundImage);
        frontside.setClip(shape);

        actualNode = new Group(frontside);


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

    private Group createBacksideInfo(ImageView backside) {
        Group backsideInfo = new Group(backside);
        VBox main = new VBox();
        main.setPadding(new Insets(5,10,5,10));
        main.setSpacing(5);
        main.setMinHeight(backside.getFitHeight());
        main.setPrefWidth(backside.getFitWidth());
        main.setAlignment(Pos.CENTER);
        Label name = new Label(monster.getName());
        name.styleProperty().bind(Fonts.getFont(0.2, Fonts.DARK, Fonts.FontTyp.BOLD));
        name.setTextAlignment(TextAlignment.CENTER);
        name.setWrapText(true);



        Label willTestSymbol = new Label(TestTyp.WILL.getSymbol());
        willTestSymbol.styleProperty().bind(Fonts.getFont(0.2, Fonts.BLUE, Fonts.FontTyp.NORMAL));
        Label willTest = new Label(monster.getWillTest() + "");
        willTest.styleProperty().bind(Fonts.getFont(0.2, Fonts.BLUE, Fonts.FontTyp.NORMAL));
        Label horrorValue = new Label(monster.getHorror() + "");
        horrorValue.styleProperty().bind(Fonts.getFont(0.2, Fonts.BLUE, Fonts.FontTyp.NORMAL));
        ImageView sanity = new ImageView( sanityImage);
        sanity.setFitHeight(30);
        VBox symbolsLeft = new VBox(10, willTestSymbol, sanity);
        VBox valuesLeft = new VBox(10, willTest, horrorValue);
        valuesLeft.setAlignment(Pos.CENTER_RIGHT);
        HBox left= new HBox(symbolsLeft,valuesLeft);


        Label strengthTestSymbol = new Label(TestTyp.STRENGTH.getSymbol());
        strengthTestSymbol.styleProperty().bind(Fonts.getFont(0.2, Fonts.RED, Fonts.FontTyp.NORMAL));
        Label strengthTest = new Label(monster.getStrengthTest() + "");
        strengthTest.styleProperty().bind(Fonts.getFont(0.2, Fonts.RED, Fonts.FontTyp.NORMAL));
        Label attackValue = new Label(monster.getDamage() + "");
        attackValue.styleProperty().bind(Fonts.getFont(0.2, Fonts.RED, Fonts.FontTyp.NORMAL));
        ImageView health = new ImageView( healthImage);

        VBox symbolsRight = new VBox(10, strengthTestSymbol, health);
        VBox valuesRight = new VBox(10, strengthTest, attackValue);
        valuesRight.setAlignment(Pos.CENTER_RIGHT);
        HBox right= new HBox(symbolsRight,valuesRight);

        ImageView toughness = new ImageView( toughnessImage);
        toughness.setFitHeight(40);
        toughness.setPreserveRatio(true);
        Label toughnessValue = new Label(monster.getToughness()+"");
        toughnessValue.getStyleClass().add("text-stroke-black");
        toughnessValue.setPadding(new Insets(0,10,0,10));
        toughnessValue.styleProperty().bind(Fonts.getFont(0.4, Fonts.WHITE, Fonts.FontTyp.BOLD));
        HBox values = new HBox(10,left,right,new StackPane(toughness,toughnessValue));

        Label special = new Label("Test text fsf fsd sd sdfggd g dfg df dfggdf gdfgfd sfdfsd sdfdsfds sdfdsfd");
        special.setWrapText(true);
        special.styleProperty().bind(Fonts.getFont(0.18, Fonts.DARK, Fonts.FontTyp.ITALIC));
        main.getChildren().addAll(name,values,special);
        backsideInfo.getChildren().add(main);
        backside.fitHeightProperty().bind(main.heightProperty());
        return backsideInfo;
    }

}
