package gameBuild;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import enums.AssetNames;
import enums.AssetTyps;
import enums.Encounters;
import enums.Skills;
import gameMechanics.BoniValue;
import gameMechanics.DiceBonus;

public class BuildAssets {
private DiceBonus holyWaterBonus;
private DiceBonus spiritDaggerBonus;
	public static int getPrice(AssetNames name){
		Set<Integer> one = new HashSet<Integer>(Arrays.asList(0,1,4,8,9,11,12,13,21,25,27,30));
		Set<Integer> three = new HashSet<Integer>(Arrays.asList(6,10,15,23,31,33));
		Set<Integer> four = new HashSet<Integer>(Arrays.asList(3,14,35));

		int value= name.ordinal();
		if(one.contains(value))return 1;
		if(three.contains(value))return 3;
		if(four.contains(value))return 4;
		return 2;
	}
	public static List<AssetTyps> getTyp(AssetNames name){
		List<AssetTyps> assetTyps = new ArrayList<AssetTyps>();
		Set<Integer> item = new HashSet<Integer>(Arrays.asList(1,2,4,6,7,8,9,10,14,15,16,17,19,20,21,22,30,34));
		Set<Integer> trinket = new HashSet<Integer>(Arrays.asList(0,24,25,27,31));
		Set<Integer> weapon = new HashSet<Integer>(Arrays.asList(0,1,2,7,9,10,14,15,34));
		Set<Integer> tome = new HashSet<Integer>(Arrays.asList(4,6,22));
		Set<Integer> ally = new HashSet<Integer>(Arrays.asList(5,11,18,23,26,29,35,36));
		Set<Integer> service = new HashSet<Integer>(Arrays.asList(3,12,13,28,32,33));
		Set<Integer> teamwork = new HashSet<Integer>(Arrays.asList(13));
		Set<Integer> magical = new HashSet<Integer>(Arrays.asList(20,34));

		int value= name.ordinal();
		if(item.contains(value)) assetTyps.add(AssetTyps.Item);
		if(trinket.contains(value)) assetTyps.add(AssetTyps.Trinket);
		if(weapon.contains(value)) assetTyps.add(AssetTyps.Weapon);
		if(tome.contains(value)) assetTyps.add(AssetTyps.Tome);
		if(ally.contains(value)) assetTyps.add(AssetTyps.Ally);
		if(service.contains(value)) assetTyps.add(AssetTyps.Service);
		if(teamwork.contains(value)) assetTyps.add(AssetTyps.Teamwork);
		if(magical.contains(value)) assetTyps.add(AssetTyps.Magical);
		return assetTyps;
	}
	public static DiceBonus getDiceBonus(AssetNames name) {
		int value= name.ordinal();
		if(value==4)return new DiceBonus(1,Skills.lore,Encounters.resolvingSpell);
		if(value==5)return new DiceBonus(1,Skills.lore,Encounters.resolvingSpell);
		if(value==6)return new DiceBonus(2,Skills.lore,Encounters.resolvingSpell);
		if(value==26)return new DiceBonus(1,Skills.influence,Encounters.none);
		if(value==29)return new DiceBonus(1,Skills.observation,Encounters.none);
		
		if(value==1)return new DiceBonus(2,Skills.strength,Encounters.combatEncounter);
		if(value==2)return new DiceBonus(3,Skills.strength,Encounters.combatEncounter);
		if(value==7)return new DiceBonus(2,Skills.strength,Encounters.combatEncounter);
		if(value==9)return new DiceBonus(1,Skills.strength,Encounters.combatEncounter);
		if(value==10)return new DiceBonus(5,Skills.strength,Encounters.combatEncounter,true);
		if(value==14)return new DiceBonus(4,Skills.strength,Encounters.combatEncounter);
		if(value==18)return new DiceBonus(1,Skills.strength,Encounters.none);
		if(value==20)return new DiceBonus(Encounters.combatEncounter,false,true,
				new BoniValue(5,Skills.strength),new BoniValue(5,Skills.will));
		if(value==21)return new DiceBonus(5,Skills.strength,Encounters.combatEncounter,false,true);
		if(value==34)new DiceBonus(Encounters.combatEncounter,false,true,
				new BoniValue(2,Skills.strength),new BoniValue(1,Skills.will));
		
		if(value==19)return new DiceBonus(2,Skills.will,Encounters.combatEncounter);
		if(value==30)return new DiceBonus(1,Skills.will,Encounters.combatEncounter);
		if(value==36)return new DiceBonus(1,Skills.will,Encounters.none);

		
		return null;
	}

}
