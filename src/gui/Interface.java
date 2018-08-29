package gui;

import gui.interfaceelements.ActiveInvestigatorGUI;
import gui.interfaceelements.AncientOneGUI;
import gui.interfaceelements.InactiveInvestigatorsGUI;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import lombok.Getter;

public class Interface extends Group {

    @Getter
    private final ActiveInvestigatorGUI activeInvestigatorGUI;
    @Getter
    private final InactiveInvestigatorsGUI inactiveInvestigatorsGUI;
    @Getter
    private final AncientOneGUI ancientOneGUI;
   public Interface(StackPane parent){

       StackPane.setAlignment(this,Pos.TOP_LEFT);
       activeInvestigatorGUI = new ActiveInvestigatorGUI();
       activeInvestigatorGUI.translateYProperty().bind(parent.heightProperty().subtract(250));
       inactiveInvestigatorsGUI = new InactiveInvestigatorsGUI();
       inactiveInvestigatorsGUI.prefHeightProperty().bind(parent.heightProperty().multiply(0.5));
       inactiveInvestigatorsGUI.translateYProperty().bind(parent.heightProperty().multiply(0.5).subtract(inactiveInvestigatorsGUI.heightProperty().multiply(0.5)));
       ancientOneGUI = new AncientOneGUI();



       this.getStyleClass().add("transparent");


        this.getChildren().addAll(activeInvestigatorGUI,ancientOneGUI,inactiveInvestigatorsGUI);
   }


}
