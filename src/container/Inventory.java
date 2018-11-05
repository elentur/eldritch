package container;

import enums.ItemType;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import lombok.Getter;
import model.Item.Item;

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
        return getItemsWithTypeFilter(filter);
    }

    @Override
    public boolean add(Item value) {
        boolean add = super.add(value);
        update.setValue(true);
        return add;

    }

    @Override
    public void add(int index, Item value) {
        super.add(index, value);
        update.setValue(true);
    }

    @Override
    public Item remove(int index) {
        Item item = super.remove(index);
        update.setValue(true);
        return item;
    }

    @Override
    public boolean remove(Object o) {
        boolean remove= super.remove(o);
        update.setValue(true);
        return remove;
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
