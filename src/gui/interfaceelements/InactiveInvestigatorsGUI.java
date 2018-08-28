package gui.interfaceelements;

import Service.GameService;
import gui.buttons.InvestigatorButton;
import gui.buttons.MonsterButton;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.layout.FlowPane;
import model.Item.Investigator;
import model.Item.monsters.Vampire;

public class InactiveInvestigatorsGUI extends FlowPane {

    public InactiveInvestigatorsGUI(){
        super(10,10);
        this.getStyleClass().add("transparent");
        this.setOrientation(Orientation.VERTICAL);
        this.setPadding(new Insets(0,0,0,20));
    }

    public void update() {
        this.getChildren().clear();
        for(Investigator inv : GameService.getInstance().getInactiveInvestigators()){
            InvestigatorButton investigatorButton = new InvestigatorButton(inv);
            this.getChildren().add(0,investigatorButton);
        }


    }
}
