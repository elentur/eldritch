package gui;

import gui.interfaceelements.ActiveInvestigatorGUI;
import gui.interfaceelements.AncientOneGUI;
import gui.interfaceelements.InactiveInvestigatorsGUI;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import lombok.Getter;

public class EffectLayer extends StackPane {


   public EffectLayer() {
       this.getStyleClass().add("transparent");
       this.setMouseTransparent(true);
   }


}
