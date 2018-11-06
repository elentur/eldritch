package gui.choice;

import Service.GameService;
import enums.YesNo;
import expetions.InvestigatorChoiceException;
import expetions.ItemChoiceException;
import expetions.ReserveException;
import gamemechanics.choice.InformationChoice;
import gamemechanics.choice.InvestigatorChoice;
import gui.ItemScrollPane;
import gui.buttons.InventoryItemButton;
import gui.buttons.YesNoButton;
import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import model.Item.Investigator;

import java.util.ArrayList;
import java.util.List;

public class InvestigatorChoiceGUI extends ChoiceDialog {

    public InvestigatorChoiceGUI(InvestigatorChoice choice) {
        super(0.6, 0.6, choice);

        List<Investigator> investigators;
        try {
            investigators = choice.getInvestigators();
        } catch (InvestigatorChoiceException ex) {
            GameService.getInstance().addChoice(new InformationChoice("", ex.getMessage(), new ArrayList<>()));
            close();
            return;
        }
        List<Investigator> chosen = new ArrayList<>();

        ItemScrollPane scrollPane = new ItemScrollPane();
        for (Investigator investigator : investigators) {
            InventoryItemButton button = new InventoryItemButton(investigator, true);
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                if (e.getButton().equals(MouseButton.PRIMARY)) {

                    if (chosen.contains(investigator)) {
                        chosen.remove(investigator);
                    } else {
                        chosen.add(investigator);
                    }
                    button.switchSelected();
                }
            });
            scrollPane.getScrollableChildren().addAll(button);
        }
        scrollPane.disableBackground(true);
        main.getChildren().add(scrollPane);
        YesNoButton okButton = new YesNoButton(YesNo.YES);
        okButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            if (e.getButton().equals(MouseButton.PRIMARY)) {

            }
            try {
                choice.choose(chosen);
                close();
            } catch (InvestigatorChoiceException ex) {
                GameService.getInstance().addChoice(new InformationChoice("", ex.getMessage(), new ArrayList<>()));
            }
        });
        StackPane.setAlignment(okButton, Pos.BOTTOM_RIGHT);
        main.getChildren().add(okButton);
    }


}
