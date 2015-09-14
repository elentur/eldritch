package elements;

import java.util.List;
import java.util.Map;

import enums.AssetNames;
import enums.AssetTyps;
import enums.SpellNames;
import enums.SpellTyps;
import gameBuild.BuildAssets;
import gameBuild.BuildSpells;
import gameBuild.Global;
import gameMechanics.IO;
import gui.GameTextures;
import javafx.scene.image.Image;

public class Spell extends Item {
	private SpellNames name;
	private Map<String,String> names=null;
	private List<SpellTyps> spellTyps;

		private static final long serialVersionUID = 1L;
		
		
		public Spell(SpellNames name, int back) {
			this.name=name;
			names=IO.readText(Global.language+"/Spell.txt");
			spellTyps=BuildSpells.getTyp(name);
		}


		@Override
		public String getName() {
			return names.get("name_" + name.ordinal());
		}
		
	@Override
	public Image getPicture() {
		return GameTextures.spellPicture[name.ordinal()];
	}

	@Override
	public Image getPictureBack() {
		return GameTextures.itemBackPicture;
	}

	@Override
	public Image getPictureSymbol() {
		return GameTextures.spellSymbol;
	}
	@Override
	public int getItemNumber() {
		return name.ordinal();
	}

	@Override
	public String getText() {
		 return names.get("text_" + name.ordinal());
	}

	@Override
	public String getTypText() {
		return names.get("typ_" + name.ordinal());

	}

}
