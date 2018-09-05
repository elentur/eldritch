package model.Item.token;

import Service.GameService;
import container.ItemStack;
import enums.FieldID;
import enums.ItemType;
import enums.OmenStates;
import gamemechanics.encounter.Encounter;
import lombok.Getter;
import model.Item.Token;

public abstract class GateToken extends Token {
    @Getter
    private final FieldID fieldID;
    @Getter
    private final OmenStates omenState;
    private ItemStack stack;

    public GateToken(FieldID fieldID , OmenStates omenState) {
        super(ItemType.GATE_TOKEN);
        this.fieldID = fieldID;
        this.omenState = omenState;
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
        return GameService.getInstance().getOtherWorldEncounter().showFirst();
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
