package oldVersion.test;

import org.junit.Test;

import oldVersion.elements.Investigator;
import oldVersion.enums.Skills;
import oldVersion.gameItems.Dice;

public class DiceTest {
	Dice dice = new Dice(Skills.influence,new Investigator("CharlieKane"));

	@Test
	public void test() {
		System.out.println(dice.roll());
		System.out.println(dice.roll());
		System.out.println(dice.roll());
		System.out.println(dice.roll());
		System.out.println(dice.roll());
		System.out.println(dice.roll());
		System.out.println(dice.roll());
	}

}
