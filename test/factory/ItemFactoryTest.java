package factory;

import container.ItemContainer;
import enums.SituationTyp;
import enums.ItemTyp;
import enums.TestTyp;
import model.Item.Asset;
import model.Item.ItemBonus;
import model.Item.ItemBonus_DiceResult;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ItemFactoryTest {
    Asset asset;
    @BeforeMethod
    public void setUp() throws Exception {
    asset = new Asset();
    asset.setName("${18_derringer}");
    asset.setId("&18Derringer");
    asset.setPrice(1);
    asset.setTyp(ItemTyp.TRINKET_WEAPON);
    ItemBonus bonus  = new ItemBonus_DiceResult(1,1, TestTyp.STRENGTH, SituationTyp.COMBAT_ENCOUNTER);
    List<ItemBonus> boni = new ArrayList<>();
    boni.add(bonus);
    asset.setBonus(boni);
    }

    @Test
    public void testGetAssets() throws Exception {

        ItemFactory itemFactory = new ItemFactory();
        ItemContainer<Asset> assets = itemFactory.getAssets();
        Assert.assertTrue((assets.contains(asset)));
        Assert.assertEquals(asset.getName(),".18 Derringer");
    }

}