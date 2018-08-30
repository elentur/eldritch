package container;

import factory.ItemFactory;
import gui.buttons.ItemButton;
import model.Item.Item;
import model.Item.Spell;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;

public class ItemStack<T extends Item> {

    private final ItemContainer<T> drawStack;
    private final ItemContainer<T> traystack;
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
        return drawStack.remove(0);
    }

    public T showFirst() {
        unempty();
        return drawStack.get(0);
    }

    private void unempty() {
        if(drawStack.isEmpty()){
            drawStack.addAll(traystack);
        }
        traystack.clear();
        shuffle();
    }

    private void shuffle() {
        Collections.shuffle(drawStack);
    }

    public void discard(T item) {
        traystack.add(item);
    }
}
