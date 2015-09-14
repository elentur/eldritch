package gameBuild;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import enums.AssetNames;
import enums.AssetTyps;

public class BuildAssets {

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
}
