package test;

import org.junit.Test;

import gameItems.Dice;

public class DiceTest {
	Dice dice = new Dice();

	@Test
	public void test() {
		System.out.println(dice.roll(0));
		System.out.println(dice.roll(1));
		System.out.println(dice.roll(2));
		System.out.println(dice.roll(5));
		System.out.println(dice.roll(12));
		System.out.println(dice.roll(100));
		System.out.println(dice.roll(150));
	}

}
