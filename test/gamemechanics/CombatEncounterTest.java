package gamemechanics;

import Service.GameService;
import container.ItemContainer;
import factory.InvestigatorFactory;
import factory.ItemFactory;
import factory.MonsterFactory;
import model.Investigator;
import model.Item.Item;
import model.Monster;
import org.junit.Test;
import org.testng.Assert;
import preparation.CombatPreparation;

import java.util.ArrayList;
import java.util.List;


public class CombatEncounterTest {

    @Test
    public void testCombatEncounter() {
        Investigator inv = new InvestigatorFactory().getInvestigators().get(0).getInstance();
        Monster monster = new MonsterFactory().getMonster().get(0).getInstance();
        GameService game = GameService.getInstance();
        List<Monster> monsters = new ArrayList<>();
        monsters.add(monster);
        CombatEncounter combatEncounter = new CombatEncounter(monsters, inv);
        List<Monster> availableMonsters = combatEncounter.getAvailableMonster();
        Assert.assertEquals(availableMonsters, monsters);
        combatEncounter.setActiveMonster(availableMonsters.get(0));
        Assert.assertNotNull(combatEncounter.getActiveMonster());
        CombatPreparation preparation = combatEncounter.prepareForCombat();
        ItemContainer<Item> bonusItems =preparation.getBonusItems();
        ItemContainer<Item> expectedItems = new ItemContainer<>();
        expectedItems.add(new ItemFactory().getAssets().get("&profaneTome"));
        expectedItems.add(new ItemFactory().getSpells().get("&stormOfSpirits"));
        Assert.assertEquals(bonusItems,expectedItems);


    }

}