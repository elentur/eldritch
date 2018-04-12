package oldVersion.gameBuild;

import java.awt.Point;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oldVersion.elements.AmericaEncounter;
import oldVersion.elements.Artifact;
import oldVersion.elements.AsiaEncounter;
import oldVersion.elements.Asset;
import oldVersion.elements.ClueToken;
import oldVersion.elements.Condition;
import oldVersion.elements.DefaultEncounter;
import oldVersion.elements.EuropeEncounter;
import oldVersion.elements.Expedition;
import oldVersion.elements.Gate;
import oldVersion.elements.Investigator;
import oldVersion.elements.Monster;
import oldVersion.elements.Mystery;
import oldVersion.elements.OtherWorld;
import oldVersion.elements.Research;
import oldVersion.elements.Special;
import oldVersion.elements.Spell;
import oldVersion.enums.AssetNames;
import oldVersion.enums.FieldTyps;
import oldVersion.enums.MonsterNames;
import oldVersion.enums.Path;
import oldVersion.enums.Space;
import oldVersion.enums.SpellNames;
import oldVersion.gameItems.AncientOne;
import oldVersion.gameItems.Field;
import oldVersion.gameItems.GameBoard;
import oldVersion.gameItems.Stack;
import oldVersion.gameMechanics.IO;

import static oldVersion.enums.FieldTyps.*;

public class Build {
	
	public static AncientOne buildAncientOne(String name){
		return new AncientOne(name);
	}
	
