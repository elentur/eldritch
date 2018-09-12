package gui.choice;

import Service.GameService;
import enums.YesNo;
import expetions.ReserveException;
import gamemechanics.choice.InformationChoice;
import gamemechanics.choice.InvestigatorChoice;
import gamemechanics.choice.ReserveChoice;
import gui.Fonts;
import gui.ItemScrollPane;
import gui.buttons.InventoryItemButton;
import gui.buttons.YesNoButton;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import model.Item.Asset;
import model.Item.Investigator;
import model.effects.GainAsset;

import java.util.ArrayList;
import java.util.List;

public class InvestigatorChoiceGUI extends ChoiceDialog {

    public InvestigatorChoiceGUI(InvestigatorChoice choice) {
        super(0.6, 0.6, choice);

        List<Investigator> investigators = choice.getInvestigators();
        List<Investigator> choosen = new ArrayList<>();

        ItemScrollPane scrollPane = new ItemScrollPane();
        for(Investigator investigator : investigators) {
            InventoryItemButton button= new InventoryItemButton(investigator);
            button. addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
                if (e.getButton().equals(MouseButton.PRIMARY)) {
                    if(choosen.contains(investigator)){
                        choosen.remove(investigator);
                    }else{
                        choosen.add(investigator);
                    }

                    if(choice.isSingleSelect()) {
                        choice.addSelection(investigator);
                        close();
                    }else{
                        button.switchSelected();
                    }
                }
            });
            scrollPane.getScrollableChildren().addAll(button);
        }
        scrollPane.disableBackground(true);
        main.getChildren().add(scrollPane);
        if(!choice.isSingleSelect()) {

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
