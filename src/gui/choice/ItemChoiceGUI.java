package gui.choice;

import Service.GameService;
import enums.YesNo;
import expetions.ItemChoiceException;
import expetions.ReserveException;
import gamemechanics.choice.InformationChoice;
import gamemechanics.choice.ItemChoice;
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
import model.Item.Item;
import model.effects.GainAsset;

import java.util.ArrayList;
import java.util.List;

public class ItemChoiceGUI extends ChoiceDialog {

    public ItemChoiceGUI(ItemChoice choice) {
        super(0.6, 0.6, choice);
        List<Item> items;
        try {
             items = choice.get();

        }catch (ItemChoiceException ex){
            GameService.getInstance().addChoice(new InformationChoice("", ex.getMessage(), new ArrayList<>()));
            close();
            return;
        }
        List<Item> chosen = new ArrayList<>();

        ItemScrollPane scrollPane = new ItemScrollPane();
        for(Item item : items) {
            InventoryItemButton button= new InventoryItemButton(item,true);
            button. addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
                if (e.getButton().equals(MouseButton.PRIMARY)) {



                        if(chosen.contains(item)){
                            chosen.remove(item);
                        }else{
                            chosen.add(item);
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
                } catch (ItemChoiceException ex) {
                    GameService.getInstance().addChoice(new InformationChoice("", ex.getMessage(), new ArrayList<>()));
                }
            });
            StackPane.setAlignment(okButton, Pos.BOTTOM_RIGHT);
            main.getChildren().add(okButton);

    }


}
