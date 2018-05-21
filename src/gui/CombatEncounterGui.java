package gui;

import gamemechanics.CombatEncounter;
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
            ItemScrollPane pane = new ItemScrollPane();
            pane.setWidth(background.getWidth()*0.6);
            pane.setHeight(background.getHeight()*0.7);

            main.getChildren().add(pane);
            for(Monster monster : encounter.getMonsters()) {
                pane.getScrollableChildren().add(new MonsterButton(monster));
            }
        }else{

        }
    }


}
