package container;

import enums.ItemType;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import lombok.Getter;
import model.Item.Item;
import model.Item.ItemBonus;

import java.util.function.Function;

@Getter
public class Inventory extends ItemContainer<Item> {

    private boolean showAsset;
    private boolean showArtifact;
    private boolean showSpell;
    private boolean showCondition;
    private BooleanProperty update;


    public Inventory() {

        update = new SimpleBooleanProperty(false);
        showAsset = true;
        showArtifact = true;
        showSpell = true;
        showCondition = true;
    }

    public ItemContainer<Item> getItems() {
        Function<Item, Boolean> filter = item -> {
            boolean containing ;
            containing = showAsset && item.getItemType().equals(ItemType.ASSET)
                    || showArtifact && item.getItemType().equals(ItemType.ARTIFACT)
                    || showSpell && item.getItemType().equals(ItemType.SPELL)
                    || showCondition && item.getItemType().equals(ItemType.CONDITION);
            return containing;
        };
        return getItemsWidthTypeFilter(filter);
    }

    public void setShowAsset() {
        showAsset = !showAsset;
        update.setValue(true);
    }

    public void setShowArtifact() {
        showArtifact = !showArtifact;
        update.setValue(true);
    }

    public void setShowSpell() {
        showSpell = !showSpell;
        update.setValue(true);
    }

    public void setShowCondition() {
        showCondition = !showCondition;
        update.setValue(true);
    }
}
