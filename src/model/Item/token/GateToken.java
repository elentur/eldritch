package model.Item.token;

import Service.GameService;
import container.ItemStack;
import enums.ItemType;
import gamemechanics.encounter.Encounter;
import model.Item.Token;

public class GateToken extends Token {
    private ItemStack stack;

    public GateToken() {
        super(ItemType.GATE_TOKEN);
    }

    @Override
    public String getId() {
        return "&gateToken";
    }

    @Override
    public String getNameId() {
        return "${gate_token}";
    }

    @Override
    public Encounter getEncounter() {
        return GameService.getInstance().getOtherWorldEncounter().draw();
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
