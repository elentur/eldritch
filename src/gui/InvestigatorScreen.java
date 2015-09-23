package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import elements.Card;
import elements.Investigator;
import elements.Item;
import enums.Screens;
import exceptions.CardNotFoundException;
import gameBuild.Global;
import gameMechanics.IO;
import investigators.MarkHarrigan;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.TextAlignment;

import static gameBuild.Global.*;

public class InvestigatorScreen {
	private static Group root;
	private static boolean build = true;
	private static Button btnBack;
	private static Button btnAccept;
	private static Rectangle imgBackground;
	private static Group investigatorRoot;
	private static Scene screen;
	private static AncientOneInterface aOInterface;
	private static ScrollBar scrollBar;
	private static InvestigatorSheet investigatorSheet;
	private static VBox selectedInvestigators;

	private static Investigator akachiOnyele;
	private static Investigator charlieKane;
	private static Investigator dianaStanley;
	private static Investigator jacquelineFine;
	private static Investigator jimCulver;
	private static Investigator leoAnderson;
	private static Investigator lilyChen;
	private static Investigator lolaHayes;
	private static Investigator markHarrigan;
	private static Investigator normanWithers;
	private static Investigator silasMarsh;
	private static Investigator trishScarborough;
	private static List<Investigator> selectedInvestigator;
	private static VBox startEquipment;
	private static CheckBox checkBox;

	public static void setScreen(Group root1, Scene scene) {
		screen = scene;
		root = root1;
		if (build) {
			buildButtons();
			buildOther();
		}
		if (selectedInvestigator.isEmpty()) {
			btnAccept.setDisable(true);

		}
		scrollBar.deselectAll();
		investigatorSheet.setVisible(false);
		

		investigatorRoot = new Group(imgBackground, btnBack, btnAccept, aOInterface, scrollBar, investigatorSheet,
				selectedInvestigators, startEquipment, checkBox);
		root.getChildren().add(0, investigatorRoot);
		Animations.blendingUp();

	}

	private static void buildOther() {
		selectedInvestigator = new ArrayList<Investigator>();
		selectedInvestigators = new VBox(screen.getHeight() / (-50));
		selectedInvestigators.translateXProperty().bind(screen.widthProperty().divide(1.2));
		selectedInvestigators.translateYProperty().bind(screen.heightProperty().divide(3.5));
		investigatorSheet = new InvestigatorSheet(false);
		investigatorSheet.translateXProperty().bind(investigatorSheet.widthProperty().divide(1.8));
		investigatorSheet.translateYProperty().bind(screen.heightProperty().divide(4));

		akachiOnyele = new Investigator("AkachiOnyele");
		akachiOnyele.getFlatToken().setOnMouseClicked(a -> selectInvestigator(a, akachiOnyele));
		charlieKane = new Investigator("CharlieKane");
		charlieKane.getFlatToken().setOnMouseClicked(a -> selectInvestigator(a, charlieKane));
		dianaStanley = new Investigator("DianaStanley");
		dianaStanley.getFlatToken().setOnMouseClicked(a -> selectInvestigator(a, dianaStanley));
		jacquelineFine = new Investigator("JacquelineFine");
		jacquelineFine.getFlatToken().setOnMouseClicked(a -> selectInvestigator(a, jacquelineFine));
		jimCulver = new Investigator("JimCulver");
		jimCulver.getFlatToken().setOnMouseClicked(a -> selectInvestigator(a, jimCulver));
		lilyChen = new Investigator("LilyChen");
		lilyChen.getFlatToken().setOnMouseClicked(a -> selectInvestigator(a, lilyChen));
		lolaHayes = new Investigator("LolaHayes");
		lolaHayes.getFlatToken().setOnMouseClicked(a -> selectInvestigator(a, lolaHayes));
		leoAnderson = new Investigator("LeoAnderson");
		leoAnderson.getFlatToken().setOnMouseClicked(a -> selectInvestigator(a, leoAnderson));
		markHarrigan = new Investigator("MarkHarrigan");
		markHarrigan.getFlatToken().setOnMouseClicked(a -> selectInvestigator(a, markHarrigan));
		normanWithers = new Investigator("NormanWithers");
		normanWithers.getFlatToken().setOnMouseClicked(a -> selectInvestigator(a, normanWithers));
		silasMarsh = new Investigator("SilasMarsh");
		silasMarsh.getFlatToken().setOnMouseClicked(a -> selectInvestigator(a, silasMarsh));
		trishScarborough = new Investigator("TrishScarborough");
		trishScarborough.getFlatToken().setOnMouseClicked(a -> selectInvestigator(a, trishScarborough));

		scrollBar = new ScrollBar();
		scrollBar.translateXProperty().bind(screen.widthProperty().divide(4));
		scrollBar.translateYProperty().bind(screen.widthProperty().divide(50));
		scrollBar.setContent(akachiOnyele.getFlatToken(), charlieKane.getFlatToken(), dianaStanley.getFlatToken(),
				jacquelineFine.getFlatToken(), jimCulver.getFlatToken(), leoAnderson.getFlatToken(),
				lilyChen.getFlatToken(), lolaHayes.getFlatToken(), markHarrigan.getFlatToken(),
				normanWithers.getFlatToken(), silasMarsh.getFlatToken(), trishScarborough.getFlatToken());

		imgBackground = new Rectangle();
		imgBackground.widthProperty().bind(screen.widthProperty());
		imgBackground.heightProperty().bind(screen.heightProperty());
		imgBackground.setFill(new ImagePattern(MenueTextures.background));

		aOInterface = new AncientOneInterface(game.getAncientOne());
		startEquipment = new VBox(20);
		startEquipment.translateXProperty().bind(screen.widthProperty().divide(1.5));
		startEquipment.translateYProperty().bind(screen.heightProperty().divide(4));
		startEquipment.setAlignment(Effects.fontPos);
		checkBox = new CheckBox();
		Map<String, String> names = IO.readText(Global.language + "/Menu.txt");
		checkBox.setText(names.get("multiplayer"));
		checkBox.translateXProperty().bind(screen.widthProperty().divide(20));
		checkBox.translateYProperty().bind(screen.heightProperty().divide(3.5));
	}

