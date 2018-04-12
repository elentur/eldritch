package oldVersion.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import oldVersion.elements.Investigator;
import oldVersion.enums.Skills;
import oldVersion.gameBuild.Global;
import oldVersion.gameItems.Dice;
import oldVersion.gameItems.Skill;
import oldVersion.gameMechanics.IO;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class DiceGui extends Group {
	private GraphicButton rollDice;
	private ScrollBar scrollbar;
	private Dice dice;
	private ObservableList<Integer> diceList;
	private List<Node> diceNodesList;
	private Scene scene;
	private Skills skill;
	private Investigator investigator;
	private Map<String,String> names=IO.readText(Global.language+"/GameScreen.txt");
	private boolean close;
	private boolean rolled;
	
	
	public DiceGui(Skills skill,Investigator investigator){
		scene = StageControll.getPrimaryStage().getScene();
		dice= new Dice(skill,investigator);
		diceList=FXCollections.observableArrayList();
		this.skill=skill;
		this.close =false;
		this.investigator=investigator;
		diceNodesList = new ArrayList<Node>();
		rollDice= new GraphicButton(names.get("rollDice") + Skill.getSkillAsSymbol(skill) + dice.getNumber());
		scrollbar=new ScrollBar();
		scrollbar.widthProperty().bind(scene.widthProperty().divide(2.5));
		scrollbar.heightProperty().bind(scene.widthProperty().divide(13.5));
		rollDice.setOnMouseClicked(a->rollDice());
		this.getChildren().add(rollDice);
		
	}
	
	private void rollDice() {
		if(!close){
			rolled= true;
			this.getChildren().remove(rollDice);
			this.getChildren().add(scrollbar);
			
			diceList.addAll(dice.roll());
			
			for(Integer d : diceList){
				Label lblDice = new Label(d.toString());
				lblDice.setOnMouseClicked(a->selectDie(a));
				lblDice.styleProperty().bind(Effects.fontMedium);
				lblDice.setTextFill(Effects.fontColorDark);
				lblDice.setAlignment(Pos.TOP_CENTER);
				lblDice.setBackground(new Background(new BackgroundImage(MenueTextures.roundButton, BackgroundRepeat.NO_REPEAT,  BackgroundRepeat.NO_REPEAT,
						new BackgroundPosition(null, 0, true, Side.TOP, 0, true), 
						new  BackgroundSize(50, 50, false, false, false, false))));
				lblDice.minWidthProperty().bind(scrollbar.heightProperty().divide(2));
				lblDice.minHeightProperty().bind(scrollbar.heightProperty().divide(2));
				
				diceNodesList.add(lblDice);
			}
			scrollbar.setContent(diceNodesList.toArray(new Node[diceNodesList.size()]));
		}
	}

	private void selectDie(MouseEvent a) {
		if(!close){
			int i = diceNodesList.indexOf((Label)a.getSource());
			int result =dice.reroll(i);
					diceList.set(i, result);
			((Label)a.getSource()).setText(result+"");
		}
		
	}

	public GraphicButton getButton(){
		return rollDice;
	}
	public IntegerProperty succsessProperty(){
		return dice.successProperty();
	}
	public void setClose(){
		close=true;
	}

	public boolean isRolled() {
		return rolled;
	}
}
