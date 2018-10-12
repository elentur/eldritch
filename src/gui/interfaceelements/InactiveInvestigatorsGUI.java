package gui.interfaceelements;

import Service.GameService;
import gui.Animations;
import gui.InterfaceLinking;
import gui.buttons.FieldButton;
import gui.buttons.InvestigatorButton;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Rectangle;
import model.Field;
import model.Item.Investigator;


public class InactiveInvestigatorsGUI extends FlowPane {
    private final Rectangle clipshape;

    public InactiveInvestigatorsGUI() {
        super(10, 10);
        clipshape = new Rectangle(100, 300);
        clipshape.widthProperty().bind(this.widthProperty().add(10));
        clipshape.heightProperty().bind(this.heightProperty());

        this.getStyleClass().add("transparent");
        this.setOrientation(Orientation.VERTICAL);
        this.setPadding(new Insets(0, 0, 0, 20));
        this.setClip(clipshape);
        this.setMaxWidth(50);
update();

    }

    public void update() {
        if (this.getChildren().isEmpty()) {
            for (Investigator inv : GameService.getInstance().getInactiveInvestigators()) {
                InvestigatorButton investigatorButton = buildButton(inv);
                this.getChildren().add(0, investigatorButton);
            }
        } else {
            Investigator[] invs = GameService.getInstance().getInactiveInvestigators();
            InvestigatorButton oldButton = (InvestigatorButton) this.getChildren().remove(this.getChildren().size() - 1);
            oldButton.clearListener();
            InvestigatorButton investigatorButton = buildButton(invs[invs.length - 1]);
            this.getChildren().add(0, investigatorButton);
            double w = this.getHeight() / 3 - 7;
            this.setLayoutY(this.getLayoutY() - w);
            clipshape.setLayoutY(clipshape.getLayoutY() + w);
            Animations.startInvestigatorScroll(this, clipshape, w);
        }


    }

    private InvestigatorButton buildButton(Investigator inv){
        InvestigatorButton investigatorButton = new InvestigatorButton(inv);
        investigatorButton.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                Field field = GameService.getInstance().getFieldOfInvestigator(inv);
                FieldButton fieldButton = InterfaceLinking.gameBoardGUI.getFieldButton(field);
                if(fieldButton!=null){
                    Animations.zoomTo(fieldButton);
                }

            }

        });
        return investigatorButton;
    }
}
