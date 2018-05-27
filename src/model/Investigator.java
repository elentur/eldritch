package model;

import container.ItemContainer;
import enums.ConditionType;
import enums.ItemType;
import enums.TestType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import model.Item.Item;
import model.Item.ItemBonus;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(of={"firstName","skillSet","health","sanity"})
public abstract class Investigator implements Item {
    private String id;
    private String firstName;
    private String lastName;
    private String occupation;
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

    }

    public void addSanity(int value) {
        actualSanity+=value;
        if(actualSanity> sanity){
            actualSanity=sanity;
        }else if(actualSanity<0){
            actualSanity=0;
        }

    }
}
