package model;

import enums.TestTyp;
import factory.InvestigatorFactory;
import factory.ItemFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class InvestigatorTest {

    Investigator investigator;

    @BeforeMethod
    public void setUp() throws Exception {
        InvestigatorFactory investigatorFactory = new InvestigatorFactory();
        investigator = investigatorFactory.getInvestigators().get("&agnesBaker");
    }

    @Test
    public void testGetSkill() throws Exception {
        Investigator inv = investigator.getInstance();
        Assert.assertEquals(inv.getSkill(TestTyp.LORE), 4);
        Assert.assertEquals(inv.getSkill(TestTyp.INFLUENCE), 3);
        Assert.assertEquals(inv.getSkill(TestTyp.OBSERVATION), 2);
        Assert.assertEquals(inv.getSkill(TestTyp.STRENGTH), 2);
        Assert.assertEquals(inv.getSkill(TestTyp.WILL), 2);
    }

    @Test
    public void testGetConditions() throws Exception {
    }

    @Test
    public void testGetInstance() throws Exception {
        Investigator inv = investigator.getInstance();
        Assert.assertEquals(inv.getFirstName(), investigator.getFirstName());
        Assert.assertEquals(inv.getLastName(), investigator.getLastName());
        Assert.assertEquals(inv.getOccupation(), investigator.getOccupation());
        Assert.assertEquals(inv.getSkillSet(), investigator.getSkillSet());
        Assert.assertEquals(inv.getHealth(), investigator.getHealth());
        Assert.assertEquals(inv.getSanity(), investigator.getSanity());
        Assert.assertEquals(inv.getStartingSpace(), investigator.getStartingSpace());
        Assert.assertEquals(inv.getStartingPossessions(), investigator.getStartingPossessions());
        ItemFactory itemFactory = new ItemFactory();
        Assert.assertTrue(inv.getInventory().contains(itemFactory.getAssets().get("&profaneTome")));
        Assert.assertTrue(inv.getInventory().contains(itemFactory.getSpells().get("&stormOfSpirits")));
    }

}