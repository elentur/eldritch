package model.Item;

import container.ItemStack;
import enums.ItemType;
import gamemechanics.encounter.Encounter;
import lombok.Getter;
import utils.ResourceUtil;

import java.util.List;
import java.util.UUID;

public abstract class AncientOne implements Item {

    @Getter
    public final String uniqueId = UUID.randomUUID().toString();


    protected AncientOne() {

    }

    @Override
    public String getName() {
        return  ResourceUtil.get(getNameId(),"ancientone");
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
        return null;
    }

    @Override
    public ItemType getItemType() {
        return ItemType.ANCIENT_ONE;
    }

    public abstract Encounter getEncounter();

    @Override
    public void setStack(ItemStack itemStack){
    }
    @Override
    public void discard(){

    }
    @Override
    public Token draw(){
        return null;
    }
    @Override
    public void executeReckoning(Investigator inv, boolean autoFail){}
}
