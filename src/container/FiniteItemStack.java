package container;

import lombok.extern.java.Log;

@Log
public class FiniteItemStack<T>  extends ItemStack{

    public FiniteItemStack(ItemContainer init) {
        super(init);
    }

    @Override
    protected void unempty() {
        if(drawStack.isEmpty()) {
            log.info("Stack is empty");
        }
    }
}
