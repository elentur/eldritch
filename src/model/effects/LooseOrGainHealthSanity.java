package model.effects;


import Service.GameService;
import enums.*;
import gamemechanics.choice.Choice;
import gamemechanics.choice.InvestigatorChoice;
import gamemechanics.choice.MonsterChoice;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import lombok.Getter;
import model.Effect;
import model.Field;
import model.Item.Investigator;
import model.Item.Monster;
import model.Item.boni.ItemBonus_PreventLossOfHealthOrSanity;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

@Getter
public class LooseOrGainHealthSanity extends Effect {
    public final static int START_INVESTIGATOR = 1;
    public final static int ACTIVE_INVESTIGATOR = 2;
    public final static int ENCOUNTERING_INVESTIGATOR = 3;
    public final static List<ItemBonus_PreventLossOfHealthOrSanity> listener = new ArrayList<>();

    private final SpendType spendType;
    private int value;
    private ItemType itemType;
    private FieldID fieldID;
    private EffectSelector selector;
    private Investigator investigator;
    private Monster monster;
    private int invType;

    public LooseOrGainHealthSanity(SpendType spendType, int value, int invType) {
        super(EffectTyps.LOOSE_OR_GAIN_HEALTH_SANITY);
        this.spendType = spendType;
        this.value = value;
        this.invType = invType;
    }

    public LooseOrGainHealthSanity(SpendType spendType, int value, Investigator investigator) {
        super(EffectTyps.LOOSE_OR_GAIN_HEALTH_SANITY);
        this.spendType = spendType;
        this.value = value;
        this.investigator = investigator;
    }

    public LooseOrGainHealthSanity(SpendType spendType, int value, Monster monster) {
        super(EffectTyps.LOOSE_OR_GAIN_HEALTH_SANITY);
        this.spendType = spendType;
        this.value = value;
        this.monster = monster;
    }

    public LooseOrGainHealthSanity(SpendType spendType, int value, Choice choice) {
        super(EffectTyps.LOOSE_OR_GAIN_HEALTH_SANITY);
        this.spendType = spendType;
        this.value = value;
        this.condition = choice;
    }

    public LooseOrGainHealthSanity(SpendType spendType, int value, EffectSelector selector, FieldID fieldID, ItemType itemType) {
        super(EffectTyps.LOOSE_OR_GAIN_HEALTH_SANITY);
        this.spendType = spendType;
        this.value = value;
        this.selector = selector;
        this.fieldID = fieldID;
        this.itemType = itemType;
    }

    @Override
    public void execute() {
        if (isExecuted()) {
            return;
        }
        super.execute();
        if (!isAccepted()) return;

        switch (spendType) {
            case HEALTH:
                if (investigator != null) {

                    investigator.addHealth(value);
                } else if (monster != null) {
                    if (monster.getActualToughness() > 0) {
                        monster.addDamage(value);
                    }
                } else if (selector.equals(EffectSelector.ALL) && ItemType.MONSTER.equals(itemType)) {
                    Field field = GameService.getInstance().getGameBoard().getField(fieldID);
                    for (Monster m : field.getMonster()) {
                        GameService.getInstance().addEffect(new LooseOrGainHealthSanity(spendType, value, m));
                    }
                } else if (selector.equals(EffectSelector.ALL) && ItemType.INVESTIGATOR.equals(itemType)) {
                    Field field = GameService.getInstance().getGameBoard().getField(fieldID);
                    for (Investigator i : field.getInvestigators()) {
                        GameService.getInstance().addEffect(new LooseOrGainHealthSanity(spendType, value, i));
                    }
                }
                break;
            case SANITY:
                if (investigator != null) {

                    investigator.addSanity(value);
                } else if (selector.equals(EffectSelector.ALL) && ItemType.INVESTIGATOR.equals(itemType)) {
                    Field field = GameService.getInstance().getGameBoard().getField(fieldID);
                    for (Investigator i : field.getInvestigators()) {
                        GameService.getInstance().addEffect(new LooseOrGainHealthSanity(spendType, value, i));
                    }
                }
                break;
            default:
                break;
        }

    }

    public void prevent() {
        if(investigator==null){
            return;
        }
        for (ItemBonus_PreventLossOfHealthOrSanity bonus : listener) {
            if (bonus.getSpendType().equalsWithAll(spendType)) {
                bonus.setInvestigator(investigator);
                bonus.setDamage(value);
                bonus.execute(GameService.getInstance().getEncounterProperty().getValue());
                value = Math.min(value + bonus.getPreventedValue(), 0);
                bonus.setPreventedValue(0);
                if(value==0){
                    return;
                }
            }
        }
    }

        @Override
        public void init () {
            super.init();
            if (condition != null) {
                switch (condition.getChoiceType()) {
                    case INVESTIGATOR_CHOICE:
                        investigator = ((InvestigatorChoice) condition).getSelectedInvs().get(0);
                        break;
                    case MONSTER_CHOICE:
                        monster = ((MonsterChoice) condition).getSelectedMonster().get(0);
                        break;
                }
            }
            if (invType != 0) {
                switch (invType) {
                    case START_INVESTIGATOR:
                        investigator = GameService.getInstance().getStartInvestigator();
                        break;
                    case ACTIVE_INVESTIGATOR:
                        investigator = GameService.getInstance().getActiveInvestigator();
                        break;
                    case ENCOUNTERING_INVESTIGATOR:
                        investigator = GameService.getInstance().getEncounteringInvestigator();
                        break;

                }
            }
        }

        @Override
        public String getText () {
            init();
            String name = "";
            if (selector == null) {
                name = investigator != null ? investigator.getName() : "The " + monster.getName();
            } else {
                name = selector.getText() + " " + itemType.getText();
            }
            if (spendType == null || value == 0) {
                return ResourceUtil.get("${loose}", "effect", name, ResourceUtil.get("${nothing}", "effect"));
            }
            if (value < 0) {
                return ResourceUtil.get("${loose}", "effect", name, Math.abs(value) + " " + spendType.getText());
            } else {
                return ResourceUtil.get("${gain}", "effect", name, value + " " + spendType.getText());
            }
        }


    }
