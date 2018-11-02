package oldVersion.gameItems;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import oldVersion.elements.Element;
import oldVersion.elements.Item;
import oldVersion.enums.AssetNames;
import oldVersion.enums.SpellNames;
import oldVersion.elements.Asset;
import oldVersion.elements.Card;
import oldVersion.exceptions.CardNotFoundException;
import oldVersion.gui.ItemGraphic;
import javafx.scene.Node;
import utils.RNG;

/**
 * 
 * @author Marcus B�tz
 * @version 1 13.08.2015
 * 
 * 
 * Diese Klasse soll als ADT f�r alle m�glichen Karten vom Typ Card oder dessen Unterklassen dienen
 * 
 */
public class Stack<T extends Element> implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<T> drawStack = new ArrayList<T>();
	private List<T> dropStack = new ArrayList<T>();
	private boolean hasDropStack=false;
	public Stack(boolean hasDropStack){
		this.hasDropStack =hasDropStack;
	}
	
	public void dropCard(T card){
		if(hasDropStack)dropStack.add(card);
	}
	public boolean isEmpty(){
		return drawStack.isEmpty();
	}
	public void addOnTop(T card){
		drawStack.add(0,card);
	}
	public void add(T card){
		drawStack.add(card);
	}
	public void addStack(Stack<T> stack){
		drawStack.addAll(stack.getStack());
		stack.clear();
	}
	
	public List<T> getStack(){
		return drawStack;
	}
	
	public void shuffle(){
		List<T> tmpStack = new ArrayList<T>(drawStack);
		drawStack.clear();

		while(tmpStack.size()>0){
			drawStack.add(tmpStack.remove(RNG.getInt(tmpStack.size())));
		}
		
	}
	
	public void clear(){
		drawStack.clear();
	}
	
	public boolean contains(T card){
		return drawStack.contains(card);
	}
	public boolean contains(String cardName){
		for( T card: drawStack){
			if(card.getName().equals(cardName)|| equalsTyp(card,cardName))return true;
		}
		if(hasDropStack){
			for( T card: dropStack){
				if(card.getName().equals(cardName)|| equalsTyp(card,cardName))return true;
			}
		}
		return false;
	}
	public int index(T card){
		return drawStack.indexOf(card);
	}
	public int index(String cardName){
		for(int i = 0; i< getSize();i++){
			if(i<drawStack.size()){
				if(drawStack.get(i).getName().equals(cardName)|| equalsTyp(drawStack.get(i), cardName))return i;
			}
			if(hasDropStack && i<dropStack.size()){
				if(dropStack.get(i).getName().equals(cardName)|| equalsTyp(dropStack.get(i), cardName))return i;
			}
		}
		return-1;
	}
	public int index(int itemNumber){
		for(int i = 0; i< getSize();i++){
			if(i<drawStack.size()){
				if(((Item)drawStack.get(i)).getItemNumber()==itemNumber)return i;
			}
			if(hasDropStack && i<dropStack.size()){
				if(((Item)dropStack.get(i)).getItemNumber()==itemNumber)return i;
			}
		}
		return-1;
	}

	public T showNextCard()throws CardNotFoundException{
		try{
			return drawStack.get(0);
		} catch(Exception e){
			throw new CardNotFoundException();
		}
	}
	
	public T drawNextCard()throws CardNotFoundException{
		try{
			T newCard= drawStack.get(0);
			removeCard(newCard);
			return newCard;
		} catch(Exception e){
			throw new CardNotFoundException();
		}
	}
	
	public T drawLastCard()throws CardNotFoundException{
		try{
			T newCard= drawStack.get(drawStack.size()-1);
			removeCard(newCard);
			return newCard;
		} catch(Exception e){
			throw new CardNotFoundException();
		}
	}
	
	public T drawCard(T card)throws CardNotFoundException{
		try{
			T newCard= drawStack.get(index(card));
			removeCard(newCard);
			return newCard;
		} catch(Exception e){
			throw new CardNotFoundException();
		}
	}
	public T drawCard(String cardName)throws CardNotFoundException{
		try{
			T newCard= drawStack.get(index(cardName));
			removeCard(newCard);
			return newCard;
		} catch(Exception e){
			throw new CardNotFoundException();
		}
	}
	public T drawCard(AssetNames assetName)throws CardNotFoundException{
		try{
			T newCard= drawStack.get(index(assetName.ordinal()));
			removeCard(newCard);
			return newCard;
		} catch(Exception e){
			throw new CardNotFoundException();
		}
	}
	public T drawCard(SpellNames spellName)throws CardNotFoundException{
		try{
			T newCard= drawStack.get(index(spellName.ordinal()));
			removeCard(newCard);
			return newCard;
		} catch(Exception e){
			throw new CardNotFoundException();
		}
	}


	public T drawCard(int index)throws CardNotFoundException{
		try{
			T newCard= drawStack.get(index);
			removeCard(newCard);
			return newCard;
		} catch(Exception e){
			throw new CardNotFoundException();
		}
	}
	public void removeCard(T card)throws CardNotFoundException{
			if (!drawStack.remove(card)) throw new CardNotFoundException();
			if(isEmpty() && hasDropStack){
				drawStack.addAll(dropStack);
				dropStack.clear();
				shuffle();
			}

	}
	
	private boolean equalsTyp(T card,String cardName){
		if (card instanceof Asset && ((Asset) card).getAttribute().equals(cardName))return true;
		return false;
		
	}
//	public void removeCard(String cardName)throws CardNotFoundException{
//		for( Card card: stackArray){
//			if(card.getName().equals(cardName)|| card.getAttribute().equals(cardName)){
//				if (!stackArray.remove(card)) throw new CardNotFoundException();
//				return;
//			}
//		}
//		throw new CardNotFoundException();
//	}
//	public void removeCard(int index)throws CardNotFoundException{
//		try{
//			stackArray.remove(stackArray.get(index));
//		}catch(IndexOutOfBoundsException e){
//			throw new CardNotFoundException();
//		}
//	}
	public int getSize(){
		return drawStack.size();
	}
	public int getSizeDropStack(){
		return dropStack.size();
	}
	
	public String toString(){
		String s="#########draw############\n";
		for( T card: drawStack){
			s=s+card.getName()+"\n";
		}
		if(hasDropStack){
			s=s+"#########drop############\n";
			for( T card: dropStack){
				s=s+card.getName()+"\n";
			}
		}
		
		return s;
	}

	public Node[] getNodes() {
		Node[] nodes = new Node[getSize()];
		int i = 0;
		for(T card : drawStack){
			try{
					nodes[i]=new ItemGraphic((Item)card);
					i++;
			}catch(Exception e){
				System.out.println("Fehler in Stack getNodes");
			}
		}
		return nodes;
	}
}
