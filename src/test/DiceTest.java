package test;

import org.junit.Test;

import elements.Investigator;
import enums.Skills;
import gameItems.Dice;

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
