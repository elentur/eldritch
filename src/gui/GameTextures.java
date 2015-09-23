package gui;

import gameBuild.Global;
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
	public static Image gate;
	
	public static Image cultist;
	public static Image byakee;
	public static Image colorOfSpace;
	public static Image cthonian;
	public static Image darkYoung;
	public static Image deepOne;
	public static Image elderThing;
	public static Image ghost;
	public static Image ghoul;
	public static Image gnophKeh;
	public static Image goatSpawn;
	public static Image gug;
	public static Image houndOfTindalos;
	public static Image lloigor;
	public static Image maniac;
	public static Image miGo;
	public static Image mummy;
	public static Image nightgaunt;
	public static Image riot;
	public static Image serpentPeople;
	public static Image shoggoth;
	public static Image skeleton;
	public static Image starSpawn;
	public static Image vampire;
	public static Image warlock;
	public static Image witch;
	public static Image wraith;
	public static Image zombie;
	
	public static Image clue;
	public static Image investigatorFrame;
	public static Image clueIndicator;
	public static Image healthIndicator;
	public static Image sanityIndicator;
	public static Image passport;
	public static Image suitCase;
	public static Image leatherBackground;
	
	public static void load(){
		boolean backgroundLoading = !Global.debug;
		assetPicture= new Image[37];
		for(int i = 0; i< assetPicture.length; i++){		
			assetPicture[i]= new Image("images/assets/" + i + ".jpg",402,206, true, false, backgroundLoading);	
		}
		spellPicture= new Image[10];
		for(int i = 0; i< spellPicture.length; i++){		
			spellPicture[i]= new Image("images/spells/" + i + ".jpg",402,206, true, false, backgroundLoading);	
		}
		
		itemBackPicture= new Image("images/assets/ItemBack.png",373,282, true, false, backgroundLoading);	
		assetSymbol= new Image("images/assets/AssetSymbol.png",54,50, true, false,backgroundLoading);		
		spellSymbol= new Image("images/spells/SpellSymbol.png",54,50, true, false, backgroundLoading);		
		clue= new Image("images/clue.png",75,75, true, false, backgroundLoading);		
		
		
		gameBoard= new Image("images/gameBoard/GameBoard.jpg",5804,3594, true, false, backgroundLoading);
		mark= new Image("images/gameBoard/Mark.png",66,58, true, false, backgroundLoading);
		cityMark= new Image("images/gameBoard/CityMark.png",518,154, true, false, backgroundLoading);
		expeditionMarkWilderness= new Image("images/gameBoard/ExpeditionMarkWilderness.png",558,170, true, false, backgroundLoading);
		expeditionMarkSea= new Image("images/gameBoard/ExpeditionMarkSea.png",558,152, true, false, backgroundLoading);
		citySpace= new Image("images/gameBoard/CitySpace.png",61,61, true, false, backgroundLoading);
		wildernessSpace= new Image("images/gameBoard/WildernessSpace.png",61,61, true, false,backgroundLoading);
		seaSpace= new Image("images/gameBoard/SeaSpace.png",61,61, true, false, backgroundLoading);
		gate= new Image("images/gameBoard/Gate.png",100,100, true, false, backgroundLoading);

		cultist= new Image("images/monster/Cultist.jpg",384,263, true, false, backgroundLoading);
		byakee= new Image("images/monster/Byakee.jpg",384,263, true, false, backgroundLoading);
		colorOfSpace= new Image("images/monster/ColorOfSpace.jpg",384,263, true, false, backgroundLoading);
		cthonian= new Image("images/monster/Cthonian.jpg",384,263, true, false, backgroundLoading);
		darkYoung= new Image("images/monster/DarkYoung.jpg",384,263, true, false, backgroundLoading);
		deepOne= new Image("images/monster/DeepOne.jpg",384,263, true, false, backgroundLoading);
		elderThing= new Image("images/monster/ElderThing.jpg",384,263, true, false, backgroundLoading);
		ghost= new Image("images/monster/Ghost.jpg",384,263, true, false, backgroundLoading);
		ghoul= new Image("images/monster/Ghoul.jpg",384,263, true, false, backgroundLoading);
		gnophKeh= new Image("images/monster/GnophKeh.jpg",384,263, true, false, backgroundLoading);
		goatSpawn= new Image("images/monster/GoatSpawn.jpg",384,263, true, false, backgroundLoading);
		gug= new Image("images/monster/Gug.jpg",384,263, true, false, backgroundLoading);
		houndOfTindalos= new Image("images/monster/HoundOfTindalos.jpg",384,263, true, false, backgroundLoading);
		lloigor= new Image("images/monster/Lloigor.jpg",384,263, true, false, backgroundLoading);
		maniac= new Image("images/monster/Maniac.jpg",384,263, true, false, backgroundLoading);
		miGo= new Image("images/monster/MiGo.jpg",384,263, true, false, backgroundLoading);
		mummy= new Image("images/monster/Mummy.jpg",384,263, true, false, backgroundLoading);
		nightgaunt= new Image("images/monster/Nightgaunt.jpg",384,263, true, false, backgroundLoading);
		riot= new Image("images/monster/Riot.jpg",384,263, true, false, backgroundLoading);
		serpentPeople= new Image("images/monster/SerpentPeople.jpg",384,263, true, false, backgroundLoading);
		shoggoth= new Image("images/monster/Shoggoth.jpg",384,263, true, false, backgroundLoading);
		skeleton= new Image("images/monster/Skeleton.jpg",384,263, true, false, backgroundLoading);
		starSpawn= new Image("images/monster/StarSpawn.jpg",384,263, true, false, backgroundLoading);
		vampire= new Image("images/monster/Vampire.jpg",384,263, true, false, backgroundLoading);
		warlock= new Image("images/monster/Warlock.jpg",384,263, true, false, backgroundLoading);
		witch= new Image("images/monster/Witch.jpg",384,263, true, false, backgroundLoading);
		wraith= new Image("images/monster/Wraith.jpg",384,263, true, false, backgroundLoading);
		zombie= new Image("images/monster/Zombie.jpg",384,263, true, false, backgroundLoading);
		investigatorFrame= new Image("images/InvestigatorFrame.png",400,386, true, false, backgroundLoading);
		clueIndicator= new Image("images/gameBoard/ClueIndicator.png",234,234, true, false, backgroundLoading);
		healthIndicator= new Image("images/gameBoard/HealthIndicator.png",234,234, true, false, backgroundLoading);
		sanityIndicator= new Image("images/gameBoard/SanityIndicator.png",234,234, true, false, backgroundLoading);
		passport= new Image("images/gameBoard/Passport.png",200,198, true, false, backgroundLoading);
		suitCase= new Image("images/gameBoard/SuitCase.png",194,122, true, false, backgroundLoading);
		leatherBackground = new Image("images/LeatherBackground.jpg",1024,678, true, false, backgroundLoading);

	}
}
