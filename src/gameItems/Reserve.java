package gameItems;

import elements.Asset;
import elements.Item;
import exceptions.CardNotFoundException;
import gameMechanics.DiceBonus;
import gui.ItemGraphic;
import javafx.scene.Group;
import javafx.scene.Node;

public class Reserve {
	private Asset[] reserve =new Asset[4];
	private Stack<Asset> assetDeck;
	private boolean refillNeedet =true;
	
	public Reserve(Stack<Asset> assetDeck){
		this.assetDeck =assetDeck;
		refill();
	}
	
	public void refill(){
		if(refillNeedet){
			for(int i = 0 ; i < 4;i++){
				if (reserve[i] ==null)
					try {
						reserve[i]= assetDeck.drawNextCard();
					} catch (CardNotFoundException e) {
						// Keine Karten mehr auf dem Stapel
					}
			}
		}
	}
	
	public Asset[] showAssets(){
		return reserve;//kann auch null Elemente enthalten
	}
	
	public Asset getAsset(int i){
		Asset newCard = reserve[i];
		reserve[i]=null;
		return newCard;//kann auch null returnen
	}
	public int getSize(){
		int i=0;
		for(Asset asset : reserve){
			if (asset!=null) i++;
		}
		return i;
	}
	public Node[] getNodes() {
		Node[] nodes = new Node[4];
		int i = 0;
		for(Asset asset : reserve){
			try{
					if(asset != null)nodes[i]=new ItemGraphic((Item)asset,true);
					else nodes[i]= new Group();
					i++;
			}catch(Exception e){
				nodes[i]=new Group();
				//System.out.println("Fehler in Stack getNodes");
			}
		}
		return nodes;
	}
}
