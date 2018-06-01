package model.Item;

import container.ItemContainer;
import enums.ConditionType;
import enums.ItemType;
import enums.TestType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;
import model.SkillSet;
import model.StartingPossession;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString(of={"firstName","skillSet","health","sanity"})
@Log
public abstract class Investigator implements Item {
    public String uniqueId = UUID.randomUUID().toString();
    private String id;
    private String firstName;
    private String lastName;
    private String occupation;
    private String actionText;
    private String bonusText;
    private SkillSet skillSet;
    private int health;
    private int sanity;
    private int actualHealth;
    private int actualSanity;
    private String startingSpace;
    private List<StartingPossession> startingPossessions;

    private List<ItemBonus> bonus;

    private ItemContainer<Item> inventory;

    public String getOccupation() {
        return ResourceUtil.get(occupation, this.getClass());
    }

    public int getSkill(TestType typ) {
        return skillSet.getSkill(typ);
    }

    public List<ConditionType> getConditions() {
        return new ArrayList<>();
    }

    @Override
    public String getNameId() {
        return "";
    }

    public String getName(){
        return firstName +" " +lastName;
    }

    public String getBonusText(){
        return ResourceUtil.get(bonusText,"investigator");
    }
    @Override
    public ItemType getItemTyp() {
        return ItemType.NONE;
    }

   protected abstract List<StartingPossession> createStartingPossessions();


    public void addHealth(int value) {
        actualHealth+=value;
        if(actualHealth> health){
            actualHealth=health;
        }else if(actualHealth<0){
            actualHealth=0;
        }
        log.info(getName() +" has changed health value to " +actualHealth);
    }

    public void addSanity(int value) {
        actualSanity+=value;
        if(actualSanity> sanity){
            actualSanity=sanity;

        }else if(actualSanity<0){
            actualSanity=0;
        }
        log.info(getName() +" has changed sanity value to " +actualSanity);
    }
}
