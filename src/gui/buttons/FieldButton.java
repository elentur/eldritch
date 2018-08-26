package gui.buttons;

import Service.GameService;
import gui.Fonts;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import model.Field;
import model.Item.Investigator;
import model.Item.Monster;

public class FieldButton extends Group {

    private final static Image city = new Image("images/gameBoard/CitySpace.png", 120, 120, true, true, false);
    private final static Image sea = new Image("images/gameBoard/SeaSpace.png", 120, 120, true, true, false);
    private final static Image wilderness = new Image("images/gameBoard/WildernessSpace.png", 120, 120, true, true, false);
    private final static Image gateImage = new Image("images/gameBoard/Gate.png", 100, 100, true, true, false);
    private final static Image expeditionImage = new Image("images/gameBoard/Expedition.png", 100, 100, true, true, false);



    private final Field field;
    private final FlowPane investigators;
    private final FlowPane monsters;
    private final FlowPane gateExpedition;
    private  boolean mouseOver;
    private final ImageView gate;
    private final ImageView expedition;


    public FieldButton(Field field, double x, double y) {
        Button button = new Button(getBackgroundImage(field));
        this.getChildren().add(button);
        this.field = field;
        this.setTranslateX(x);
        this.setTranslateY(y);

        gateExpedition = new FlowPane(5,5);
        gateExpedition.setTranslateX(70);
        gateExpedition.setTranslateY(-200);
        gateExpedition.setOrientation(Orientation.HORIZONTAL);
        gateExpedition.setAlignment(Pos.CENTER);
        gateExpedition.setPrefWidth(405);
        this.getChildren().add(gateExpedition);

        investigators = new FlowPane(5,5);
        investigators.setTranslateY(150);
        investigators.setTranslateX(-50);
        investigators.setOrientation(Orientation.HORIZONTAL);
        investigators.setAlignment(Pos.CENTER);
        investigators.setPrefWidth(255);
        this.getChildren().add(investigators);

        monsters = new FlowPane(5,5);
        monsters.translateYProperty().bind(monsters.heightProperty().multiply(-1));
        monsters.setTranslateX(-50);
        monsters.setOrientation(Orientation.HORIZONTAL);
        monsters.setAlignment(Pos.CENTER);
        monsters.setPrefWidth(255);
        this.getChildren().add(monsters);



        gate = new ImageView(gateImage);
        gate.setFitHeight(200);
        gate.setFitWidth(200);

        expedition = new ImageView(expeditionImage);
        expedition.setFitHeight(200);
        expedition.setFitWidth(200);

        //  this.setBorder(new Border(new BorderStroke(Fonts.GREEN, BorderStrokeStyle.SOLID, new CornerRadii(1.0), BorderStroke.MEDIUM)));



        field.updateProperty().addListener((observable,oldV,newV) -> {
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

        button.addEventHandler(MouseEvent.MOUSE_ENTERED, e->{
                mouseOver=true;
                update();
        });

        button.addEventHandler(MouseEvent.MOUSE_EXITED, e->{
            mouseOver=false;
            update();
        });
        update();


    }

    private void update() {

        createInvestigators();
        createMonster();
       createGateExpedition();

    }

    private void createGateExpedition() {
        gateExpedition.getChildren().clear();
        if(field.hasGate()){
            gateExpedition.getChildren().add(gate);
        }
        if(field.hasExpedition()){
            gateExpedition.getChildren().add(expedition);
        }
    }

    private void createMonster() {
        monsters.getChildren().clear();

        for(Monster monster : field.getMonster()){
            ImageView invImg = new ImageView("images/monster/"+ monster.getId()+".jpg");
            invImg.setFitHeight(60);
            invImg.setFitWidth(60);
            monsters.getChildren().add(invImg);
            if(!mouseOver){
                Label number = new Label(field.getMonster().size()+"");
                number.styleProperty().bind(Fonts.getFont(0.25,Fonts.RED,Fonts.FontTyp.BOLD));
                monsters.getChildren().add(number);
                break;
            }
        }
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
