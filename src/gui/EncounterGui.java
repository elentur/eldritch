package gui;

import gamemechanics.Encounter;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

class EncounterGui extends DialogGui {
     private final Encounter encounter;
      private VBox centerPane;
      private AnchorPane encounterPane;
      private DicePane dicePane;

    final BorderPane encounterMain;
     EncounterGui(Encounter encounter) {
        super("", 0.7,0.7 );
        this.encounter=encounter;

        encounterMain = new BorderPane();
        encounterPane = new AnchorPane();


        main.getChildren().clear();
        main.getChildren().add(encounterMain);

    }

    void populateCenter(){
        dicePane = new DicePane(encounter);
        centerPane= new VBox(encounterPane,dicePane);
        encounterMain.setCenter(centerPane);
    }


}
