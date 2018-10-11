package container;

import enums.ItemType;
import lombok.extern.java.Log;
import model.Item.Item;

import java.util.Collections;

@Log
public abstract class ItemStack<T extends Item> {

    protected final ItemContainer<T> drawStack;
    protected final ItemContainer<T> traystack;

    public ItemStack(ItemContainer<T> init){
        drawStack=init;
        for(Item item : drawStack){
            item.setStack(this);
        }
        traystack=new ItemContainer<>();
        shuffle();
    }

    public Item get(String id){
        Item item = drawStack.get(id);
        if(item!= null){
            return item;
        }
        return traystack.get(id);
    }
    public T draw() {
        unempty();
        return drawStack.isEmpty()?null:drawStack.remove(0);
    }

    public T showFirst() {
        unempty();
        return drawStack.get(0);
    }

    protected void unempty() {
        if(drawStack.isEmpty()){
            drawStack.addAll(traystack);
            traystack.clear();
        }

        shuffle();
    }

    protected void shuffle() {
        Collections.shuffle(drawStack);
    }

    public void discard(T item) {
        traystack.add(item);
    }

    public T getByItemType(ItemType itemType) {
        return drawStack.stream().filter(item->item.getItemType().equals(itemType)).findFirst().orElse(null);
    }
}
