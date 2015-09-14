package test;


import java.util.Random;

import org.junit.Test;

import gameItems.Round;

public class RoundTest {
	Round lap = new Round(3);
	Random rnd = new Random(1234);
	@Test
	public void testNext() {
		for(int i= 0; i< 10;i++){
			
			System.out.println("Runde: " + lap.getNumberOfRounds());
			System.out.println("Ermittlungsleiter: " + lap.getLeadInvestigator());
			for(int j = 0; j<3; j++){
				System.out.println("Phase: " + lap.getPhase());
				for(int h = 0; h<3; h++){
					System.out.println("Aktiver Spieler: " + lap.getActiveInvestigator());
					lap.next();
				}
			}
			lap.setLeadInvestigator(rnd.nextInt(3)+1);
		}
		
	}

}
