package gui;

import gamemechanics.CombatEncounter;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;
import model.Monster;

public class CombatEncounterGui extends EncounterGui {
    private final CombatEncounter encounter;

    public CombatEncounterGui(CombatEncounter encounter) {
        super(encounter);
        this.encounter = encounter;
        showMonsterSelection();
    }

    private void showMonsterSelection() {
        if(encounter.getMonsters().size()>1){
            FlowPane pane = new FlowPane();
         //   pane.setBorder(new Border(new BorderStroke(Color.YELLOW,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderStroke.MEDIUM)));
            pane.setPadding(new Insets(20,20,20,20));
            pane.setHgap(20);
            pane.setVgap(20);
            pane.setAlignment(Pos.CENTER);
            main.getChildren().add(pane);
            for(Monster monster : encounter.getMonsters()) {
                pane.getChildren().add(new MonsterButton(monster));
            }
        }else{

        }
    }
}
