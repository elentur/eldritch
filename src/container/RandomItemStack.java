package container;

import model.Item.Item;

public class RandomItemStack<T>  extends ItemStack{

    public RandomItemStack(ItemContainer init) {
        super(init);
    }

    @Override
    public void discard(Item item) {
        drawStack.add(item);
        shuffle();
    }

}
