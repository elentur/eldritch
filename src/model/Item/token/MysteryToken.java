package model.Item.token;

import Service.GameService;
import container.ItemStack;
import enums.ItemType;
import gamemechanics.encounter.Encounter;
import model.Item.Token;

public class MysteryToken extends Token {
    private ItemStack stack;
    private Encounter encounter;
    public MysteryToken() {
       this(null);
    }

    public MysteryToken(Encounter encounter) {
        super(ItemType.MYSTERY_TOKEN);
        this.encounter = encounter;
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
        if(encounter!= null){
            return encounter;
        }
        return GameService.getInstance().getSpecialEncounter().showFirst();
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
