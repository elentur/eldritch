package gui.choice;

import container.Inventory;
import container.ItemContainer;
import enums.YesNo;
import gamemechanics.choice.TradeChoice;
import gui.ItemScrollPane;
import gui.buttons.InventoryItemButton;
import gui.buttons.YesNoButton;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import model.Item.Item;
import model.Item.Token;
import model.effects.Trade;


public class TradeChoiceGUI extends ChoiceDialog {

    private final ItemScrollPane scrollPaneLeft;
    private final ItemScrollPane scrollPaneRight;

    public TradeChoiceGUI(TradeChoice choice) {
        super(0.6, 0.6, choice);

        ItemContainer<? extends Item> left = choice.getLeft();
        ItemContainer<? extends Item> right = choice.getRight();

        scrollPaneLeft = new ItemScrollPane();
        scrollPaneRight = new ItemScrollPane();
        updateScrollPanes(choice, (ItemContainer<Item>) left, (ItemContainer<Item>) right);

        scrollPaneLeft.disableBackground(true);
        scrollPaneLeft.setWidth1(300);
        scrollPaneLeft.setHeight1(600);
        scrollPaneLeft.setTranslateX(-300);
        scrollPaneRight.disableBackground(true);
        scrollPaneRight.setWidth1(300);
        scrollPaneRight.setHeight1(600);
        scrollPaneRight.setTranslateX(300);
        ImageView leftImg = new ImageView(new Image("images/investigator/" + choice.getInv1().getId() + ".png", 80, 80, false, true, false));
        leftImg.setRotate(-35);
        leftImg.setTranslateX(scrollPaneLeft.getTranslateX() - 120);
        leftImg.setTranslateY(-(scrollPaneLeft.getHeight() / 2 - 50));
        ImageView rightImg = new ImageView(new Image("images/investigator/" + choice.getInv2().getId() + ".png", 80, 80, false, true, false));
        rightImg.setRotate(-35);
        rightImg.setTranslateX(scrollPaneRight.getTranslateX() - 120);
        rightImg.setTranslateY(-(scrollPaneRight.getHeight() / 2 - 50));


        main.getChildren().addAll(scrollPaneLeft, scrollPaneRight, leftImg, rightImg);
        if (!choice.isSingleSelect()) {

            YesNoButton okButton = new YesNoButton(YesNo.YES);
            okButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                if (e.getButton().equals(MouseButton.PRIMARY)) {
                    close();
                    choice.getInv1().getUpdate().setValue(true);
                    choice.getInv2().getUpdate().setValue(true);
                }
            });
            StackPane.setAlignment(okButton, Pos.BOTTOM_RIGHT);
            main.getChildren().add(okButton);
        }
    }

    private void updateScrollPanes(TradeChoice choice, ItemContainer<Item> left, ItemContainer<Item> right) {
        scrollPaneLeft.getScrollableChildren().clear();
        for (Item item : left) {
            InventoryItemButton button = new InventoryItemButton(item, true);
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                if (e.getButton().equals(MouseButton.PRIMARY) && (choice.getTradeMode() == TradeChoice.BOTH
                        || choice.getTradeMode() == TradeChoice.LEFT_TO_RIGHT)) {
                    left.remove(item);
                    right.add(item);
                    if (choice.isSingleSelect()) {
                        close();
                        return;
                    }
                    updateScrollPanes(choice, left, right);
                }
            });
            scrollPaneLeft.getScrollableChildren().addAll(button);
        }
        scrollPaneRight.getScrollableChildren().clear();
        for (Item item : right) {
            InventoryItemButton button = new InventoryItemButton(item, true);
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                if (e.getButton().equals(MouseButton.PRIMARY) && (choice.getTradeMode() == TradeChoice.BOTH
                        || choice.getTradeMode() == TradeChoice.RIGHT_TO_LETF)) {
                    right.remove(item);
                    left.add(item);
                    if (choice.isSingleSelect()) {
                        close();
                        return;
                    }
                    updateScrollPanes(choice, left, right);
                }
            });
            scrollPaneRight.getScrollableChildren().addAll(button);
        }
    }


}
