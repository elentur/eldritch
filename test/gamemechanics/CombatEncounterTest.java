package gamemechanics;

import Service.GameService;
import factory.InvestigatorFactory;
import factory.MonsterFactory;
import model.Investigator;
import model.Monster;
import org.junit.Test;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class CombatEncounterTest {

    @Test
    public void testCombatEncounter(){
        Investigator inv = new InvestigatorFactory().getInvestigators().get(0).getInstance();
        Monster monster = new MonsterFactory().getMonster().get(0).getInstance();
        GameService game = GameService.getInstance();
        List<Monster> monsters= new ArrayList<>();
        monsters.add(monster);
        CombatEncounter combatEncounter= new CombatEncounter(monsters,inv);
        List<Monster> availableMonsters = combatEncounter.getAvailableMonster();
        Assert.assertEquals(availableMonsters,monsters);
        combatEncounter.setActiveMonster(availableMonsters.get(0));
    }

}