package model.Item.token;

import container.ItemStack;
import enums.FieldID;
import enums.ItemType;
import gamemechanics.encounter.Encounter;
import gamemechanics.encounter.ExpeditionEncounter;
import gamemechanics.encounter.RumorEncounter;
import lombok.Getter;
import model.Item.Mythos;
import model.Item.MythosBlue;
import model.Item.Token;
@Getter
public class RumorToken extends Token {
    private final MythosBlue mythos;
    private final FieldID fieldID;
    private ItemStack stack;

    public RumorToken(FieldID fieldID, MythosBlue mythos) {
        super(ItemType.RUMOR_TOKEN);
         this.mythos = mythos;
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

    public Mythos getMythos(){
        return mythos;
    }
    @Override
    public Encounter getEncounter() {
        return mythos.getEncounter();
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
