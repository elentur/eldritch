package gui;

import javafx.scene.image.Image;

public class GameTextures {

	public static Image[] assetPicture;
	public static Image[] spellPicture;
	public static Image itemBackPicture;
	public static Image assetSymbol;
	public static Image spellSymbol;
	
	public static Image gameBoard;
	public static Image mark;
	public static Image cityMark;
	public static Image expeditionMarkWilderness;
	public static Image expeditionMarkSea;
	public static Image citySpace;
	public static Image wildernessSpace;
	public static Image seaSpace;
	
	public static Image clue;
	
	public static void load(){
		assetPicture= new Image[37];
		for(int i = 0; i< assetPicture.length; i++){		
			assetPicture[i]= new Image("images/assets/" + i + ".jpg",402,206, true, false, true);	
		}
		spellPicture= new Image[10];
		for(int i = 0; i< spellPicture.length; i++){		
			spellPicture[i]= new Image("images/spells/" + i + ".jpg",402,206, true, false, true);	
		}
		
		itemBackPicture= new Image("images/assets/ItemBack.png",373,282, true, false, true);	
		assetSymbol= new Image("images/assets/AssetSymbol.png",54,50, true, false, true);		
		spellSymbol= new Image("images/spells/SpellSymbol.png",54,50, true, false, true);		
		clue= new Image("images/clue.png",75,75, true, false, true);		
		
		
		gameBoard= new Image("images/gameBoard/GameBoard.jpg",5804,3594, true, false, true);
		mark= new Image("images/gameBoard/Mark.png",66,58, true, false, true);
		cityMark= new Image("images/gameBoard/CityMark.png",518,154, true, false, true);
		expeditionMarkWilderness= new Image("images/gameBoard/ExpeditionMarkWilderness.png",558,170, true, false, true);
		expeditionMarkSea= new Image("images/gameBoard/ExpeditionMarkSea.png",558,152, true, false, true);
		citySpace= new Image("images/gameBoard/CitySpace.png",61,61, true, false, true);
		wildernessSpace= new Image("images/gameBoard/WildernessSpace.png",61,61, true, false, true);
		seaSpace= new Image("images/gameBoard/SeaSpace.png",61,61, true, false, true);

	}
}
