package gui;

import javafx.application.Platform;
import javafx.geometry.Insets;
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

import java.awt.*;


public abstract class DialogGui extends StackPane {

    protected final static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width ;
    protected final static int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height ;
    private final static int centerX =screenWidth / 2;
    private final static int centerY = screenHeight / 2;
    private final static Image backgroundImage= new Image("images/InfoScreen.png");

    protected Rectangle background;
    protected StackPane main;
    private boolean inNestedEventLoop;


    protected DialogGui(String s, double width, double height) {
        super();

        this.setBackground(new Background(new BackgroundFill(Color.rgb(0,0,0,0.2),CornerRadii.EMPTY,Insets.EMPTY)));
        background = new Rectangle(centerX-((screenWidth*width)/2),centerY-((screenHeight*height)/2),screenWidth*width,screenHeight*height);
        main = new StackPane();
        main.minWidthProperty().bind(background.widthProperty().multiply(0.7));
        main.maxWidthProperty().bind(background.widthProperty().multiply(0.7));
        main.minHeightProperty().bind(background.heightProperty().multiply(0.7));
        main.maxHeightProperty().bind(background.heightProperty().multiply(0.7));
        main.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,CornerRadii.EMPTY,Insets.EMPTY)));
        this.getChildren().addAll(background,main);

        background.setFill(new ImagePattern(backgroundImage));
        DropShadow shadow = new DropShadow(BlurType.GAUSSIAN, Color.rgb(0,0,0,0.6),10,0,3,3);
        background.setEffect(shadow);
        this.getStylesheets().add("css/rootStyle.css");
        this.setEffect(new DropShadow());





    }
    public void close(){
        Platform.runLater(this::closeEvent);


    }
    private void closeEvent(){
        InterfaceLinking.root.getChildren().remove(this);
        com.sun.javafx.tk.Toolkit.getToolkit().exitNestedEventLoop(this, null);
        afterClose();
    }

    protected  void afterClose(){}

    public void showAndWait() {

        com.sun.javafx.tk.Toolkit.getToolkit().checkFxUserThread();

        // TODO: file a new bug; the following assertion can fail if this
        // method is called from an event handler that is listening to a
        // WindowEvent.WINDOW_HIDING event.
        assert !inNestedEventLoop;

        inNestedEventLoop = true;
        com.sun.javafx.tk.Toolkit.getToolkit().enterNestedEventLoop(this);
    }

}
