package model.Item;

import container.ItemStack;
import enums.ItemType;
import enums.MythosType;
import factory.ItemFactory;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import utils.ResourceUtil;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public abstract class Mythos implements Item {
    public String uniqueId = UUID.randomUUID().toString();
    private final MythosType type;
    private final List<ItemBonus> bonus;
    private ItemStack stack;

    public Mythos(MythosType mythosType) {
        type = mythosType;
        bonus=createBonus();
    }
    public String getName(){
        return  ResourceUtil.get(getNameId(),"mythos");
    }

    public String toString() {
        return getName();
    }

    @Override
    public ItemType getSubType() {
        return ItemType.NONE;
    }

    @Override
    public ItemType getItemType() {
        return ItemType.MYTHOS;
    }

    @Override
    public void setStack(ItemStack itemStack){
        stack=itemStack;
    }
    @Override
    public void discard(){
        stack.discard(this);
    }
    @Override
    public Mythos draw(){
        return (Mythos) getStack().draw();
    }
}
