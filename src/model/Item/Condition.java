package model.Item;

import Service.DiceRollerService;
import Service.GameService;
import container.ItemStack;
import enums.ItemType;
import gamemechanics.Action;
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
public abstract class Condition implements Item {
    public String uniqueId = UUID.randomUUID().toString();
    private final ItemType type;
    private final List<ItemBonus> bonus;
    private ItemStack stack;

    private Investigator owner;

    public Condition(ItemType itemType) {
        type = itemType;
        bonus=createBonus();
    }
    public String getName(){
        return  ResourceUtil.get(getNameId(),"condition");
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
        return ItemType.CONDITION;
    }

    @Override
    public void setStack(ItemStack itemStack){
        stack=itemStack;
    }
    @Override
    public void discard(){
        //TODO only for testing
       if(DiceRollerService.debug) stack = GameService.getInstance().getConditions();
       owner=null;
        stack.discard(this);
    }
    @Override
    public Condition draw(){
        return (Condition) getStack().draw();
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
    @Override
    public List<Effect> getDrawEffects(Investigator investigator) {
        owner = investigator;
        return new ArrayList<>();
    }


    @Override
    public Action getEncounter() {
        return null;
    }

    public void doubleEffect(Investigator inv){};
}
