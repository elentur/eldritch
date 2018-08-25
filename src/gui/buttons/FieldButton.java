package gui.buttons;

import Service.GameService;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import model.Field;
import model.Item.Investigator;

public class FieldButton extends Group {

    private final static Image city = new Image("images/gameBoard/CitySpace.png", 120, 120, true, true, false);
    private final static Image sea = new Image("images/gameBoard/SeaSpace.png", 120, 120, true, true, false);
    private final static Image wilderness = new Image("images/gameBoard/WildernessSpace.png", 120, 120, true, true, false);

    private final Field field;
    private final FlowPane investigators;

    public FieldButton(Field field, double x, double y) {
        Button button = new Button(getBackgroundImage(field));
        this.getChildren().add(button);
        this.field = field;
        this.setTranslateX(x);
        this.setTranslateY(y);
        investigators = new FlowPane(5,5);
        investigators.setTranslateY(150);
        investigators.setTranslateX(-50);
        investigators.setOrientation(Orientation.HORIZONTAL);
        investigators.setAlignment(Pos.CENTER);
        investigators.setPrefWidth(255);
        this.getChildren().add(investigators);



        //  this.setBorder(new Border(new BorderStroke(Fonts.GREEN, BorderStrokeStyle.SOLID, new CornerRadii(1.0), BorderStroke.MEDIUM)));



        field.getUpdate().addListener((observable,oldV,newV) -> {
            if(newV.booleanValue()){
                update();
                field.getUpdate().setValue(false);
            }
        });


        button.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
            if (e.getButton().equals(MouseButton.PRIMARY)) {
                GameService.getInstance().moveTo(GameService.getInstance().getActiveInvestigator(),field);
            }
        });
        update();


    }

    private void update() {

        createInvestigators();

    }

    private void createInvestigators() {
        investigators.getChildren().clear();
        for(Investigator inv : field.getInvestigators()){
            ImageView invImg = new ImageView("images/investigator/"+ inv.getId()+".jpg");
            invImg.setFitHeight(60);
            invImg.setFitWidth(60);
            investigators.getChildren().add(invImg);
        }
    }


    public static Image getBackgroundImage(Field field) {
        switch (field.getType()) {
            case CITY:
                return city;
            case WILDERNESS:
                return wilderness;
            default:
                return sea;
        }
    }
}
