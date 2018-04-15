package factory;

import enums.EffectTyps;
import model.Effect;
import model.Monster;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MonsterFactoryTest {
    Monster monster;
    @BeforeMethod
    public void setUp() throws Exception {
        monster = new Monster();
        monster.setId("&houndOfTindalos");
        monster.setName("${houndOfTindalos}");
        monster.setImage("");
        monster.setWillTest(0);
        monster.setHorror(2);
        monster.setStrengthTest(-1);
        monster.setDamage(3);
        monster.setToughness(3);
        List<Effect> effects = new ArrayList<>();
        Effect effect = new Effect();
        effect.setTyp(EffectTyps.RECKONING);
        List<List<String>> parts = new ArrayList<>();
        parts.add(Arrays.asList( "move to",
                "nearest",
                "Investigator"));
        parts.add(Arrays.asList( "fight"));
        effect.setParts(parts);
        effects.add(effect);
        monster.setEffects(effects);
    }

    @Test
    public void testGetMonster() throws Exception {

        MonsterFactory factory = new MonsterFactory();
       List<Monster> monsters =  factory.getMonster();
       Assert.assertEquals(monsters.get(0),monster);
        Assert.assertEquals(monsters,factory.getMonster());
        Assert.assertEquals(monster.getName(),"Hound of Tindalos");
    }

}