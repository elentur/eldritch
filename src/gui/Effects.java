package gui;

import java.awt.Toolkit;

import gameItems.AncientOne;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.effect.Shadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Effects {
	//public static Font font = Font.loadFont("file:src/fonts/UGLYQUA.TTF",  Toolkit.getDefaultToolkit().getScreenSize().getWidth() /50);
	
	public static StringProperty fontVerySmall = new SimpleStringProperty();//Font.loadFont("file:src/fonts/UGLYQUA.TTF",  Toolkit.getDefaultToolkit().getScreenSize().getWidth() /200);
	public static StringProperty fontVerySmallCursive =new SimpleStringProperty();
	
	public static StringProperty fontSmall = new SimpleStringProperty();//Font.loadFont("file:src/fonts/UGLYQUA.TTF",  Toolkit.getDefaultToolkit().getScreenSize().getWidth() /140);
	public static StringProperty fontSmallCursive = new SimpleStringProperty();//Font.loadFont("file:src/fonts/UGLYQUAITALIC.TTF",  Toolkit.getDefaultToolkit().getScreenSize().getWidth() /140);
	public static StringProperty fontMedium = new SimpleStringProperty();//Font.loadFont("file:src/fonts/UGLYQUA.TTF",  Toolkit.getDefaultToolkit().getScreenSize().getWidth() /80);
	public static StringProperty fontMedium2 =new SimpleStringProperty();// Font.loadFont("file:src/fonts/UGLYQUA.TTF",  Toolkit.getDefaultToolkit().getScreenSize().getWidth() /120);
    public static StringProperty fontBig = new SimpleStringProperty();
    public static StringProperty fontGraphicButton = new SimpleStringProperty();// Font.loadFont("file:src/fonts/UGLYQUA.TTF",  Toolkit.getDefaultToolkit().getScreenSize().getWidth() /110);
	
    public static DoubleProperty fontSizeVerySmall = new SimpleDoubleProperty(10);
    public static DoubleProperty fontSizeSmall = new SimpleDoubleProperty(10);
    public static DoubleProperty fontSizeMedium = new SimpleDoubleProperty(10);
    public static DoubleProperty fontSizeMedium2 = new SimpleDoubleProperty(10);
    public static DoubleProperty fontSizeBig = new SimpleDoubleProperty(10);
    public static DoubleProperty fontSizeGraphicButton = new SimpleDoubleProperty(10);
 
    public static Color fontColorDark = Color.DARKSLATEGREY;
	public static Color fontColorRed = Color.rgb(205, 40, 43);
	public static Color fontColorBlue = Color.rgb(88, 120, 200);
	public static Color fontColorGreen = Color.rgb(0, 170, 80);
	public static Pos fontPosLeft = Pos.TOP_LEFT;
	public static Pos fontPosRight = Pos.TOP_RIGHT;
	
	public static BoxBlur blure = new BoxBlur(5,5,3);
	public static DropShadow shadowBtn = new DropShadow(BlurType.ONE_PASS_BOX, Color.color(0, 0, 0, 0.8),10,0.1, 5, 5);
	public static Glow highlightBtn = new Glow(1);
	public static Glow highlightSpaces = new Glow(0.5);
	public static DropShadow textShadow = new DropShadow(BlurType.ONE_PASS_BOX, Color.color(0, 0, 0, 0.8),3,0.1, 1, 1);
	
	public static Light.Point pointLight = new Light.Point(100, 100, 100, Color.BISQUE);
	public static Lighting highlightPicture = new Lighting();
	public static Color fontColor = Color.ANTIQUEWHITE;
	public static Pos fontPos = Pos.TOP_CENTER;
	
	
	public static void init(){
		Font.loadFont("file:src/resources/fonts/UGLYQUAITALIC.TTF",  Toolkit.getDefaultToolkit().getScreenSize().getWidth() /200);
		Font.loadFont("file:src/resources/fonts/UGLYQUA.TTF",  Toolkit.getDefaultToolkit().getScreenSize().getWidth() /200);

		fontSizeBig.bind( StageControll.getPrimaryStage().getScene().widthProperty().divide(30));
		fontBig.bind(Bindings.concat("-fx-font:" , Effects.fontSizeBig.asString(), " 'UglyQua' ;"));
		
		fontSizeVerySmall.bind( StageControll.getPrimaryStage().getScene().widthProperty().divide(140));
		fontVerySmall.bind(Bindings.concat("-fx-font: ", Effects.fontSizeVerySmall.asString(), " 'UglyQua' ;"));

		fontVerySmallCursive.bind(Bindings.concat("-fx-font: ", Effects.fontSizeVerySmall.asString()," 'UglyQua-Italic' ;"));

		fontSizeSmall.bind( StageControll.getPrimaryStage().getScene().widthProperty().divide(100));
		fontSmall.bind(Bindings.concat("-fx-font: ", Effects.fontSizeSmall.asString()," 'UglyQua' ;"));
		fontSmallCursive.bind(Bindings.concat("-fx-font: ", Effects.fontSizeSmall.asString()," 'UglyQua-Italic' ;"));

		fontSizeMedium.bind( StageControll.getPrimaryStage().getScene().widthProperty().divide(50));
		fontMedium.bind(Bindings.concat("-fx-font: ", Effects.fontSizeMedium.asString()," 'UglyQua' ;"));

		fontSizeMedium2.bind( StageControll.getPrimaryStage().getScene().widthProperty().divide(90));
		fontMedium2.bind(Bindings.concat("-fx-font: ", Effects.fontSizeMedium2.asString()," 'UglyQua' ;"));

		fontSizeGraphicButton.bind( StageControll.getPrimaryStage().getScene().widthProperty().divide(75));
		fontGraphicButton.bind(Bindings.concat("-fx-font: ", Effects.fontSizeGraphicButton.asString()," 'UglyQua' ;"));

	}
	public static void highlight(MouseEvent lbl){
		((Node)lbl.getSource()).setEffect(Effects.highlightBtn);
	}
	public static void highlightOff(MouseEvent lbl){
		((Node)lbl.getSource()).setEffect(null);
	}
	
	public static void highlightSpaces(MouseEvent lbl){
		((Node)lbl.getSource()).setEffect(Effects.highlightSpaces);
	}
	
	public static void highlight(Node lbl){
		lbl.setEffect(Effects.highlightBtn);
	}
	public static void highlightOff(Node lbl){
		lbl.setEffect(null);
	}
	
	public static void highlightPct(MouseEvent lbl){
		highlightPicture.setLight(pointLight);
		highlightPicture.setSurfaceScale(2);
		((Node)lbl.getSource()).setEffect(Effects.highlightPicture);
	}
	public static void highlightPctOff(MouseEvent lbl,AncientOne ancientOne, String name){
		if(!(ancientOne!=null && ancientOne.getName()==name))((Node)lbl.getSource()).setEffect(null);
	}
	public static void highlightPct(Node lbl){
		highlightPicture.setLight(pointLight);
		highlightPicture.setSurfaceScale(2);
		lbl.setEffect(Effects.highlightPicture);
	}
	public static void highlightPctOff(Node lbl){
		lbl.setEffect(null);
	}

}
