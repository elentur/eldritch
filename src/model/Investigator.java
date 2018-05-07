package model;

import container.ItemContainer;
import enums.ConditionTyp;
import enums.TestTyp;
import factory.ItemFactory;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import model.Item.Item;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString(of={"firstName","skillSet","health","sanity"})
public class Investigator {
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
    private ItemContainer<StartingPossession> startingPossessions;

    private ItemContainer<Item> inventory;

    public String getOccupation() {
        return ResourceUtil.get(occupation, this.getClass());
    }

    public int getSkill(TestTyp typ) {
        return skillSet.getSkill(typ);
    }

    public List<ConditionTyp> getConditions() {
        List<ConditionTyp> conditions = new ArrayList<>();
        return conditions;

    }

    public Investigator getInstance() {
        Investigator inv = new Investigator();
        inv.setId(id);
        inv.setFirstName(firstName);
        inv.setLastName(lastName);
        inv.setOccupation(occupation);
        SkillSet skills = new SkillSet(
                skillSet.getLore(),
                skillSet.getInfluence(),
                skillSet.getObservation(),
                skillSet.getStrength(),
                skillSet.getWill());
        inv.setSkillSet(skills);
        inv.setHealth(health);
        inv.setSanity(sanity);
        inv.setActualHealth(health);
        inv.setActualSanity(sanity);
        inv.setStartingSpace(startingSpace);
        inv.setStartingPossessions(startingPossessions);
        ItemFactory itemFactory = new ItemFactory();
        inv.setInventory(new ItemContainer<>());
        for (StartingPossession p : startingPossessions) {
            switch (p.getTyp()) {
                case ASSET:
                    inv.getInventory().add(itemFactory.getAssets().get(p.getId()));
                    break;
                case SPELL:
                    inv.getInventory().add(itemFactory.getSpells().get(p.getId()));
                    break;
                default:
                    break;

            }
        }
        return inv;
    }

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
