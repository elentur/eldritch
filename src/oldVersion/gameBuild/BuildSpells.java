package oldVersion.gameBuild;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import oldVersion.enums.AssetNames;
import oldVersion.enums.AssetTyps;
import oldVersion.enums.SpellNames;
import oldVersion.enums.SpellTyps;

public class BuildSpells {


	public static List<SpellTyps> getTyp(SpellNames name){
		List<SpellTyps> spellTyps = new ArrayList<SpellTyps>();
		Set<Integer> ritual = new HashSet<Integer>(Arrays.asList(0,2,3,7,8));
		Set<Integer> incantation = new HashSet<Integer>(Arrays.asList(1,4,5,6,9));
		
		int value= name.ordinal();
		if(ritual.contains(value)) spellTyps.add(SpellTyps.Ritual);
		if(incantation.contains(value)) spellTyps.add(SpellTyps.Incantation);
		return spellTyps;
	}
}
