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
            ItemScrollPane monsterSelection = new ItemScrollPane();
            monsterSelection.setWidth(background.getWidth()*0.6);
            monsterSelection.setHeight(background.getHeight()*0.7);
            main.getChildren().clear();
            main.getChildren().add(monsterSelection);
            for(Monster monster : encounter.getMonsters()) {
               MonsterButton monsterButton =  new MonsterButton(monster);
                monsterSelection.getScrollableChildren().add(monsterButton);
                monsterButton.setOnMouseClicked(e->{
                    encounter.setActiveMonster(monster);
                    populateCenter();
                    Animations.startRotateFromTo(monsterSelection,encounterMain,main);
                });
            }
        }else{
            encounter.setActiveMonster(encounter.getMonsters().get(0));
            populateCenter();
        }

    }

}
