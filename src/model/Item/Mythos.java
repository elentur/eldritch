package model.Item;

import Service.GameService;
import container.ItemStack;
import enums.Dificulty;
import enums.ItemType;
import enums.MythosType;
import factory.ItemFactory;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import model.Effect;
import utils.ResourceUtil;

import java.util.ArrayList;
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
    private final Dificulty dificulty;

    private List<Effect> effects;

    public Mythos(MythosType mythosType,Dificulty dificulty) {
        type = mythosType;
        bonus=createBonus();
        this.dificulty=dificulty;
        effects=new ArrayList<>();
    }
    public String getName(){
        return  ResourceUtil.get(getNameId(),"mythos");
    }

    public String toString() {
        return getName();
    }

    public void execute(){
        for(Effect effect : effects){
            GameService.getInstance().addEffect(effect);
        }
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

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        return boni;
    }
}
