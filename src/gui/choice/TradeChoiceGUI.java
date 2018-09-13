package gui.choice;

import Service.GameService;
import container.Inventory;
import enums.YesNo;
import expetions.ReserveException;
import gamemechanics.choice.InformationChoice;
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
        scrollPaneLeft.setHeight1(600);
        scrollPaneLeft.setTranslateX(-300);
        scrollPaneRight.disableBackground(true);
        scrollPaneRight.setWidth1(300);
        scrollPaneRight.setHeight1(600);
        scrollPaneRight.setTranslateX(300);
        ImageView leftImg=new ImageView(new Image("images/investigator/"+ choice.getInv1().getId()+".jpg",80,80,false,true,false));
        leftImg.setRotate(-35);
        leftImg.setTranslateX(scrollPaneLeft.getTranslateX()-120);
        leftImg.setTranslateY(-(scrollPaneLeft.getHeight()/2-50));
        ImageView rightImg=new ImageView(new Image("images/investigator/"+ choice.getInv2().getId()+".jpg",80,80,false,true,false));
        rightImg.setRotate(-35);
        rightImg.setTranslateX(scrollPaneRight.getTranslateX()-120);
        rightImg.setTranslateY(-(scrollPaneRight.getHeight()/2-50));




        main.getChildren().addAll(scrollPaneLeft,scrollPaneRight,leftImg,rightImg);
        if(!choice.isSingleSelect()) {

            YesNoButton okButton = new YesNoButton(YesNo.YES);
            okButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                if (e.getButton().equals(MouseButton.PRIMARY)) {
                        close();
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
