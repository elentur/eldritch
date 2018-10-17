package model.Item.token;

import container.ItemStack;
import enums.ItemType;
import gamemechanics.encounter.Encounter;
import gamemechanics.encounter.MysteryEncounter;
import gamemechanics.encounter.RumorEncounter;
import model.Item.Token;

public class EldritchToken extends Token {
    private final Encounter encounter;
    private ItemStack stack;

    public EldritchToken(MysteryEncounter encounter) {
        super(ItemType.ELDRITCH_TOKEN);
        this.encounter=encounter;
       if( encounter!=null){
           encounter.setEldritchToken(this);
       }
    }

    @Override
    public String getId() {
        return "&eldritchToken";
    }

    @Override
    public String getNameId() {
        return "${eldritch_token}";
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