	public static GameBoard buildGameBoard(){
		Map<Field,Space> fields= new HashMap<Field,Space>();
		Field field1=new Field("1",Space.city,new Point(172,303),Other);
		Field field2=new Field("2",Space.sea,new Point(93,637),Other);
		Field field3=new Field("3",Space.sea,new Point(313,1368),Other);
		Field field4=new Field("4",Space.wilderness,new Point(460,296),Other);
		Field field5=new Field("5",Space.city,new Point(524,447),Other);
		Field field6=new Field("6",Space.city,new Point(438,620),Other);
		Field field7=new Field("7",Space.city,new Point(582,844),Other);
		Field field8=new Field("8",Space.sea,new Point(767,676),Other);
		Field field9=new Field("9",Space.wilderness,new Point(997,171),Other);
		Field field10=new Field("10",Space.wilderness,new Point(1229,908),Other);
		Field field11=new Field("11",Space.sea,new Point(1276,1372),Other);
		Field field12=new Field("12",Space.sea,new Point(1137,1568),Other);
		Field field13=new Field("13",Space.sea,new Point(1689,112),Other);
		Field field14=new Field("14",Space.city,new Point(1598,354),Other);
		Field field15=new Field("15",Space.city,new Point(1564,1403),Other);
		Field field16=new Field("16",Space.city,new Point(1962,374),Other);
		Field field17=new Field("17",Space.city,new Point(2060,879),Other);
		Field field18=new Field("18",Space.sea,new Point(2005,1482),Other);
		Field field19=new Field("19",Space.wilderness,new Point(2679,373),Other);
		Field field20=new Field("20",Space.city,new Point(2465,1102),Other);
		Field field21=new Field("21",Space.wilderness,new Point(2563,1266),Other);
		
		

		Field fieldArkham=new Field("arkham",Space.city,new Point(650,520),City);
		Field fieldSanFrancisco=new Field("sanFrancisco",Space.city,new Point(153,499),City);
		Field fieldBuenosAires=new Field("buenosAires",Space.city,new Point(640,1330),City);
		Field fieldLondon=new Field("london",Space.city,new Point(1146,407),City);
		Field fieldRom=new Field("rom",Space.city,new Point(1316,642),City);
		Field fieldIstanbul=new Field("istanbul",Space.city,new Point(1626,551),City);
		Field fieldShanghai=new Field("shanghai",Space.city,new Point(2329,845),City);
		Field fieldSydney=new Field("sydney",Space.city,new Point(2517,1487),City);
		Field fieldTokyo=new Field("tokyo",Space.city,new Point(2573,670),City);
		
		Field fieldPyramids=new Field("pyramids",Space.wilderness,new Point(1520,873),Expedition);
		Field fieldHimalaya=new Field("himalayas",Space.wilderness,new Point(1993,703),Expedition);
		Field fieldAmazon=new Field("amazon",Space.wilderness,new Point(651,1031),Expedition);
		Field fieldHeartOfAfrica=new Field("heartOfAfrica",Space.wilderness,new Point(1472,1164),Expedition);
		Field fieldTunguska=new Field("tunguska",Space.wilderness,new Point(2050,408),Expedition);
		Field fieldAntarctica=new Field("antarctica",Space.sea,new Point(1575,1683),Expedition);
		
		field1.addNeighbour(field19, Path.ship);
		field1.addNeighbour(fieldSanFrancisco, Path.ship);
		field1.addNeighbour(field4, Path.uncharted);
		field2.addNeighbour(fieldSanFrancisco, Path.ship);
		field2.addNeighbour(fieldTokyo, Path.ship);
		field3.addNeighbour(fieldBuenosAires, Path.ship);
		field3.addNeighbour(fieldSydney, Path.ship);
		field4.addNeighbour(field1, Path.uncharted);
		field4.addNeighbour(field5, Path.uncharted);
		field5.addNeighbour(fieldSanFrancisco, Path.train);
		field5.addNeighbour(fieldArkham, Path.train);
		field6.addNeighbour(fieldSanFrancisco, Path.train);
		field6.addNeighbour(fieldArkham, Path.train);
		field7.addNeighbour(field6, Path.train);
		field7.addNeighbour(field8, Path.ship);
		field7.addNeighbour(fieldSanFrancisco, Path.ship);
		field7.addNeighbour(fieldBuenosAires, Path.ship);
		field7.addNeighbour(fieldAmazon, Path.uncharted);
		field8.addNeighbour(fieldBuenosAires, Path.ship);
		field8.addNeighbour(fieldArkham, Path.ship);
		field8.addNeighbour(field7, Path.ship);
		field8.addNeighbour(field10, Path.ship);
		field9.addNeighbour(fieldArkham, Path.ship);
		field10.addNeighbour(fieldRom, Path.ship);
		field10.addNeighbour(field8, Path.ship);
		field10.addNeighbour(field15, Path.ship);
		field10.addNeighbour(fieldPyramids, Path.uncharted);
		field11.addNeighbour(fieldBuenosAires, Path.ship);
		field11.addNeighbour(field15, Path.ship);
		field12.addNeighbour(fieldBuenosAires, Path.ship);
		field12.addNeighbour(fieldAntarctica, Path.ship);
		field13.addNeighbour(fieldLondon, Path.ship);
		field14.addNeighbour(fieldRom, Path.train);
		field14.addNeighbour(field16, Path.train);
		field15.addNeighbour(field10, Path.ship);
		field15.addNeighbour(field11, Path.ship);
		field15.addNeighbour(field17, Path.ship);
		field15.addNeighbour(field18, Path.ship);
		field15.addNeighbour(fieldHeartOfAfrica, Path.uncharted);
		field16.addNeighbour(field14, Path.train);
		field16.addNeighbour(fieldTunguska, Path.train);
		field16.addNeighbour(fieldIstanbul, Path.train);
		field17.addNeighbour(fieldIstanbul, Path.train);
		field17.addNeighbour(fieldShanghai, Path.train);
		field17.addNeighbour(field20, Path.ship);
		field17.addNeighbour(field15, Path.ship);
		field17.addNeighbour(fieldHimalaya, Path.uncharted);
		field18.addNeighbour(field15, Path.ship);
		field18.addNeighbour(fieldSydney, Path.ship);
		field19.addNeighbour(field1, Path.ship);
		field19.addNeighbour(fieldTokyo, Path.ship);
		field19.addNeighbour(fieldTunguska, Path.train);
		field19.addNeighbour(fieldShanghai, Path.train);
		field20.addNeighbour(fieldShanghai, Path.ship);
		field20.addNeighbour(fieldTokyo, Path.ship);
		field20.addNeighbour(fieldSydney, Path.ship);
		field20.addNeighbour(field17, Path.ship);
		field21.addNeighbour(fieldSydney, Path.uncharted);
		
		fieldSanFrancisco.addNeighbour(field1, Path.ship);
		fieldSanFrancisco.addNeighbour(field2, Path.ship);
		fieldSanFrancisco.addNeighbour(field7, Path.ship);
		fieldSanFrancisco.addNeighbour(field5, Path.train);
		fieldSanFrancisco.addNeighbour(field6, Path.train);
		fieldArkham.addNeighbour(field9, Path.ship);
		fieldArkham.addNeighbour(field8, Path.ship);
		fieldArkham.addNeighbour(fieldLondon, Path.ship);
		fieldArkham.addNeighbour(field5, Path.train);
		fieldArkham.addNeighbour(field6, Path.train);
		fieldBuenosAires.addNeighbour(field3, Path.ship);
		fieldBuenosAires.addNeighbour(field7, Path.ship);
		fieldBuenosAires.addNeighbour(fieldAmazon, Path.uncharted);
		fieldBuenosAires.addNeighbour(field8, Path.ship);
		fieldBuenosAires.addNeighbour(field11, Path.ship);
		fieldBuenosAires.addNeighbour(field12, Path.ship);
		fieldLondon.addNeighbour(field13, Path.ship);
		fieldLondon.addNeighbour(fieldArkham, Path.ship);
		fieldLondon.addNeighbour(fieldRom, Path.ship);
		fieldRom.addNeighbour(field10, Path.ship);
		fieldRom.addNeighbour(fieldLondon, Path.ship);
		fieldRom.addNeighbour(fieldPyramids, Path.ship);
		fieldRom.addNeighbour(field14, Path.train);
		fieldRom.addNeighbour(fieldIstanbul, Path.train);
		fieldIstanbul.addNeighbour(field16, Path.train);
		fieldIstanbul.addNeighbour(fieldRom, Path.train);
		fieldIstanbul.addNeighbour(field17, Path.train);
		fieldIstanbul.addNeighbour(fieldPyramids, Path.train);
		fieldShanghai.addNeighbour(field17, Path.train);
		fieldShanghai.addNeighbour(field19, Path.train);
		fieldShanghai.addNeighbour(field20, Path.ship);
		fieldShanghai.addNeighbour(fieldHimalaya, Path.uncharted);
		fieldShanghai.addNeighbour(fieldTokyo, Path.ship);
		fieldTokyo.addNeighbour(field19, Path.ship);
		fieldTokyo.addNeighbour(field2, Path.ship);
		fieldTokyo.addNeighbour(field20, Path.ship);
		fieldTokyo.addNeighbour(fieldShanghai, Path.train);
		fieldSydney.addNeighbour(field3, Path.ship);
		fieldSydney.addNeighbour(field18, Path.ship);
		fieldSydney.addNeighbour(field20, Path.ship);
		fieldSydney.addNeighbour(fieldAntarctica, Path.ship);
		fieldSydney.addNeighbour(field21, Path.uncharted);
		
		fieldAmazon.addNeighbour(field7, Path.uncharted);
		fieldAmazon.addNeighbour(fieldBuenosAires, Path.uncharted);
		fieldPyramids.addNeighbour(field10, Path.uncharted);
		fieldPyramids.addNeighbour(fieldHeartOfAfrica, Path.uncharted);
		fieldPyramids.addNeighbour(fieldRom, Path.ship);
		fieldHeartOfAfrica.addNeighbour(fieldPyramids, Path.uncharted);
		fieldHeartOfAfrica.addNeighbour(field15, Path.uncharted);
		fieldHimalaya.addNeighbour(field17, Path.uncharted);
		fieldHimalaya.addNeighbour(fieldShanghai, Path.uncharted);
		fieldAntarctica.addNeighbour(field12, Path.ship);
		fieldAntarctica.addNeighbour(fieldSydney, Path.ship);
		fieldTunguska.addNeighbour(field16, Path.train);
		fieldTunguska.addNeighbour(field19, Path.train);
		
		fields.put(field1, field1.getSpace());
		fields.put(field2, field2.getSpace());
		fields.put(field3, field3.getSpace());
		fields.put(field4, field4.getSpace());
		fields.put(field5, field5.getSpace());
		fields.put(field6, field6.getSpace());
		fields.put(field7, field7.getSpace());
		fields.put(field8, field8.getSpace());
		fields.put(field9, field9.getSpace());
		fields.put(field10, field10.getSpace());
		fields.put(field11, field11.getSpace());
		fields.put(field12, field12.getSpace());
		fields.put(field13, field13.getSpace());
		fields.put(field14, field14.getSpace());
		fields.put(field15, field15.getSpace());
		fields.put(field16, field16.getSpace());
		fields.put(field17, field17.getSpace());
		fields.put(field18, field18.getSpace());
		fields.put(field19, field19.getSpace());
		fields.put(field20, field20.getSpace());
		fields.put(field21, field21.getSpace());
		
		fields.put(fieldArkham, fieldArkham.getSpace());
		fields.put(fieldSanFrancisco, fieldSanFrancisco.getSpace());
		fields.put(fieldBuenosAires, fieldBuenosAires.getSpace());
		fields.put(fieldLondon, fieldLondon.getSpace());
		fields.put(fieldRom, fieldRom.getSpace());
		fields.put(fieldIstanbul, fieldIstanbul.getSpace());
		fields.put(fieldShanghai, fieldShanghai.getSpace());
		fields.put(fieldTokyo, fieldTokyo.getSpace());
		fields.put(fieldSydney, fieldSydney.getSpace());
		
		fields.put(fieldAmazon, fieldAmazon.getSpace());
		fields.put(fieldPyramids, fieldPyramids.getSpace());
		fields.put(fieldHeartOfAfrica, fieldHeartOfAfrica.getSpace());
		fields.put(fieldAntarctica, fieldAntarctica.getSpace());
		fields.put(fieldTunguska, fieldTunguska.getSpace());
		fields.put(fieldHimalaya, fieldHimalaya.getSpace());
		
		GameBoard gameBoard = new GameBoard(fields);
		return gameBoard;
	}

