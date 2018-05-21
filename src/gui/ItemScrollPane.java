package gui;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;


public class ItemScrollPane extends Group {

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
        scrollPane.getStyleClass().add("item-scroll-pane");
        flowPane.setPadding(new Insets(20, 20, 20, 20));
        flowPane.setHgap(20);
        flowPane.setVgap(20);

        flowPane.setAlignment(Pos.CENTER);
        Rectangle frame = new Rectangle(10,10,new ImagePattern(frameImage));
        frame.widthProperty().bind(scrollPane.widthProperty());
        frame.heightProperty().bind(scrollPane.heightProperty());
        frame.setMouseTransparent(true);

        flowPane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,CornerRadii.EMPTY,Insets.EMPTY)));
    //    this.setBorder(new Border(new BorderStroke(Color.YELLOW, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.MEDIUM)));


        up= new ArrowButton(ArrowButton.ArrowDir.UP);
        up.translateXProperty().bind(scrollPane.translateXProperty().add(scrollPane.widthProperty()).multiply(1.05));
        down=new ArrowButton(ArrowButton.ArrowDir.DOWN);
        down.translateXProperty().bind(scrollPane.translateXProperty().add(scrollPane.widthProperty()).multiply(1.05));
        down.translateYProperty().bind(scrollPane.translateYProperty().add(scrollPane.heightProperty().subtract(down.heightProperty())));

        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        down.setOnMousePressed(e->Animations.scroll2(scrollPane,1,down));
        up.setOnMousePressed(e->Animations.scroll2(scrollPane,-1,up));

        up.enabledProperty().bind(scrollPane.vvalueProperty().isNotEqualTo(0));
        down.enabledProperty().bind(scrollPane.vvalueProperty().isNotEqualTo(1));

        up.visibleProperty().bind(scrollPane.heightProperty().lessThan(flowPane.heightProperty()));
        down.visibleProperty().bind(scrollPane.heightProperty().lessThan(flowPane.heightProperty()));
        this.getChildren().addAll(scrollPane,frame,up,down);
    }


    public ObservableList<Node> getScrollableChildren() {
        return flowPane.getChildren();
    }

    public void setAlignment(Pos center) {
        flowPane.setAlignment(center);
    }

    public void setWidth(double width) {
        scrollPane.setMinWidth(width);
        scrollPane.setMaxWidth(width);
    }

    public void setHeight(double height) {
        scrollPane.setMinHeight(height);
        scrollPane.setMaxHeight(height);
    }
}
