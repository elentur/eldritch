package model.Item;

import container.FiniteItemStack;
import container.Inventory;
import container.ItemContainer;
import container.ItemStack;
import enums.ConditionType;
import enums.FieldID;
import enums.ItemType;
import enums.TestType;
import gamemechanics.Action;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;
import model.Effect;
import model.Item.token.ClueToken;
import model.Item.token.FocusToken;
import model.SkillSet;
import utils.MathUtils;
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

    private Inventory inventory;
    private ItemContainer<ClueToken> clues;
    private FiniteItemStack<FocusToken> focusStack;
    private int shipTicket;
    private int trainTicket;

    private int maxActions;


    private ItemStack stack;
    private List<Action> doneActions;

    public Investigator(String id, SkillSet skillSet, int health, int sanity, FieldID startField, Item... startItems) {
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
        this.setStartingSpace(startField);
        this.setBonus(createBonus());
        this.clues = new ItemContainer<>();
        this.focusStack = new FiniteItemStack<>(new ItemContainer<FocusToken>());
        this.inventory = new Inventory();
        for (Item p : startItems) {
            switch (p.getSubType()) {
                case CLUE_TOKEN:
                    addClue((ClueToken) p);
                    break;
                default:
                    this.getInventory().add(p);
                    break;
            }

        }
        doneActions = new ArrayList<>();
        maxActions = 2;

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
    public void setStack(ItemStack itemStack) {
        stack = itemStack;
    }

    @Override
    public void discard() {
        stack.discard(this);
    }

    @Override
    public Investigator draw() {
        return (Investigator) getStack().draw();
    }

    public void addClue(ClueToken clueToken) {
        clues.add(clueToken);
        update.setValue(true);
    }

    public ClueToken getClue() {
        if (clues.isEmpty()) {
            return null;
        }
        ClueToken clue = clues.remove(0);
        clue.discard();
        update.setValue(true);
        return clue;
    }

    public void improve(TestType testType, int value) {
        getSkillSet().improve(testType, value);
        update.setValue(true);
    }


    @Override
    public void executeReckoning(Investigator inv, boolean autoFail) {
    }

    public ItemContainer<FocusToken>  getFocus() {
        return focusStack.getDrawStack();
    }


    public int getShipTicket() {
        return shipTicket;
    }

    public void setShipTicket(int shipTicket) {
        this.shipTicket = shipTicket;
        update.setValue(true);
    }

    public void addShipTicket(int shipTicket) {
        this.shipTicket += shipTicket;
        MathUtils.clamp(shipTicket, 0, 2 - trainTicket);
        update.setValue(true);
    }

    public void removeShipTicket(int shipTicket) {
        this.shipTicket -= shipTicket;
        MathUtils.clamp(shipTicket, 0, 2 - trainTicket);
        update.setValue(true);
    }

    public int getTrainTicket() {
        return trainTicket;
    }

    public void setTrainTicket(int trainTicket) {
        this.trainTicket = trainTicket;
        update.setValue(true);
    }

    public void addTrainTicket(int trainTicket) {
        this.trainTicket += trainTicket;
        MathUtils.clamp(trainTicket, 0, 2 - shipTicket);
        update.setValue(true);
    }

    public void removeTrainTicket(int trainTicket) {
        this.trainTicket -= trainTicket;
        MathUtils.clamp(trainTicket, 0, 2 - shipTicket);
        update.setValue(true);
    }

    public void addFocus() {
        if(focusStack.getDrawStack().size()>=2){
            return;
        }
        FocusToken f = new FocusToken(this);
        f.setStack(focusStack);
      focusStack.addItem(f);
      update.setValue(true);
    }

    public void removeFocus(FocusToken focusToken) {
       focusStack.removeItem(focusToken);
        update.setValue(true);
    }

    public void addDoneAction(Action action) {
        this.doneActions.add(action);
    }

    public List<Action> getDoneActions() {
        return doneActions;
    }

    @Override
    public List<Effect> getDrawEffects() {
        return new ArrayList<>();
    }


    @Override
    public Action getEncounter() {
        return null;
    }

    public void removeFromInventory(Item item) {
        inventory.remove(item);
       // update.setValue(true);
    }

    public void addToInventory(Item item) {
        inventory.add(item);
      //  update.setValue(true);
    }
}
