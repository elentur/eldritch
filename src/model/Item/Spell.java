package model.Item;

import container.ItemStack;
import enums.ItemType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public abstract class Spell implements Item {
    private ItemStack stack;
    public String uniqueId = UUID.randomUUID().toString();
    private ItemType type;
    private List<ItemBonus> bonus;
    protected List<SpellConsequence> consequence = new ArrayList<>();

    public Spell(ItemType type ){
        this.type = type;
        this.bonus= createBonus();
        this.consequence = createConsequences();
    }

    protected abstract List<SpellConsequence> createConsequences();

    public String getName(){
        return  ResourceUtil.get(getNameId(),"spell");
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
        return ItemType.SPELL;
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
    public Spell draw(){
        return (Spell) getStack().draw();
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
