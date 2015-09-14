package test;


import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import elements.Asset;
import gameItems.Reserve;
import gameItems.Stack;

public class AssetDeckTest {
	Reserve reserve;
	Stack<Asset> supportDeck = new Stack<Asset>(true);
	Asset card;

	@Before
	public void setUp() throws Exception {
		for(int i = 0; i<10; i++){
			//this.card = new Asset(""+i);

			this.card.setAttribute("A"+i);
			supportDeck.add(this.card);
		}
		supportDeck.shuffle();
		reserve = new Reserve(supportDeck);
	}

	@Test
	public void testRefill() {
		reserve.refill();
		System.out.println(Arrays.toString(reserve.showAssets()));
	}

	@Test
	public void testShowAssets() {
		System.out.println(Arrays.toString(reserve.showAssets()));
	}

	@Test
	public void testGetAsset() {
		reserve.getAsset(1);
		System.out.println(Arrays.toString(reserve.showAssets()));
		reserve.refill();
		System.out.println(Arrays.toString(reserve.showAssets()));
	}

}
