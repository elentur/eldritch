package gameItems;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Dice implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public ArrayList<Integer> roll(int dice){
		Random rnd =new Random(1234);
		ArrayList<Integer> allDice = new ArrayList<Integer>();
		for(int i= 0 ; i < dice;i++){
			allDice.add(rnd.nextInt(6)+1);
		}
		allDice.sort(Collections.reverseOrder());
		return allDice;
	}
}
