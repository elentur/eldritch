package model.Item;

import Service.GameService;
import container.FiniteItemStack;
import container.ItemContainer;
import container.ItemStack;
import enums.ItemType;
import enums.OldOnes;
import factory.ItemFactory;
import gamemechanics.Mythos;
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

    @Getter
    protected int minNumberOfSolvedMysteries;
    @Getter
    protected int doom;
    @Getter
    private final ItemStack<Mythos> green ;
    @Getter
    private final ItemStack<Mythos> blue ;
    @Getter
    private final ItemStack<Mythos> yellow ;
    @Getter
    private final List<Mythos> first;
    @Getter
    private final List<Mythos> second;
    @Getter
    private final List<Mythos> third;


    protected AncientOne(OldOnes oldOne, int minNumberOfSolvedMysteries,int doom) {
        this.oldOne = oldOne;
        this.minNumberOfSolvedMysteries=minNumberOfSolvedMysteries;
        this.doom=doom;

        green = ItemFactory.getMythosGreen();
        blue = ItemFactory.getMythosBlue();
        yellow = ItemFactory.getMythosYellow();
        first=new ArrayList<>();
        second=new ArrayList<>();
        third = new ArrayList<>();



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


    public abstract void init();

    protected abstract void startEndGame();
}
