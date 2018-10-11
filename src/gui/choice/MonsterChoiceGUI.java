package gui.choice;

import Service.GameService;
import enums.ChoiceType;
import enums.YesNo;
import expetions.ReserveException;
import gamemechanics.choice.InformationChoice;
import gamemechanics.choice.MonsterChoice;
import gamemechanics.encounter.CombatEncounter;
import gui.ItemScrollPane;
import gui.buttons.MonsterButton;
import gui.buttons.YesNoButton;
import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import model.Item.Monster;

import java.util.ArrayList;
import java.util.List;

public class MonsterChoiceGUI extends ChoiceDialog {

    public MonsterChoiceGUI(MonsterChoice choice) {
        super(0.6, 0.6, choice);

        List<Monster> monsters = choice.getMonsters();
        List<Monster> choosen = new ArrayList<>();
        ItemScrollPane scrollPane = new ItemScrollPane();
        for(Monster monster : monsters) {
            MonsterButton button= new MonsterButton(monster);
            button. addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
                if (e.getButton().equals(MouseButton.PRIMARY)) {
                    if(choice.getChoiceType().equals(ChoiceType.COMBAT_ENCOUNTER)) {
                        close();
                        List<Monster> remain = new ArrayList<>(monsters);
                        remain.remove(monster);
                        GameService.getInstance().addEncounter(new CombatEncounter(monster, remain, GameService.getInstance().getActiveInvestigator()));
                    }else{
                        if(choosen.contains(monster)){
                            choosen.remove(monster);
                        }else{
                            choosen.add(monster);
                        }

                        if(choice.isSingleSelect()) {
                            choice.addSelection(monster);
                        }else{
                            button.switchSelected();
                        }
                    }
                }
            });
            scrollPane.getScrollableChildren().addAll(button);
        }
        scrollPane.disableBackground(true);
        main.getChildren().add(scrollPane);

        if(!choice.isSingleSelect() &&choice.getChoiceType().equals(ChoiceType.MONSTER_CHOICE)) {

            YesNoButton okButton = new YesNoButton(YesNo.YES);
            okButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                if (e.getButton().equals(MouseButton.PRIMARY)) {
                    try {
                        //TODO
                        close();
                    }catch (ReserveException ex){
                        GameService.getInstance().addChoice(new InformationChoice("",ex.getMessage(),new ArrayList<>()));
                    }
                }
            });
            StackPane.setAlignment(okButton, Pos.BOTTOM_RIGHT);
            main.getChildren().add(okButton);
        }
    }


}
