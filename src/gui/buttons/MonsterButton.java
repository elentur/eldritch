package gui.buttons;

import enums.TestType;
import gui.Fonts;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import model.Item.Monster;

public class MonsterButton extends ItemButton {


    private final static Image sanityImage = new Image("images/sanity.png",30,30,true,true,true);
    private final static Image healthImage = new Image("images/health.png",30,30,true,true,true);
    private final static Image toughnessImage = new Image("images/toughness.png",40,40,true,true,true);


    public MonsterButton(Monster monster) {
        super(monster);

    }


    @Override
    protected Group createBacksideInfo(ImageView backside) {
        Monster monster = (Monster)item;
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



        Label willTestSymbol = new Label(TestType.WILL.getSymbol());
        willTestSymbol.styleProperty().bind(Fonts.getFont(0.18, Fonts.BLUE, Fonts.FontTyp.NORMAL));
        Label willTest = new Label(monster.getWillTest() + "");
        willTest.styleProperty().bind(Fonts.getFont(0.2, Fonts.BLUE, Fonts.FontTyp.NORMAL));
        Label horrorValue = new Label(monster.getHorror() + "");
        horrorValue.styleProperty().bind(Fonts.getFont(0.2, Fonts.BLUE, Fonts.FontTyp.NORMAL));
        ImageView sanity = new ImageView( sanityImage);
        sanity.setFitHeight(30);
        VBox symbolsLeft = new VBox(10, willTestSymbol, sanity);
        VBox valuesLeft = new VBox(10, willTest, horrorValue);
        valuesLeft.setMinWidth(30);
        valuesLeft.setMaxWidth(30);
        symbolsLeft.setMinWidth(30);
        symbolsLeft.setMaxWidth(30);
        valuesLeft.setAlignment(Pos.CENTER_RIGHT);
        HBox left= new HBox(symbolsLeft,valuesLeft);


        Label strengthTestSymbol = new Label(TestType.STRENGTH.getSymbol());
        strengthTestSymbol.styleProperty().bind(Fonts.getFont(0.18, Fonts.RED, Fonts.FontTyp.NORMAL));
        Label strengthTest = new Label(monster.getStrengthTest() + "");
        strengthTest.styleProperty().bind(Fonts.getFont(0.2, Fonts.RED, Fonts.FontTyp.NORMAL));
        Label attackValue = new Label(monster.getDamage() + "");
        attackValue.styleProperty().bind(Fonts.getFont(0.2, Fonts.RED, Fonts.FontTyp.NORMAL));
        ImageView health = new ImageView( healthImage);
        health.setFitHeight(30);
        VBox symbolsRight = new VBox(10, strengthTestSymbol, health);
        VBox valuesRight = new VBox(10, strengthTest, attackValue);
        valuesRight.setMinWidth(30);
        valuesRight.setMaxWidth(30);
        symbolsRight.setMinWidth(30);
        symbolsRight.setMaxWidth(30);
        valuesRight.setAlignment(Pos.CENTER_RIGHT);
        HBox right= new HBox(symbolsRight,valuesRight);

        ImageView toughness = new ImageView( toughnessImage);
        toughness.setFitHeight(40);
        toughness.setPreserveRatio(true);
        Label toughnessValue = new Label(monster.getToughness()+"");
        toughnessValue.getStyleClass().add("text-stroke-black");
        toughnessValue.setPadding(new Insets(0,10,0,10));
        toughnessValue.styleProperty().bind(Fonts.getFont(0.4, Fonts.GREEN, Fonts.FontTyp.BOLD));
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