	public static Stack<Spell> buildSpellDeck() {
		Stack<Spell> spellDeck = new Stack<Spell>(true);
		//For Testing, not finally implementation
		for (int i = 0 ; i<10; i++){
			spellDeck.add(new Spell(SpellNames.values()[i],0));
			spellDeck.add(new Spell(SpellNames.values()[i],1));
		}

		spellDeck.shuffle();	
		return spellDeck;
	}

	public static Stack<Asset> buildAssetDeck() {
		Stack<Asset> assetDeck = new Stack<Asset>(true);
		Asset asset=null;
		for(int i = 0; i <36;i++){
			asset = new Asset(AssetNames.values()[i]);
			//System.out.println(i + ": " + asset);
			assetDeck.add(asset);
		}
		
		assetDeck.shuffle();	
		return assetDeck;
	}

	public static Stack<Artifact> buildArtifactDeck() {
		// TODO Auto-generated method stub
		return null;
	}

	public static Stack<Condition> buildConditionDeck() {
		// TODO Auto-generated method stub
		return null;
	}

	public static Stack<ClueToken> buildCluePool(GameBoard gameboard) {
		Stack<ClueToken> clues = new Stack<ClueToken>(true);
		for(Field field:gameboard.getFields().keySet()){
			clues.add(new ClueToken(field));
		}
		clues.shuffle();
		return clues;
	}

