package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import elements.Asset;
import enums.AssetNames;
import exceptions.CardNotFoundException;
import gameItems.Stack;

public class StackTest {
	Stack<Asset> stack = new Stack<Asset>(true);
	Stack<Asset> stack2 = new Stack<Asset>(true);
	Asset card;
	Asset card2;
	@Before
	public void setUp() throws Exception {
		for(int i = 0; i<10; i++){
			this.card = new Asset(AssetNames.values()[i]);
			this.card.setAttribute("A"+i);
			stack.add(this.card);
		}
		
		for(int i = 0; i<10; i++){
		//	this.card2 = new Asset("New"+ItemNames.values()[i]);
			this.card2.setAttribute("New A"+i);
			stack2.add(this.card2);
		}
	}

	@Test
	public void testShuffle() {
		//System.out.println(stack.toString());
		stack.shuffle();
		//System.out.println(stack.toString());
	}
	
	@Test
	public void testAddStack() {
		System.out.println(stack.toString());
		System.out.println(stack2.toString());
		stack.addStack(stack2);
		System.out.println(stack.toString());
		System.out.println(stack2.toString());
	}

//	@Test
//	public void testClear() {
//		System.out.println(stack.toString());
//		stack.clear();
//		System.out.println(stack.toString());
//	}

	@Test
	public void testContainsT() {
		Asset card = new Asset(AssetNames.values()[1]);
		card.setAttribute("A1");
		assertFalse(stack.contains(card));
		assertTrue(stack.contains(this.card));
	}

	@Test
	public void testContainsString() {
		assertFalse(stack.contains(" 1"));
		assertTrue(stack.contains("1"));
		assertTrue(stack.contains("A1"));
	}

	@Test
	public void testIndexT() {
		assertTrue(stack.index(this.card)!=-1);
	//	assertTrue(stack.index(new Asset("x"))==-1);
	}

	@Test
	public void testIndexString() {
		assertTrue(stack.index(" 1")==-1);
		assertTrue(stack.index("1")!=-1);
		assertTrue(stack.index("A1")!=-1);
	}

	@Test(expected = CardNotFoundException.class)
	public void testDrawCardT() throws CardNotFoundException {
		stack.drawCard(this.card);
		assertFalse(stack.contains(this.card));
		stack.add(this.card);
		assertTrue(stack.contains(this.card));
		//stack.drawCard(new Asset("x"));
	}

	@Test(expected = CardNotFoundException.class)
	public void testDrawCardString() throws CardNotFoundException {
		stack.drawCard(this.card.getName());
		assertFalse(stack.contains(this.card.getName()));
		stack.add(this.card);
		assertTrue(stack.contains(this.card.getName()));
		stack.drawCard("-1");
	}

	@Test(expected = CardNotFoundException.class)
	public void testDrawCardInt()throws CardNotFoundException {
		int index = stack.index(this.card);
		stack.drawCard(index);
		assertFalse(stack.contains(this.card));
		stack.add(this.card);
		assertTrue(stack.contains(this.card));
		stack.drawCard(121);
	}

	@Test(expected = CardNotFoundException.class)
	public void testRemoveCardT()throws CardNotFoundException {
		stack.removeCard(this.card);
		stack.add(this.card);
		//stack.removeCard(new Asset("x"));
	}

	@Test
	public void testDropCard()throws CardNotFoundException {
		for(int i = 0;i<100;i++){
					stack.dropCard(stack.drawNextCard());
					System.out.println(stack);
		}

	}

//	@Test(expected = CardNotFoundException.class)
//	public void testRemoveCardString()throws CardNotFoundException {
//		stack.removeCard(this.card.getName());
//		stack.add(this.card);
//		stack.removeCard("123");
//	}
//
//	@Test(expected = CardNotFoundException.class)
//	public void testRemoveCardInt()throws CardNotFoundException {
//		stack.removeCard(stack.index(this.card));
//		stack.add(this.card);
//		stack.removeCard(123);
//	}


}
