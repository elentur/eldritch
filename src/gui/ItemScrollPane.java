package gui;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;


public class ItemScrollPane extends StackPane {

    private final FlowPane flowPane;
    private final ScrollPane scrollPane;
    private final static Image frameImage = new Image("images/ShowCaseFrame.png");

    private final Rectangle frame;

    public ItemScrollPane() {
        flowPane = new FlowPane();
        scrollPane = new ScrollPane(flowPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.getStylesheets().add("css/rootStyle.css");
        scrollPane.getStyleClass().add("show-case");
        this.getStyleClass().add("eldritch-text-field");

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.maxHeightProperty().bind(this.heightProperty());
        scrollPane.maxWidthProperty().bind(this.widthProperty());

        StackPane.setAlignment(scrollPane,Pos.CENTER);

        flowPane.setPadding(new Insets(20, 30, 20, 0));
        flowPane.setHgap(20);
        flowPane.setVgap(20);

        flowPane.setAlignment(Pos.CENTER);
        frame = new Rectangle(10,10,new ImagePattern(frameImage));
        frame.widthProperty().bind(scrollPane.widthProperty());
        frame.heightProperty().bind(scrollPane.heightProperty());
        frame.setMouseTransparent(true);
        StackPane.setAlignment(frame,Pos.CENTER);

        flowPane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,CornerRadii.EMPTY,Insets.EMPTY)));
         //this.setBorder(new Border(new BorderStroke(Color.YELLOW, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.MEDIUM)));
        this.setMaxHeight(Screen.getPrimary().getBounds().getHeight()*0.35);
        //StackPane.setMargin(this, new Insets(0,50,0,50));
        StackPane.setAlignment(this,Pos.BOTTOM_CENTER);
        this.getChildren().addAll(scrollPane,frame);

    }


    public ObservableList<Node> getScrollableChildren() {
        return flowPane.getChildren();
    }



    public void setWidth1(double width) {
        this.setWidth(width);
        this.setMaxWidth(width);

    }

    public void setHeight1(double height) {
        this.setHeight(height);
        this.setMaxHeight(height);


    }


    public void disableBackground(boolean disabled) {
        if(disabled){
            this.getChildren().remove(frame);
            scrollPane.getStyleClass().remove("show-case");
            scrollPane.getStyleClass().add("transparent");
        }else{
            this.getChildren().add(1,frame);
            scrollPane.getStyleClass().add("show-case");
            scrollPane.getStyleClass().remove("transparent");
        }
    }
}
