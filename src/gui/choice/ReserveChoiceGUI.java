package gui.choice;

import Service.GameService;
import enums.YesNo;
import gamemechanics.choice.MonsterChoice;
import gamemechanics.choice.ReserveChoice;
import gamemechanics.encounter.CombatEncounter;
import gui.ItemScrollPane;
import gui.buttons.ItemButton;
import gui.buttons.MonsterButton;
import gui.buttons.YesNoButton;
import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import model.Item.Asset;
import model.Item.Monster;
import model.effects.GainAsset;

import java.util.ArrayList;
import java.util.List;

public class ReserveChoiceGUI extends ChoiceDialog {

    public ReserveChoiceGUI(ReserveChoice choice) {
        super(0.6, 0.6, choice);

        List<Asset> assets = choice.get();
        List<Asset> choosen = new ArrayList<>();

        ItemScrollPane scrollPane = new ItemScrollPane();
        for(Asset asset : assets) {
            ItemButton button= new ItemButton("asset",asset);
            button. addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
                if (e.getButton().equals(MouseButton.PRIMARY)) {
                    if(choosen.contains(asset)){
                        choosen.remove(asset);
                    }else{
                        choosen.add(asset);
                    }
                    if(choice.isSingleSelect()) {

                        GameService.getInstance().getReserve().remove(choosen.get(0));
                        GameService.getInstance().addEffect(new GainAsset(choosen.get(0),GameService.getInstance().getEncounteringInvestigator()));
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

                    //TODO
                    GameService.getInstance().getReserve().buy(choosen, 4);
                    close();
                }
            });
            StackPane.setAlignment(okButton, Pos.BOTTOM_RIGHT);
            main.getChildren().add(okButton);
        }
    }


}
