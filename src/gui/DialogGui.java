package gui;

import javafx.scene.Scene;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;

public abstract class DialogGui extends Stage {

    final static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width ;
    final static int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height ;
    private final static int centerX =screenWidth / 2;
    private final static int centerY = screenHeight / 2;
    private final static Image backgroundImage= new Image("images/InfoScreen.png");

    DialogGui(String s, double width, double height) {
        super();
        AnchorPane root = new AnchorPane();
        root.setBackground(new Background(new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,
                new BackgroundSize(1,1,true,true,false,false))));
        DropShadow shadow = new DropShadow(BlurType.GAUSSIAN, Color.BLACK,50,20,10,10);

        root.setEffect(shadow);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("css/rootStyle.css");
        scene.setFill(Color.TRANSPARENT);
        this.setScene(scene);
        this.setWidth(screenWidth*width);
        this.setHeight(screenHeight*height);
        this.setX(centerX-(this.getWidth()/2));
        this.setY(centerY-(this.getHeight()/2));
        this.initModality(Modality.APPLICATION_MODAL);
        this.initStyle(StageStyle.TRANSPARENT);
        this.setAlwaysOnTop(true);
        this.getScene().getRoot().setEffect(new DropShadow());



    }
}
