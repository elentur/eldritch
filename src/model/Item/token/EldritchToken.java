package model.Item.token;

import enums.ItemType;
import gamemechanics.encounter.Encounter;
import gamemechanics.encounter.RumorEncounter;
import model.Item.Token;

public class EldritchToken extends Token {
    private final Encounter encounter;

    public EldritchToken(Encounter encounter) {
        super(ItemType.ELDRITCH_TOKEN);
        this.encounter=encounter;
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
}
