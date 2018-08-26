package model.Item.token;

import enums.ItemType;
import gamemechanics.encounter.Encounter;
import gamemechanics.encounter.ExpeditionEncounter;
import model.Item.Token;

public class ExpeditionToken extends Token {
    private final ExpeditionEncounter encounter;

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
}
