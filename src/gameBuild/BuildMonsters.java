package gameBuild;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import enums.MonsterNames;
import gameMechanics.Event;

public class BuildMonsters {

	public static int getHorror(MonsterNames name) {
		Set<Integer> one = new HashSet<Integer>(Arrays.asList(1,8,9,10,14,15,23,24,27));
		Set<Integer> three = new HashSet<Integer>(Arrays.asList(4,6,20,22));
		Set<Integer> zero = new HashSet<Integer>(Arrays.asList(25));
		Set<Integer> nothing = new HashSet<Integer>(Arrays.asList(18));

		int value= name.ordinal();
		if(one.contains(value))return 1;
		if(three.contains(value))return 3;
		if(zero.contains(value))return 0;
		if(nothing.contains(value))return -1;
		return 2;
	
	}

	public static int getToughness(MonsterNames name) {
		Set<Integer> one = new HashSet<Integer>(Arrays.asList(8,14,24,25));
		Set<Integer> three = new HashSet<Integer>(Arrays.asList(6,9,12,15,16,18,23,26));
		Set<Integer> four = new HashSet<Integer>(Arrays.asList(3,11,13,20));
		Set<Integer> five = new HashSet<Integer>(Arrays.asList(4,22));

		int value= name.ordinal();
		if(one.contains(value))return 1;
		if(three.contains(value))return 3;
		if(four.contains(value))return 4;
		if(five.contains(value))return 5;
		return 2;
	
	}

	public static int getDamage(MonsterNames name) {
		Set<Integer> one = new HashSet<Integer>(Arrays.asList(5,17,21,24,25));
		Set<Integer> three = new HashSet<Integer>(Arrays.asList(3,4,11,12,13,18,19,22));
		Set<Integer> nothing = new HashSet<Integer>(Arrays.asList(2,7));

		int value= name.ordinal();
		if(one.contains(value))return 1;
		if(three.contains(value))return 3;
		if(nothing.contains(value))return -1;
		return 2;

	}

	public static int getStrengthTest(MonsterNames name) {
		Set<Integer> one = new HashSet<Integer>(Arrays.asList(5,6,12,14,19,24,25,26,27));
		Set<Integer> three = new HashSet<Integer>(Arrays.asList(4,18,22));
		Set<Integer> zero = new HashSet<Integer>(Arrays.asList(1,2,7,8));

		int value= name.ordinal();
		if(one.contains(value))return -1;
		if(three.contains(value))return -3;
		if(zero.contains(value))return 0;
		return -2;
	}

	public static int getWillTest(MonsterNames name) {
		Set<Integer> one = new HashSet<Integer>(Arrays.asList(8,14));
		Set<Integer> negativeOne = new HashSet<Integer>(Arrays.asList(3,4,7,22,25,26));
		
		int value= name.ordinal();
		if(one.contains(value))return 1;
		if(negativeOne.contains(value))return -1;
		return 0;
	}

	public static Event getEvent(MonsterNames name) {
		// TODO Auto-generated method stub
		return null;
	}

}
