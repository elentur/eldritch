package gui;

import java.awt.Toolkit;

import gameItems.AncientOne;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Effects {
	public static Font font = Font.loadFont("file:src/fonts/UGLYQUA.TTF",  Toolkit.getDefaultToolkit().getScreenSize().getWidth() /50);
	public static Font fontVerySmall = Font.loadFont("file:src/fonts/UGLYQUA.TTF",  Toolkit.getDefaultToolkit().getScreenSize().getWidth() /200);
	public static Font fontVerySmallCursive = Font.loadFont("file:src/fonts/UGLYQUAITALIC.TTF",  Toolkit.getDefaultToolkit().getScreenSize().getWidth() /200);
	
	public static Font fontSmall = Font.loadFont("file:src/fonts/UGLYQUA.TTF",  Toolkit.getDefaultToolkit().getScreenSize().getWidth() /140);
	public static Font fontSmallCursive = Font.loadFont("file:src/fonts/UGLYQUAITALIC.TTF",  Toolkit.getDefaultToolkit().getScreenSize().getWidth() /140);
	public static Font fontMedium = Font.loadFont("file:src/fonts/UGLYQUA.TTF",  Toolkit.getDefaultToolkit().getScreenSize().getWidth() /80);
	public static Font fontMedium2 = Font.loadFont("file:src/fonts/UGLYQUA.TTF",  Toolkit.getDefaultToolkit().getScreenSize().getWidth() /120);

	public static Font fontGraphicButton = Font.loadFont("file:src/fonts/UGLYQUA.TTF",  Toolkit.getDefaultToolkit().getScreenSize().getWidth() /110);
	
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
	
	public static Light.Point pointLight = new Light.Point(100, 100, 100, Color.BISQUE);
	public static Lighting highlightPicture = new Lighting();
	public static Color fontColor = Color.ANTIQUEWHITE;
	public static Pos fontPos = Pos.TOP_CENTER;
	
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
