package gamemechanics;

import Service.GameService;
import container.ItemContainer;
import factory.ItemFactory;
import model.Investigator;
import model.Item.Item;
import model.Item.investigators.AgnesBaker;
import model.Item.monsters.Vampire;
import model.Monster;
import org.junit.Test;
import org.testng.Assert;
import preparation.Preparation;

import java.util.ArrayList;
import java.util.List;


public class CombatEncounterTest {

    @Test
    public void testCombatEncounter() {
        Investigator inv = new AgnesBaker();
        Monster monster = new Vampire();
        GameService game = GameService.getInstance();
        List<Monster> monsters = new ArrayList<>();
        monsters.add(monster);
        CombatEncounter combatEncounter = new CombatEncounter(monsters, inv);
        List<Monster> availableMonsters = combatEncounter.getAvailableMonster();
        Assert.assertEquals(availableMonsters, monsters);
        combatEncounter.setActiveMonster(availableMonsters.get(0));
        Assert.assertNotNull(combatEncounter.getActiveMonster());
        Preparation preparation = combatEncounter.getPreparation();
        ItemContainer<Item> expectedItems = new ItemContainer<>();
        expectedItems.add(new ItemFactory().getAssets().get("&profaneTome"));
        expectedItems.add(new ItemFactory().getSpells().get("&stormOfSpirits"));


    }

}