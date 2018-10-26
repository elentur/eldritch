package container;

import enums.ItemType;
import lombok.Getter;
import lombok.extern.java.Log;
import model.Item.Item;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Log
public abstract class ItemStack<T extends Item> {

    @Getter
    protected final ItemContainer<T> drawStack;
    @Getter
    protected final ItemContainer<T> traystack;

    public ItemStack(ItemContainer<T> init) {
        drawStack = init;
        for (Item item : drawStack) {
            item.setStack(this);
        }
        traystack = new ItemContainer<>();
        shuffle();
    }

    public void addItem(T item) {
        if (item != null) {
            item.setStack(this);
            drawStack.add(item);
        }
    }

    public void addItem(int i, T item) {
        if (item != null) {
            item.setStack(this);
            drawStack.add(i, item);
        }
    }

    public void addAllItem(Collection<T> items) {
        for (T item : items) {
            if (item != null)
                item.setStack(this);
        }
        drawStack.addAll(items);

    }

    public void addAllItem(int i, Collection<T> items) {
        for (T item : items) {
            if (item != null)
                item.setStack(this);
        }
        drawStack.addAll(i, items);
    }

    public Item get(String id) {
        Item item = drawStack.get(id);
        if (item != null) {
            drawStack.remove(item);
            return item;
        }
        item = traystack.get(id);
        if (item != null) {
            drawStack.remove(item);
        }
        return item;
    }

    public T draw() {
        unempty();
        return drawStack.isEmpty() ? null : drawStack.remove(0);
    }

    public T showFirst() {
        unempty();
        return drawStack.get(0);
    }

    protected void unempty() {
        if (drawStack.isEmpty()) {
            drawStack.addAll(traystack);
            traystack.clear();
            shuffle();
        }
    }

    public void shuffle() {
        Collections.shuffle(drawStack);
    }

    public void discard(T item) {
        traystack.add(item);
    }

    public T getByItemType(List<ItemType> itemType) {
        return drawStack.stream().filter(item -> itemType.contains(item.getItemType())).findFirst().orElse(null);
    }

    public  void removeItem(T item){

            drawStack.remove(item);

    }

}