	public static Stack<Gate> buildGateStack(GameBoard gameboard) {
		Stack<Gate> gates = new Stack<Gate>(true);
		for(Field field:gameboard.getFields().keySet()){
			if(field.getFieldTyp()==FieldTyps.City ||
					(field.getFieldTyp()==FieldTyps.Expedition &&(field.toString()=="pyramids" ||
					field.toString()=="amazon"||
					field.toString()=="tunguska"))){
				//Monster noch adden
				gates.add(new Gate(field));
			}
		}
		gates.shuffle();
		return gates;
	}

	public static Stack<Monster> buildMonsterPool(AncientOne ancient){
		Stack<Monster> monsterPool = new Stack<Monster>(false);
		for (int i = 1 ; i<28; i++){
			monsterPool.add(new Monster(MonsterNames.values()[i]));
		}
		
		monsterPool.add(new Monster(MonsterNames.ghoul));
		monsterPool.add(new Monster(MonsterNames.goatSpawn));
		for (int i = 0 ; i<5; i++){
			monsterPool.add(new Monster(ancient.getCultist1()));
		}
		monsterPool.shuffle();
		return monsterPool;
	}

	public static Stack<Mystery> buildMysteryDeck(AncientOne ancientOne) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Stack<Research> buildResearchDeck(AncientOne ancientOne) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Stack<Special> buildSpecialDeck(AncientOne ancientOne) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Stack<Expedition> buildExpeditionDeck() {
		// TODO Auto-generated method stub
		return null;
	}

	public static Stack<OtherWorld> buildOtherWorldDeck() {
		// TODO Auto-generated method stub
		return null;
	}

	public static Stack<AmericaEncounter> buildAmeriacEncounterDeck() {
		// TODO Auto-generated method stub
		return null;
	}

	public static Stack<EuropeEncounter> buildEuropeEncounterDeck() {
		// TODO Auto-generated method stub
		return null;
	}

	public static Stack<AsiaEncounter> buildAsiaEncounterDeck() {
		// TODO Auto-generated method stub
		return null;
	}

	public static Stack<DefaultEncounter> buildDefaultEncounterDeck() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

