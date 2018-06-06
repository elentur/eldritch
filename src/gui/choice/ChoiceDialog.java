package gui.choice;

import gamemechanics.choice.Choice;
import gui.DialogGui;
import gui.Fonts;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.TextAlignment;


public abstract class ChoiceDialog extends DialogGui {

    private final static Image newBackground = new Image("images/ShowCase.jpg");
    private final Choice choice;
    public ChoiceDialog( double width, double height, Choice choice ) {
        super("", 0.3, 0.3);
        Label headline =new Label(choice.getHeadline()) ;
        headline.styleProperty().bind(Fonts.getFont(0.4,Fonts.DARK, Fonts.FontTyp.BOLD));
        headline.setAlignment(Pos.CENTER);
        headline.setTextAlignment(TextAlignment.CENTER);
        Label info = new Label(choice.getInfo());
        info.setWrapText(true);
        info.setMaxWidth(newBackground.getWidth());
        info.styleProperty().bind(Fonts.getFont(0.25,Fonts.DARK, Fonts.FontTyp.ITALIC));
        info.setAlignment(Pos.CENTER);
        info.setTextAlignment(TextAlignment.CENTER);
        VBox texts = new VBox(20,headline,info);

        texts.setAlignment(Pos.TOP_CENTER);
        main.getChildren().addAll(texts);


        background.setFill(new ImagePattern(newBackground));
        this.choice=choice;
        choice.getChoiceTakenProperty().addListener(e->{
            this.close();
        });

    }


}
