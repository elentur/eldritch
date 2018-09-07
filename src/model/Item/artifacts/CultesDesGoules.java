package model.Item.artifacts;

import enums.ItemType;
import enums.SituationType;
import enums.TestType;
import model.Item.Artifact;
import model.Item.ItemBonus;
import model.Item.boni.ItemBonus_DiceResult;

import java.util.ArrayList;
import java.util.List;


public class CultesDesGoules extends Artifact {

    public CultesDesGoules() {
        super(ItemType.ITEM_TOME);
    }

    @Override
    public String getId() {
        return "&cultesDesGoules";
    }

    @Override
    public String getNameId() {
        return "${cultes_des_goules}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        return boni;
    }


}
