package model.Item.token;

import container.ItemStack;
import enums.ItemType;
import gamemechanics.encounter.Encounter;
import gamemechanics.encounter.ExpeditionEncounter;
import model.Item.Token;

public class ExpeditionToken extends Token {
    private final ExpeditionEncounter encounter;
    private ItemStack stack;

    public ExpeditionToken(ExpeditionEncounter encounter) {
        super(ItemType.EXPEDITION_TOKEN);
        this.encounter=encounter;
    }

    @Override
    public String getId() {
        return "&expeditionToken";
    }

    @Override
    public String getNameId() {
        return "${expedition_token}";
    }

    @Override
    public Encounter getEncounter() {
        return encounter;
    }

    @Override
    public void setStack(ItemStack itemStack){
        stack=itemStack;
    }
    @Override
    public void discard(){
        stack.discard(this);
    }
}
