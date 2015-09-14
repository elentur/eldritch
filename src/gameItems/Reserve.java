package gameItems;

import elements.Asset;
import exceptions.CardNotFoundException;

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
}
