package gui.choice;

import gamemechanics.choice.Choice;
import gui.DialogGui;
import gui.Fonts;
import gui.TextField;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.TextAlignment;
import lombok.Getter;


public abstract class ChoiceDialog extends DialogGui {

    private final static Image newBackground = new Image("images/ShowCase.jpg");
    @Getter
    private VBox texts;
    private final Choice choice;


    public ChoiceDialog(double width, double height, Choice choice) {
        super("", width, height);
        this.choice = choice;
        if (!choice.checkConditions()) {
            this.close();
            return;
        }
        Label headline = new Label(choice.getHeadline());
        headline.styleProperty().bind(Fonts.getFont(0.4, Fonts.DARK, Fonts.FontTyp.BOLD));
        headline.setAlignment(Pos.CENTER);
        headline.setTextAlignment(TextAlignment.CENTER);
        texts = new VBox(20, headline);
        if (choice.getInfo() != null && !choice.getInfo().equals("")) {
            TextField info = new TextField(choice.getInfo());
            info.maxWidthProperty().bind(background.widthProperty().subtract(150));
            info.maxHeightProperty().bind(main.heightProperty().subtract(headline.heightProperty().multiply(2)));
            texts.getChildren().add(info);
        }


        texts.setAlignment(Pos.TOP_CENTER);
        main.getChildren().addAll(texts);
        main.minWidthProperty().bind(background.widthProperty().multiply(1));
        main.maxWidthProperty().bind(background.widthProperty().multiply(1));
        main.minHeightProperty().bind(background.heightProperty().multiply(0.9));
        main.maxHeightProperty().bind(background.heightProperty().multiply(0.9));

        background.setFill(new ImagePattern(newBackground));

        choice.getChoiceTakenProperty().addListener(e -> this.close());

    }


}
