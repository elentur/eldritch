package gui;

import container.Inventory;
import enums.YesNo;
import gui.buttons.InventoryItemButton;
import gui.buttons.SelectButton;
import gui.buttons.YesNoButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Item.Item;

public class InventoryDialog extends DialogGui {
    private final static Image assetImage = new Image("images/effect/Asset.png", 100,100,true,true);
    private final static Image artifactImage = new Image("images/effect/Artifact.png", 100,100,true,true);
    private final static Image conditionImage = new Image("images/effect/Condition.png", 100,100,true,true);
    private final static Image spellImage = new Image("images/effect/Spell.png", 100,100,true,true);
    private final UpdateListener listener;
    private final Inventory inventory;
    private final ItemScrollPane items;

    public InventoryDialog(Inventory inventory){
        super("", 0.7, 0.7);
        listener = new UpdateListener();
        this.inventory = inventory;
        inventory.getUpdate().addListener(listener);

        SelectButton assetFilterBtn= new SelectButton(assetImage);
        assetFilterBtn.setOnMouseClicked(e-> {
                    if (e.getButton().equals(MouseButton.PRIMARY)){
                        inventory.setShowAsset();
                    }
                }
        );
        SelectButton artifactFilterBtn= new SelectButton(artifactImage);
        artifactFilterBtn.setOnMouseClicked(e-> {
                    if (e.getButton().equals(MouseButton.PRIMARY)){
                        inventory.setShowArtifact();
                    }
                }
        );
        SelectButton spellFilterBtn= new SelectButton(spellImage);
        spellFilterBtn.setOnMouseClicked(e-> {
                    if (e.getButton().equals(MouseButton.PRIMARY)){
                        inventory.setShowSpell();
                    }
                }
        );
        SelectButton conditionFilterBtn= new SelectButton(conditionImage);
        conditionFilterBtn.setOnMouseClicked(e-> {
                    if (e.getButton().equals(MouseButton.PRIMARY)){
                        inventory.setShowCondition();
                    }
                }
        );
        VBox filter = new VBox(assetFilterBtn,artifactFilterBtn,spellFilterBtn,conditionFilterBtn);


        items = new ItemScrollPane();
        HBox content = new HBox(filter,items);
        content.setFillHeight(true);
        items.prefHeightProperty().bind(filter.heightProperty());
        items.prefWidthProperty().bind(content.widthProperty());
        items.minHeightProperty().bind(filter.heightProperty());

        YesNoButton okBtn= new YesNoButton(YesNo.YES);

        okBtn.setOnMouseClicked(e->{
            if(e.getButton().equals(MouseButton.PRIMARY)){
                close();
            }
        });
        content.setAlignment(Pos.BOTTOM_RIGHT);
   content.getChildren().add(okBtn);
        main.getChildren().addAll(content);
        update();
    }


    private void update() {
        items.getScrollableChildren().clear();
        for(Item item : inventory.getItems()){
            InventoryItemButton button = new InventoryItemButton(item,false);
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, a-> close());
            items.getScrollableChildren().add(button);
        }
        inventory.getUpdate().setValue(false);
    }


    @Override
    public void close(){
        super.close();
        inventory.getUpdate().removeListener(listener);
    }
    private class UpdateListener implements ChangeListener<Boolean> {
        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if (newValue) {
                update();

            }
        }
    }
}
