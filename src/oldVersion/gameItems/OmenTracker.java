package oldVersion.gameItems;

import java.io.Serializable;

import oldVersion.enums.Omen;

public class OmenTracker implements Serializable {
	Omen omen = Omen.comet;
	int omenCount=1;
	private static final long serialVersionUID = 1L;
	
	public Omen advanceOmen(){
		omenCount++;
		return actualOmen();
		
	}
	public Omen retrieveOmen(){
		omenCount--;
		return actualOmen();
	}
	
	public Omen actualOmen(){
		if(omenCount%2==0){
			return Omen.starsign;
		}else if((omenCount-1)%4==0){
			return Omen.comet;
		}else{
			return Omen.sun;
		}
	}
}
