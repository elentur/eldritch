package oldVersion.elements;

import java.util.List;
import java.util.Map;

import oldVersion.enums.AssetNames;
import oldVersion.enums.AssetTyps;
import oldVersion.gameBuild.BuildAssets;
import oldVersion.gameBuild.Global;
import oldVersion.gameMechanics.DiceBonus;
import oldVersion.gameMechanics.IO;
import oldVersion.gui.GameTextures;
import javafx.scene.image.Image;

public class Asset extends Item {
private AssetNames name;
private Map<String,String> names=null;
private List<AssetTyps> assetTyps;
private int price;
private DiceBonus diceBonus;


	private static final long serialVersionUID = 1L;
	protected String attribute ="";
	public Asset(AssetNames name) {
		this.name=name;
		names=IO.readText(Global.language+"/Asset.txt");
		price=BuildAssets.getPrice(name);
		assetTyps=BuildAssets.getTyp(name);
		diceBonus = BuildAssets.getDiceBonus(name);

	}
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	@Override
	public String getName() {
		return names.get("name_" + name.ordinal());
	}
	
	
	@Override
	public Image getPicture() {
		return GameTextures.assetPicture[name.ordinal()];
	}
	
	public Image getPictureBack() {
		return GameTextures.itemBackPicture;
	}
	
	public Image getPictureSymbol() {
		return GameTextures.assetSymbol;
	}
	@Override
	public int getItemNumber() {
		return name.ordinal();
	}
	public int getPrice() {
		return price;
	}
	@Override
	public String getText() {
		 return names.get("text_" + name.ordinal());
	}
	public String getTypText(){
		return names.get("typ_" + name.ordinal());

	}
	public DiceBonus getDiceBonus(){
		return diceBonus;
	}
}
