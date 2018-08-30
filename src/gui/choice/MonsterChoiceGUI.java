package gui.choice;

import Service.GameService;
import gamemechanics.choice.MonsterChoice;
import gamemechanics.encounter.CombatEncounter;
import gui.ItemScrollPane;
import gui.buttons.MonsterButton;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.Item.Monster;

import java.util.ArrayList;
import java.util.List;

public class MonsterChoiceGUI extends ChoiceDialog {

    public MonsterChoiceGUI(MonsterChoice choice) {
        super(0.6, 0.6, choice);

        List<Monster> monsters = choice.getMonsters();

        ItemScrollPane scrollPane = new ItemScrollPane();
        for(Monster monster : monsters) {
            MonsterButton button= new MonsterButton(monster);
            button. addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
                if (e.getButton().equals(MouseButton.PRIMARY)) {
                    close();
                    List<Monster>  remain = new ArrayList<>(monsters);
                    remain.remove(monster);
                    GameService.getInstance().addEncounter(new CombatEncounter(monster,remain, GameService.getInstance().getActiveInvestigator()));

                }
            });
            scrollPane.getScrollableChildren().addAll(button);
        }
        scrollPane.disableBackground(true);
        main.getChildren().add(scrollPane);
    }


}
