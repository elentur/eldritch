package model.Item;

import container.ItemStack;
import enums.ItemType;
import enums.OldOnes;
import lombok.Getter;
import model.Effect;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class AncientOne implements Item {

    @Getter
    public final String uniqueId = UUID.randomUUID().toString();
    @Getter
    public final OldOnes oldOne;
    protected int minNumberOfSolvedMysteries;

    protected AncientOne(OldOnes oldOne) {
        this.oldOne = oldOne;
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

    @Override
    public List<Effect> getDrawEffects() {
        return new ArrayList<>();
    }


    public int getminNumberOfSolvedMysteries() {
        return minNumberOfSolvedMysteries;
    }
}
