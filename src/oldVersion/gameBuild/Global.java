package oldVersion.gameBuild;

import java.util.ArrayList;
import java.util.List;

import oldVersion.gameMechanics.TextAppearsTransition;
import oldVersion.gameMechanics.TokenAppearsTransition;
import oldVersion.gui.Animations;
import javafx.animation.Transition;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

public class Global {
public static String language="";

public static Game game;
public static boolean debug;
public static ScrollPane scrollPane;
public static Group screen;
public static Group overlay;
public static int myPlayerNumber=0;

public static ObservableList<TokenAppearsTransition> tokenAppearsTransitionList;
public static ObservableList<TextAppearsTransition> textAppearsTransitionList;

public static void init(){
	tokenAppearsTransitionList =  FXCollections.observableArrayList();
	tokenAppearsTransitionList.addListener(new ListChangeListener<TokenAppearsTransition>(){	
		@Override
		public void onChanged(javafx.collections.ListChangeListener.Change<? extends TokenAppearsTransition> c) {
		Animations.tokenAppearsTransition(tokenAppearsTransitionList);
		
	}});
	
	textAppearsTransitionList =  FXCollections.observableArrayList();
	textAppearsTransitionList.addListener(new ListChangeListener<TextAppearsTransition>(){	
		@Override
		public void onChanged(javafx.collections.ListChangeListener.Change<? extends TextAppearsTransition> c) {
		Animations.textAppearsTransition(textAppearsTransitionList);
		
	}});
}
//public static Label lbldebug = new Label();

}
