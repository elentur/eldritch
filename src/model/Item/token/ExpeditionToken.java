package model.Item.token;

import Service.GameService;
import container.ItemStack;
import enums.ItemType;
import gamemechanics.encounter.Encounter;
import gamemechanics.encounter.ExpeditionEncounter;
import model.Item.Token;

public class ExpeditionToken extends Token {
    private ItemStack stack;

    public ExpeditionToken() {
        super(ItemType.EXPEDITION_TOKEN);
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
        return GameService.getInstance().getExpeditionEncounter().showFirst();
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