	private static void selectInvestigator(MouseEvent a, Investigator investigator) {

		// deselect all Investiagtors
		scrollBar.deselectAll();
		if (a.getButton() == MouseButton.PRIMARY) {
			// Select this
			Rectangle rec = ((Rectangle) a.getSource());
			rec.setStroke(Color.GREEN);
			investigatorSheet.setInvestigator(investigator);
			startEquipment.getChildren().clear();
//			for (Item item : investigator.getInventory().getStack()) {
//				startEquipment.getChildren().add(new ItemGraphic(item));
//			}
			startEquipment.getChildren().addAll(investigator.getInventory().getNodes());
			if (!investigator.getClues().isEmpty()) {
				try {
					startEquipment.getChildren().add(investigator.getClues().showNextCard().getToken());
				} catch (CardNotFoundException e) {
				}
			}
			if (investigator.getName() == "Lola Hayes") {
				Map<String, String> names = IO.readText(Global.language + "/LolaHayes.txt");
				Label lblText = new Label(names.get("start"));
				lblText.setAlignment(Effects.fontPos);
				lblText.setTextFill(Effects.fontColorDark);
				lblText.styleProperty().bind(Effects.fontMedium2);
				lblText.minWidthProperty().bind(screen.widthProperty().divide(5.14).divide(1.45));
				lblText.minHeightProperty().bind(screen.widthProperty().divide(6.81).divide(1.45));
				lblText.maxWidthProperty().bind(screen.widthProperty().divide(5.14).divide(1.45));
				lblText.maxHeightProperty().bind(screen.widthProperty().divide(6.81).divide(1.45));

				lblText.setBackground(new Background(new BackgroundImage(GameTextures.itemBackPicture, null, null, null,
						new BackgroundSize(0, 0, false, false, true, false))));

				lblText.setPadding(new Insets(10));
				lblText.setTextAlignment(TextAlignment.CENTER);
				lblText.maxWidthProperty().bind(screen.widthProperty().divide(8));
				lblText.setWrapText(true);
				startEquipment.getChildren().add(lblText);
			}
			if (!investigatorSheet.isVisible()) {
				investigatorSheet.setVisible(true);
				Animations.startFadeIn(investigatorSheet);

			}
		} else if (a.getButton() == MouseButton.SECONDARY) {
			if (selectedInvestigator.size() < 8) {
				scrollBar.removeContent((Rectangle) a.getSource());
				selectedInvestigator.add(investigator);
				investigatorSheet.setVisible(false);
				startEquipment.getChildren().clear();
				GraphicButton gb = new GraphicButton(investigator.getName());
				gb.setGraphic(investigator.getFlatToken());
				gb.setOnMouseClicked(b -> reselectInvestigator(b));
				selectedInvestigators.getChildren().add(gb);
				btnAccept.setDisable(selectedInvestigator.isEmpty());
			}
		}

	}

	private static void reselectInvestigator(MouseEvent a) {
		int i = selectedInvestigators.getChildren().indexOf(a.getSource());
		if (i > -1) {
			selectedInvestigators.getChildren().remove(i);

			Investigator investigator = selectedInvestigator.remove(i);
			investigator.getFlatToken().setOnMouseClicked(b -> selectInvestigator(b, investigator));
			scrollBar.addContent(investigator.getFlatToken());

			// nicht engültig Abhängig davon ob ich persönlich mindestens einen
			// ermitler in der liste habe
			btnAccept.setDisable(selectedInvestigator.isEmpty());
		}
	}

	private static void buildButtons() {
		build = false;

		Map<String, String> names = IO.readText(Global.language + "/Menu.txt");

		btnBack = new Button(names.get("back"));
		btnBack.translateXProperty().bind(btnBack.widthProperty().divide(2));
		btnBack.translateYProperty().bind(screen.heightProperty().subtract(btnBack.heightProperty().multiply(1.2)));
		btnBack.setOnMouseClicked(a -> {
			build = true;
			Animations.blendingDown(Screens.AncientOneScreen);
			;
		});

		btnAccept = new Button(names.get("accept"));
		btnAccept.translateXProperty().bind(screen.widthProperty().subtract(btnAccept.widthProperty().multiply(1.5)));
		btnAccept.translateYProperty().bind(screen.heightProperty().subtract(btnBack.heightProperty().multiply(1.2)));
		btnAccept.setOnMouseClicked(a -> {
			Global.game.setInvestigators(selectedInvestigator);
			Animations.blendingDown(Screens.GameScreen);
			;
		});

	}

}
