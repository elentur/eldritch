package oldVersion.gui;

import oldVersion.gameBuild.Global;
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
	public static Image shipTicket;
	public static Image trainTicket;
	public static Image backgroundPattern;
	
	public static void load(){
		boolean backgroundLoading = !Global.debug;
		assetPicture= new Image[37];
		for(int i = 0; i< assetPicture.length; i++){		
			assetPicture[i]= new Image("oldVersion/images/assets/" + i + ".jpg",402,206, true, false, backgroundLoading);
		}
		spellPicture= new Image[10];
		for(int i = 0; i< spellPicture.length; i++){		
			spellPicture[i]= new Image("oldVersion/images/spells/" + i + ".jpg",402,206, true, false, backgroundLoading);
		}
		
		itemBackPicture= new Image("oldVersion/images/assets/ItemBack.png",373,282, true, false, backgroundLoading);
		assetSymbol= new Image("oldVersion/images/assets/AssetSymbol.png",54,50, true, false,backgroundLoading);
		spellSymbol= new Image("oldVersion/images/spells/SpellSymbol.png",54,50, true, false, backgroundLoading);
		clue= new Image("oldVersion/images/clue.png",75,75, true, false, backgroundLoading);
		
		
		gameBoard= new Image("oldVersion/images/gameBoard/gameboard.jpg",5804,3594, true, false, backgroundLoading);
		mark= new Image("oldVersion/images/gameBoard/Mark.png",66,58, true, false, backgroundLoading);
		cityMark= new Image("oldVersion/images/gameBoard/CityMark.png",518,154, true, false, backgroundLoading);
		expeditionMarkWilderness= new Image("oldVersion/images/gameBoard/ExpeditionMarkWilderness.png",558,170, true, false, backgroundLoading);
		expeditionMarkSea= new Image("oldVersion/images/gameBoard/ExpeditionMarkSea.png",558,152, true, false, backgroundLoading);
		citySpace= new Image("oldVersion/images/gameBoard/CitySpace.png",61,61, true, false, backgroundLoading);
		wildernessSpace= new Image("oldVersion/images/gameBoard/WildernessSpace.png",61,61, true, false,backgroundLoading);
		seaSpace= new Image("oldVersion/images/gameBoard/SeaSpace.png",61,61, true, false, backgroundLoading);
		gate= new Image("oldVersion/images/gameBoard/Gate.png",100,100, true, false, backgroundLoading);

		cultist= new Image("oldVersion/images/monster/cultist.jpg",384,263, true, false, backgroundLoading);
		byakee= new Image("oldVersion/images/monster/Byakee.jpg",384,263, true, false, backgroundLoading);
		colorOfSpace= new Image("oldVersion/images/monster/ColorOfSpace.jpg",384,263, true, false, backgroundLoading);
		cthonian= new Image("oldVersion/images/monster/Cthonian.jpg",384,263, true, false, backgroundLoading);
		darkYoung= new Image("oldVersion/images/monster/DarkYoung.jpg",384,263, true, false, backgroundLoading);
		deepOne= new Image("oldVersion/images/monster/DeepOne.jpg",384,263, true, false, backgroundLoading);
		elderThing= new Image("oldVersion/images/monster/ElderThing.jpg",384,263, true, false, backgroundLoading);
		ghost= new Image("oldVersion/images/monster/Ghost.jpg",384,263, true, false, backgroundLoading);
		ghoul= new Image("oldVersion/images/monster/Ghoul.jpg",384,263, true, false, backgroundLoading);
		gnophKeh= new Image("oldVersion/images/monster/GnophKeh.jpg",384,263, true, false, backgroundLoading);
		goatSpawn= new Image("oldVersion/images/monster/GoatSpawn.jpg",384,263, true, false, backgroundLoading);
		gug= new Image("oldVersion/images/monster/Gug.jpg",384,263, true, false, backgroundLoading);
		houndOfTindalos= new Image("oldVersion/images/monster/HoundOfTindalos.jpg",384,263, true, false, backgroundLoading);
		lloigor= new Image("oldVersion/images/monster/Lloigor.jpg",384,263, true, false, backgroundLoading);
		maniac= new Image("oldVersion/images/monster/Maniac.jpg",384,263, true, false, backgroundLoading);
		miGo= new Image("oldVersion/images/monster/MiGo.jpg",384,263, true, false, backgroundLoading);
		mummy= new Image("oldVersion/images/monster/Mummy.jpg",384,263, true, false, backgroundLoading);
		nightgaunt= new Image("oldVersion/images/monster/Nightgaunt.jpg",384,263, true, false, backgroundLoading);
		riot= new Image("oldVersion/images/monster/Riot.jpg",384,263, true, false, backgroundLoading);
		serpentPeople= new Image("oldVersion/images/monster/SerpentPeople.jpg",384,263, true, false, backgroundLoading);
		shoggoth= new Image("oldVersion/images/monster/Shoggoth.jpg",384,263, true, false, backgroundLoading);
		skeleton= new Image("oldVersion/images/monster/Skeleton.jpg",384,263, true, false, backgroundLoading);
		starSpawn= new Image("oldVersion/images/monster/StarSpawn.jpg",384,263, true, false, backgroundLoading);
		vampire= new Image("oldVersion/images/monster/Vampire.jpg",384,263, true, false, backgroundLoading);
		warlock= new Image("oldVersion/images/monster/Warlock.jpg",384,263, true, false, backgroundLoading);
		witch= new Image("oldVersion/images/monster/Witch.jpg",384,263, true, false, backgroundLoading);
		wraith= new Image("oldVersion/images/monster/Wraith.jpg",384,263, true, false, backgroundLoading);
		zombie= new Image("oldVersion/images/monster/Zombie.jpg",384,263, true, false, backgroundLoading);
		investigatorFrame= new Image("oldVersion/images/InvestigatorFrame.png",400,386, true, false, backgroundLoading);
		clueIndicator= new Image("oldVersion/images/gameBoard/ClueIndicator.png",234,234, true, false, backgroundLoading);
		healthIndicator= new Image("oldVersion/images/gameBoard/HealthIndicator.png",234,234, true, false, backgroundLoading);
		sanityIndicator= new Image("oldVersion/images/gameBoard/SanityIndicator.png",234,234, true, false, backgroundLoading);
		passport= new Image("oldVersion/images/gameBoard/Passport.png",200,198, true, false, backgroundLoading);
		suitCase= new Image("oldVersion/images/gameBoard/SuitCase.png",194,122, true, false, backgroundLoading);
		leatherBackground = new Image("oldVersion/images/LeatherBackground.jpg",1024,678, true, false, backgroundLoading);
		shipTicket =	new Image("oldVersion/images/gameBoard/ShipTicket.jpg",98,49, true, false, backgroundLoading);
		trainTicket	 = new Image("oldVersion/images/gameBoard/TrainTicket.jpg",98,49, true, false, backgroundLoading);
		backgroundPattern = new Image("oldVersion/images/gameBoard/BackgroundPattern.jpg",500,500, true, false, backgroundLoading);

	}
}
