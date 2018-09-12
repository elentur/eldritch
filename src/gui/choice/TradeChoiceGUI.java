package gui.choice;

import Service.GameService;
import container.Inventory;
import enums.YesNo;
import expetions.ReserveException;
import gamemechanics.choice.InformationChoice;
import gamemechanics.choice.InvestigatorChoice;
import gamemechanics.choice.TradeChoice;
import gui.ItemScrollPane;
import gui.buttons.InventoryItemButton;
import gui.buttons.YesNoButton;
import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import model.Item.Investigator;
import model.Item.Item;

import java.util.ArrayList;
import java.util.List;

public class TradeChoiceGUI extends ChoiceDialog {

    private final ItemScrollPane scrollPaneLeft;
    private final ItemScrollPane scrollPaneRight;

    public TradeChoiceGUI(TradeChoice choice) {
        super(0.6, 0.6, choice);

         Inventory left = choice.getLeft();
        Inventory right = choice.getRight();

         scrollPaneLeft = new ItemScrollPane();
         scrollPaneRight = new ItemScrollPane();
        updateScrollPanes(choice,left,right);

        scrollPaneLeft.disableBackground(true);
        scrollPaneLeft.setWidth1(300);
        scrollPaneRight.disableBackground(true);
        scrollPaneRight.setWidth1(300);
        scrollPaneLeft.setTranslateX(-400);
        main.getChildren().addAll(scrollPaneLeft,scrollPaneRight);
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

    private void updateScrollPanes(TradeChoice choice, Inventory left, Inventory right) {
       scrollPaneLeft.getScrollableChildren().clear();
        for(Item item : left) {
            InventoryItemButton button= new InventoryItemButton(item);
            button. addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
                if (e.getButton().equals(MouseButton.PRIMARY)) {
                    left.remove(item);
                    right.add(item);
                    if(choice.isSingleSelect()) {
                        close();
                        return;
                    }
                    updateScrollPanes(choice,left,right);
                }
            });
            scrollPaneLeft.getScrollableChildren().addAll(button);
        }
        scrollPaneRight.getScrollableChildren().clear();
        for(Item item : right) {
            InventoryItemButton button= new InventoryItemButton(item);
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
                if (e.getButton().equals(MouseButton.PRIMARY)) {
                    right.remove(item);
                    left.add(item);
                    if(choice.isSingleSelect()) {
                        close();
                        return;
                    }
                    updateScrollPanes(choice,left,right);
                }
            });
            scrollPaneRight.getScrollableChildren().addAll(button);
        }
    }


}
