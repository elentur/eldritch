package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import elements.Monster;
import gameBuild.Build;
import gameBuild.Global;
import gameItems.AncientOne;
import gameItems.Stack;

public class MonsterStackTest { Stack<Monster> monsterPool;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Global.language="src/language/english";
		
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
