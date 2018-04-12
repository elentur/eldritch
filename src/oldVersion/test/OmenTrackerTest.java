package oldVersion.test;

import static org.junit.Assert.*;

import org.junit.Test;

import oldVersion.enums.Omen;
import oldVersion.gameItems.OmenTracker;

public class OmenTrackerTest {
	OmenTracker omenTracker = new OmenTracker();
	@Test
	public void testAdvanceOmen() {
		assertTrue(omenTracker.advanceOmen()==Omen.starsign);
		assertTrue(omenTracker.advanceOmen()==Omen.sun);
		assertTrue(omenTracker.advanceOmen()==Omen.starsign);
		assertTrue(omenTracker.advanceOmen()==Omen.comet);
		assertTrue(omenTracker.advanceOmen()==Omen.starsign);
		assertTrue(omenTracker.advanceOmen()==Omen.sun);
		assertTrue(omenTracker.advanceOmen()==Omen.starsign);
	}

	@Test
	public void testRetrieveOmen() {
		assertTrue(omenTracker.retrieveOmen()==Omen.starsign);
		assertTrue(omenTracker.retrieveOmen()==Omen.sun);
		assertTrue(omenTracker.retrieveOmen()==Omen.starsign);
		assertTrue(omenTracker.retrieveOmen()==Omen.comet);
		assertTrue(omenTracker.retrieveOmen()==Omen.starsign);
		assertTrue(omenTracker.retrieveOmen()==Omen.sun);
		assertTrue(omenTracker.retrieveOmen()==Omen.starsign);
	}

	@Test
	public void testActualOmen() {
		assertTrue(omenTracker.actualOmen()==Omen.comet);
		omenTracker.advanceOmen();
		omenTracker.advanceOmen();
		assertTrue(omenTracker.actualOmen()==Omen.sun);
		omenTracker.retrieveOmen();
		omenTracker.retrieveOmen();
		omenTracker.retrieveOmen();
		omenTracker.retrieveOmen();
		omenTracker.retrieveOmen();
		assertTrue(omenTracker.actualOmen()==Omen.starsign);
	}

}
