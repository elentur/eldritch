package gamemechanics;

import Service.GameService;
import container.ItemStack;
import enums.ItemType;
import enums.OldOnes;
import gamemechanics.encounter.Encounter;
import lombok.Getter;
import lombok.Setter;
import model.Effect;
import model.Item.Investigator;
import model.Item.Item;
import model.Item.ItemBonus;
import model.Item.Token;
import model.effects.NextInvestigator;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public abstract class Mystery implements Item {

    public String uniqueId = UUID.randomUUID().toString();
    private ItemStack stack;
    private final int number;
    private final OldOnes oldOne;
    private final List<Token> tokens;

    public Mystery(int number, OldOnes oldOne) {
        this.number = number;
        this.oldOne = oldOne;
        this.tokens=new ArrayList<>();
    }

    public abstract void init();

    public abstract boolean isFinished();

    public void addToken(Token token){
        tokens.add(token);
    }
    public List<Token> getTokens(){
        return tokens;
    }

    @Override
    public String getNameId() {
         return "${mystery_"+number+"_"+ oldOne.getKey().replaceAll("[{}\\$]", "")+"}";
    }

    @Override
    public String getId() {
        return "&mystery_"+number+"_"+oldOne.getKey().replaceAll("[{}\\$]", "");
    }

    @Override
    public List<ItemBonus> createBonus() {
        return new ArrayList<>();
    }

    @Override
    public List<ItemBonus> getBonus() {
        return new ArrayList<>();
    }

    @Override
    public ItemType getItemType() {
        return ItemType.MYSTERY;
    }

    @Override
    public ItemType getSubType() {
        return ItemType.NONE;
    }

    @Override
    public String getName() {
        return ResourceUtil.get(getNameId().replace("}","_name}"), "mystery");

    }
    public String getText() {
        return ResourceUtil.get(getNameId().replace("}","_text}"), "mystery");

    }

    @Override
    public void setStack(ItemStack itemStack) {
        stack = itemStack;
    }

    @Override
    public void discard() {
        stack.discard(this);
        GameService.getInstance().addEffect(new NextInvestigator());

    }

    @Override
    public Mystery draw() {
        return (Mystery) getStack().draw();
    }

    @Override
    public void executeReckoning(Investigator inv, boolean autoFail) {
    }

    @Override
    public List<Effect> getDrawEffects() {
        return new ArrayList<>();
    }

    @Override
    public Action getEncounter() {
        return null;
    }
}
