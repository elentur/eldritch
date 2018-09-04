package gui;

import gui.interfaceelements.ActiveInvestigatorGUI;
import gui.interfaceelements.AncientOneGUI;
import gui.interfaceelements.InactiveInvestigatorsGUI;
import gui.interfaceelements.OmenTrackGUI;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.QuadCurve;
import javafx.stage.Screen;
import lombok.Getter;

public class Interface extends Group {

    @Getter
    private final ActiveInvestigatorGUI activeInvestigatorGUI;
    @Getter
    private final InactiveInvestigatorsGUI inactiveInvestigatorsGUI;
    @Getter
    private final AncientOneGUI ancientOneGUI;
    @Getter
    private final OmenTrackGUI omenTrack;
   public Interface(StackPane parent){

       StackPane.setAlignment(this,Pos.TOP_LEFT);
       activeInvestigatorGUI = new ActiveInvestigatorGUI();
       activeInvestigatorGUI.translateYProperty().bind(parent.heightProperty().subtract(250));
       inactiveInvestigatorsGUI = new InactiveInvestigatorsGUI();
       inactiveInvestigatorsGUI.prefHeightProperty().bind(parent.heightProperty().multiply(0.5));
       inactiveInvestigatorsGUI.translateYProperty().bind(parent.heightProperty().multiply(0.5).subtract(inactiveInvestigatorsGUI.heightProperty().multiply(0.5)));
       ancientOneGUI = new AncientOneGUI();
       omenTrack= new OmenTrackGUI();
       omenTrack.setTranslateX(Screen.getPrimary().getBounds().getWidth()-150);
       omenTrack.setTranslateY(150);



       this.getStyleClass().add("transparent");

       this.setTranslateX(-10);
       this.setTranslateY(-10);
        this.getChildren().addAll(activeInvestigatorGUI,ancientOneGUI,inactiveInvestigatorsGUI,omenTrack);
   }


}
