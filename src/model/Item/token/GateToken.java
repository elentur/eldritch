package model.Item.token;

import Service.GameService;
import enums.ItemType;
import gamemechanics.encounter.Encounter;
import model.Item.Token;

public class GateToken extends Token {
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
        return GameService.getInstance().drawOtherWorldEncounter();
    }
}
