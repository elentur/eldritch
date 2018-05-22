package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;


public abstract class DialogGui extends Stage {

    private final static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width ;
    private final static int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height ;
    private final static int centerX =screenWidth / 2;
    private final static int centerY = screenHeight / 2;
    private final static Image backgroundImage= new Image("images/InfoScreen.png");

    Rectangle background;
    StackPane main;

    DialogGui(String s, double width, double height) {
        super();
        StackPane root = new StackPane();
        root.setBackground(new Background(new BackgroundFill(Color.rgb(0,0,0,0.2),CornerRadii.EMPTY,Insets.EMPTY)));
        background = new Rectangle(centerX-((screenWidth*width)/2),centerY-((screenHeight*height)/2),screenWidth*width,screenHeight*height);
        main = new StackPane();
        main.minWidthProperty().bind(background.widthProperty().multiply(0.7));
        main.maxWidthProperty().bind(background.widthProperty().multiply(0.7));
        main.minHeightProperty().bind(background.heightProperty().multiply(0.7));
        main.maxHeightProperty().bind(background.heightProperty().multiply(0.7));
        main.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,CornerRadii.EMPTY,Insets.EMPTY)));
        root.getChildren().addAll(background,main);

        background.setFill(new ImagePattern(backgroundImage));
        DropShadow shadow = new DropShadow(BlurType.GAUSSIAN, Color.rgb(0,0,0,0.6),10,0,3,3);
        background.setEffect(shadow);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("css/rootStyle.css");
        scene.setFill(Color.TRANSPARENT);
        this.setScene(scene);
        this.initModality(Modality.APPLICATION_MODAL);
        this.initStyle(StageStyle.TRANSPARENT);
        this.getScene().getRoot().setEffect(new DropShadow());
        this.setFullScreen(true);
        scene.setOnKeyPressed(e->this.close());




    }
}
