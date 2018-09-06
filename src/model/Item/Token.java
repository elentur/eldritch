package model.Item;

import container.ItemStack;
import enums.ItemType;
import gamemechanics.encounter.Encounter;
import lombok.Getter;
import lombok.Setter;
import utils.ResourceUtil;

import java.util.List;
import java.util.UUID;


@Getter
@Setter
public abstract class Token implements Item {

    public final String uniqueId = UUID.randomUUID().toString();
    private final ItemType type;
    private ItemStack stack;

    protected Token(ItemType type) {
        this.type = type;
    }

    @Override
    public String getName() {
        return  ResourceUtil.get(getNameId(),"token");
    }

    @Override
    public List<ItemBonus> createBonus() {
        return null;
    }

    @Override
    public List<ItemBonus> getBonus() {
        return null;
    }

    @Override
    public ItemType getSubType() {
        return type;
    }

    @Override
    public ItemType getItemType() {
        return ItemType.TOKEN;
    }

    public abstract Encounter getEncounter();

    @Override
    public void setStack(ItemStack itemStack){
        stack=itemStack;
    }
    @Override
    public void discard(){
        stack.discard(this);
    }
    @Override
    public Token draw(){
        return (Token) getStack().draw();
    }
    @Override
    public void executeReckoning(Investigator inv, boolean autoFail){}
}