package model.Item.token;

import Service.GameService;
import container.ItemStack;
import enums.ItemType;
import gamemechanics.encounter.Encounter;
import model.Item.Token;

public class MysteryToken extends Token {
    private ItemStack stack;

    public MysteryToken() {
        super(ItemType.MYSTERY_TOKEN);
    }

    @Override
    public String getId() {
        return "&mysteryToken";
    }

    @Override
    public String getNameId() {
        return "${mystery_token}";
    }

    @Override
    public Encounter getEncounter() {
        return GameService.getInstance().getSpecialEncounter().draw();
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
