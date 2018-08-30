package model.Item.token;

import container.ItemStack;
import enums.FieldID;
import enums.ItemType;
import gamemechanics.encounter.Encounter;
import gamemechanics.encounter.ExpeditionEncounter;
import gamemechanics.encounter.RumorEncounter;
import lombok.Getter;
import model.Item.Token;
@Getter
public class RumorToken extends Token {
    private final RumorEncounter encounter;
    private final FieldID fieldID;
    private ItemStack stack;

    public RumorToken(FieldID fieldID, RumorEncounter encounter) {
        super(ItemType.RUMOR_TOKEN);
        this.encounter=encounter;
        this.fieldID=fieldID;
    }

    @Override
    public String getId() {
        return "&rumorToken";
    }

    @Override
    public String getNameId() {
        return "${rumor_token}";
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
