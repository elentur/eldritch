package gui;

import gui.interfaceelements.ActiveInvestigatorGUI;
import gui.interfaceelements.AncientOneGUI;
import gui.interfaceelements.InactiveInvestigatorsGUI;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import lombok.Getter;

public class Interface extends StackPane {

    @Getter
    private final ActiveInvestigatorGUI activeInvestigatorGUI;
    @Getter
    private final InactiveInvestigatorsGUI inactiveInvestigatorsGUI;
    @Getter
    private final AncientOneGUI ancientOneGUI;
   public Interface(){
       activeInvestigatorGUI = new ActiveInvestigatorGUI();
       StackPane.setAlignment(activeInvestigatorGUI, Pos.BOTTOM_LEFT);
       inactiveInvestigatorsGUI = new InactiveInvestigatorsGUI();
       inactiveInvestigatorsGUI.setAlignment(Pos.CENTER_LEFT);
       ancientOneGUI = new AncientOneGUI();
       StackPane.setAlignment(ancientOneGUI, Pos.TOP_LEFT);
       this.setMouseTransparent(true);
       this.getStyleClass().add("transparent");


        this.getChildren().addAll(activeInvestigatorGUI,ancientOneGUI,inactiveInvestigatorsGUI);
   }


}
