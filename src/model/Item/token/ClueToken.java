package model.Item.token;

import Service.GameService;
import enums.FieldID;
import enums.ItemType;
import gamemechanics.encounter.Encounter;
import lombok.Getter;
import model.Item.Token;

@Getter
public class ClueToken extends Token {
    private final FieldID fieldID;

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
        return GameService.getInstance().drawResearchEncounter();
    }
}
