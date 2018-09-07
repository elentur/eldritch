package model.Item;

import container.ItemStack;
import enums.ItemType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import utils.ResourceUtil;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public abstract class Artifact implements Item {

    public String uniqueId = UUID.randomUUID().toString();
    private final ItemType type;
    private final List<ItemBonus> bonus;
    private ItemStack stack;

    public Artifact(ItemType type ){
        this.type = type;
        this.bonus= createBonus();


    }
    public String getName(){
        return  ResourceUtil.get(getNameId(),"artifact");
    }

    public String toString() {
        return getName();
    }

    @Override
    public ItemType getSubType() {
        return type;
    }

    @Override
    public ItemType getItemType() {
        return ItemType.ARTIFACT;
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
    public Artifact draw(){
        return (Artifact) getStack().draw();
    }
    @Override
    public void executeReckoning(Investigator inv, boolean autoFail){}

    public String getInfoText() {
        StringBuilder infoText = new StringBuilder();
        for(Bonus bonus: getBonus()){
            infoText.append("Bonus: ").append(bonus.getText()).append("\n");
        }
        return infoText.toString();
    }
}
