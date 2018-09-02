package gui.choice;

import gamemechanics.choice.EncounterChoice;
import gamemechanics.encounter.Encounter;
import gui.ItemScrollPane;
import gui.buttons.EncounterButton;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class EncounterChoiceGUI extends ChoiceDialog {

    public EncounterChoiceGUI(EncounterChoice choice) {
        super(0.6, 0.6, choice);

        List<Encounter> encounters = choice.getEncounters();

        ItemScrollPane scrollPane = new ItemScrollPane();
        for(Encounter encounter : encounters) {
            EncounterButton button= new EncounterButton(encounter);
            button. addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
                if (e.getButton().equals(MouseButton.PRIMARY)) {
                   close();
                }
            });
            scrollPane.getScrollableChildren().addAll(button);
        }
        scrollPane.disableBackground(true);
        main.getChildren().add(scrollPane);
    }


}
