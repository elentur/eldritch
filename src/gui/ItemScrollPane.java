package gui;

import gui.buttons.ArrowButton;
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


public class ItemScrollPane extends StackPane {

    private final FlowPane flowPane;
    private final ScrollPane scrollPane;
    private final static Image frameImage = new Image("images/ShowCaseFrame.png");

    private final ArrowButton up;
    private final ArrowButton down;

    public ItemScrollPane() {
        flowPane = new FlowPane();
        scrollPane = new ScrollPane(flowPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.getStylesheets().add("css/rootStyle.css");
        scrollPane.getStyleClass().add("show-case");
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.maxHeightProperty().bind(this.heightProperty());
        scrollPane.maxWidthProperty().bind(this.widthProperty().multiply(0.8));

        flowPane.setPadding(new Insets(20, 30, 20, 0));
        flowPane.setHgap(20);
        flowPane.setVgap(20);

        flowPane.setAlignment(Pos.CENTER);
        Rectangle frame = new Rectangle(10,10,new ImagePattern(frameImage));
        frame.widthProperty().bind(scrollPane.widthProperty());
        frame.heightProperty().bind(scrollPane.heightProperty());
        frame.setMouseTransparent(true);

        flowPane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,CornerRadii.EMPTY,Insets.EMPTY)));
        this.setBorder(new Border(new BorderStroke(Color.YELLOW, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.MEDIUM)));


        up= new ArrowButton(ArrowButton.ArrowDir.UP);
        up.translateXProperty().bind(scrollPane.translateXProperty().add(scrollPane.widthProperty()).subtract(up.widthProperty()));
        down=new ArrowButton(ArrowButton.ArrowDir.DOWN);
        down.translateXProperty().bind(scrollPane.translateXProperty().add(scrollPane.widthProperty()).subtract(down.widthProperty()));
       down.translateYProperty().bind(scrollPane.translateYProperty().add(scrollPane.heightProperty().subtract(down.heightProperty())));



        down.setOnMousePressed(e->Animations.scroll(scrollPane,1,down));
        up.setOnMousePressed(e->Animations.scroll(scrollPane,-1,up));

        up.enabledProperty().bind(scrollPane.vvalueProperty().isNotEqualTo(0));
        down.enabledProperty().bind(scrollPane.vvalueProperty().isNotEqualTo(1));

        up.visibleProperty().bind(scrollPane.heightProperty().lessThan(flowPane.heightProperty()));
        down.visibleProperty().bind(scrollPane.heightProperty().lessThan(flowPane.heightProperty()));

        this.getChildren().addAll(scrollPane,frame,up,down);
    }


    public ObservableList<Node> getScrollableChildren() {
        return flowPane.getChildren();
    }



    public void setWidth1(double width) {
        this.setWidth(width);

    }

    public void setHeight1(double height) {
        this.setHeight(height);


    }


}
