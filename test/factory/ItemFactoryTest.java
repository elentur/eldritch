package factory;

import enums.ConditionTyp;
import enums.ItemTyp;
import enums.TestTyp;
import model.Item.Asset;
import model.Item.ItemBonus;
import model.Item.ItemBonus_DiceResult;
import org.junit.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class ItemFactoryTest {
    Asset asset;
    @BeforeMethod
    public void setUp() throws Exception {
    asset = new Asset();
    asset.setName(".18 Derringer");
    asset.setTyp(ItemTyp.TRINKET_WEAPON);
    ItemBonus bonus  = new ItemBonus_DiceResult(1,1, TestTyp.STRENGTH, ConditionTyp.COMBAT_ENCOUNTER);
    List<ItemBonus> boni = new ArrayList<>();
    boni.add(bonus);
    asset.setBonus(boni);
    }

    @Test
    public void testGetAssets() throws Exception {

        ItemFactory itemFactory = new ItemFactory();
        List<Asset> assets = itemFactory.getAssets();
        Assert.assertTrue((assets.contains(asset)));
    }

}