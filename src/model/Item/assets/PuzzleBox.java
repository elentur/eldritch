package model.Item.assets;

import enums.ItemType;
import model.Item.Asset;
import model.Item.ItemBonus;

import java.util.ArrayList;
import java.util.List;


public class PuzzleBox extends Asset {

    public PuzzleBox() {
        super(ItemType.TRINKET, 3);
    }

    @Override
    public String getId() {
        return "&puzzleBox";
    }

    @Override
    public String getNameId() {
        return "${puzzle_box}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        //TODO
        return boni;
    }


}
