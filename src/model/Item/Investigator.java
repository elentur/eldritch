package model.Item;

import container.ItemContainer;
import container.ItemStack;
import enums.ConditionType;
import enums.FieldID;
import enums.ItemType;
import enums.TestType;
import gamemechanics.encounter.Encounter;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;
import model.Item.token.ClueToken;
import model.SkillSet;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString(of = {"firstName", "skillSet", "health", "sanity"})
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
    private FieldID startingSpace;

    private BooleanProperty update;

    private List<ItemBonus> bonus;

    private ItemContainer<Item> inventory;
    private ItemContainer<ClueToken> clues;
    private ItemStack stack;

    public Investigator(String id, SkillSet skillSet, int health, int sanity, FieldID satrtField, Item... startItems) {
        this.setId(id);
        update = new SimpleBooleanProperty(false);
        String n = id.replace("&", "");
        this.setFirstName("${" + n + "FirstName}");
        this.setLastName("${" + n + "LastName}");
        this.setOccupation("${" + n + "Occupation}");
        this.setActionText("${" + n + "ActionText}");
        this.setBonusText("${" + n + "BonusText}");
        this.setSkillSet(skillSet);
        this.setHealth(health);
        this.setSanity(sanity);
        this.setActualHealth(getHealth());
        this.setActualSanity(getSanity());
        this.setStartingSpace(satrtField);
        this.setBonus(createBonus());
        this.clues = new ItemContainer<>();
        this.setInventory(new ItemContainer<>());
        for (Item p : startItems) {
            this.getInventory().add(p);
        }

    }

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
    public String getFirstName() {
        return ResourceUtil.get(firstName, "investigator");
    }

    public String getLastName() {
        return ResourceUtil.get(lastName, "investigator");
    }
    public String getName() {
        return getFirstName() + " " + getLastName();
    }

    public String getBonusText() {
        return ResourceUtil.get(bonusText, "investigator");
    }


    public void addHealth(int value) {
        actualHealth += value;
        if (actualHealth > health) {
            actualHealth = health;
        } else if (actualHealth < 0) {
            actualHealth = 0;
        }
        log.info(getName() + " has changed health value to " + actualHealth);
        update.setValue(true);
    }

    public void addSanity(int value) {
        actualSanity += value;
        if (actualSanity > sanity) {
            actualSanity = sanity;

        } else if (actualSanity < 0) {
            actualSanity = 0;
        }
        log.info(getName() + " has changed sanity value to " + actualSanity);
        update.setValue(true);
    }

    @Override
    public ItemType getItemType() {
        return ItemType.INVESTIGATOR;
    }

    @Override
    public ItemType getSubType() {
        return ItemType.NONE;
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
    public Investigator draw(){
        return (Investigator) getStack().draw();
    }

    public void addClue(ClueToken clueToken) {
        clues.add(clueToken);
        update.setValue(true);
    }

    public ClueToken getClue() {
        if(clues.isEmpty()) {
            return null;
        }
        ClueToken clue=  clues.remove(0);
        clue.discard();
        update.setValue(true);
        return clue;
    }
}
