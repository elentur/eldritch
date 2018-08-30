package model.Item.token;

import Service.GameService;
import container.ItemStack;
import enums.FieldID;
import enums.ItemType;
import gamemechanics.encounter.Encounter;
import lombok.Getter;
import model.Item.Token;

@Getter
public class ClueToken extends Token {
    private final FieldID fieldID;
    private ItemStack stack;

    public ClueToken(FieldID fieldID) {
        super(ItemType.CLUE_TOKEN);
        this.fieldID =fieldID;
    }


    @Override
    public String getId() {
        return "&clueToken";
    }

    @Override
    public String getNameId() {
        return "${clue_token}";
    }

    @Override
    public Encounter getEncounter() {
        return GameService.getInstance().getReseaarchEncounter().draw();
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
