package gui;

import gui.interfaceelements.ActiveInvestigatorGUI;
import gui.interfaceelements.AncientOneGUI;
import gui.interfaceelements.InactiveInvestigatorsGUI;
import gui.interfaceelements.OmenTrackGUI;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import lombok.Getter;

public class Interface extends Group {

    @Getter
    private  ActiveInvestigatorGUI activeInvestigatorGUI;
    @Getter
    private  InactiveInvestigatorsGUI inactiveInvestigatorsGUI;
    @Getter
    private  AncientOneGUI ancientOneGUI;
    @Getter
    private  OmenTrackGUI omenTrack;
   public void init(StackPane parent){

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
        this.getChildren().addAll(ancientOneGUI,inactiveInvestigatorsGUI,omenTrack,activeInvestigatorGUI);
   }


}
