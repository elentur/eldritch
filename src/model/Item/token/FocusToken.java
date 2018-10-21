package model.Item.token;

import container.ItemStack;
import enums.ItemType;
import enums.SituationType;
import enums.TestType;
import gamemechanics.encounter.Encounter;
import lombok.Getter;
import model.Item.Investigator;
import model.Item.ItemBonus;
import model.Item.Token;
import model.Item.boni.ItemBonus_RepeatRoll;

import java.util.ArrayList;
import java.util.List;

@Getter
public class FocusToken extends Token {


    private final Investigator investigator;
    private List<ItemBonus> bonus;
    public FocusToken(Investigator investigator) {
        super(ItemType.FOCUS_TOKEN);
        bonus=createBonus();
        this.investigator = investigator;
    }

    @Override
    public List<ItemBonus> getBonus() {
        return bonus;
    }
    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        ItemBonus_RepeatRoll bonus1 = new ItemBonus_RepeatRoll(1,TestType.ALL,SituationType.ALL,this);
        boni.add(bonus1);
        return boni;
    }

    @Override
    public String getId() {
        return "&focusToken";
    }

    @Override
    public String getNameId() {
        return "${focus_token}";
    }

    @Override
    public Encounter getEncounter() {
        return null;
    }
    @Override
    public void setStack(ItemStack itemStack){

    }
    @Override
    public void discard(){
    investigator.removeFocus(this);

    }
}
