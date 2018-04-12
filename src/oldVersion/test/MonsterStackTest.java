package oldVersion.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import oldVersion.elements.Monster;
import oldVersion.gameBuild.Build;
import oldVersion.gameBuild.Global;
import oldVersion.gameItems.AncientOne;
import oldVersion.gameItems.Stack;

public class MonsterStackTest { Stack<Monster> monsterPool;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Global.language="src/oldVersion.language/english";
		
	}

	@Before
	public void setUp() throws Exception {
		monsterPool= Build.buildMonsterPool(new AncientOne("Azathoth"));
	}

	@Test
	public void test() {
		for(Monster monster : monsterPool.getStack())
			System.out.println(monster);
		
		System.out.println(monsterPool.getSize());
	}
	


}
