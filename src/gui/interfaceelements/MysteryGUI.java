package gui.interfaceelements;

import Service.GameService;
import gamemechanics.Mystery;
import gamemechanics.choice.InformationChoice;
import gui.Effects;
import gui.Fonts;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.text.TextAlignment;
import lombok.Getter;
import utils.ResourceUtil;

import java.util.Random;


public class MysteryGUI extends Group {

    private final static Image clipShape = new Image("images/ItemBack.png", 260, 165, false, true, true);
    private final static Image eldritch = new Image("images/gameBoard/Eldritch.png", 40, 40, true, true, true);
    private Random random;

    @Getter
    private final UpdateListener listener;

    private Group tokens;
    private ImageView mystery;
    private   Label numberOfTokens;

    public MysteryGUI() {
        random= new Random();
        mystery = new ImageView();
        String oldeOne = GameService.getInstance().getAncientOne().getNameId().replaceAll("[{}\\$]", "");
        mystery.setImage(new Image("images/encounter/&mystery_" + oldeOne + ".png", 260, 165, false, true, true));
        mystery.setClip(new ImageView(clipShape));

        mystery.setOnMouseClicked(e -> {
            if (e.getButton().equals(MouseButton.PRIMARY)) {
                Mystery mystery = GameService.getInstance().getActiveMystery();
                InformationChoice choice = new InformationChoice(ResourceUtil.get("${mystery}", "ui"),
                        mystery.getName() + "\n" + mystery.getText(), null);
                GameService.getInstance().addChoice(choice);
            }
        });
        tokens =new Group();

        numberOfTokens= new Label();
        numberOfTokens.styleProperty().bind(Fonts.getFont(0.22, Fonts.WHITE, Fonts.FontTyp.BOLD));
        numberOfTokens.setPrefWidth(50);
        numberOfTokens.setPrefHeight(50);
        numberOfTokens.setAlignment(Pos.CENTER);
        numberOfTokens.setTranslateX(105);
        numberOfTokens.setTranslateY(100);
        numberOfTokens.setTextAlignment(TextAlignment.CENTER);
        numberOfTokens.setVisible(false);
        this.getChildren().addAll(mystery,tokens,numberOfTokens);
        this.setEffect(Effects.dropShadow);
        listener = new UpdateListener();
    }

    public void update() {
        Mystery mystery = GameService.getInstance().getActiveMystery();
        if (mystery.getTokens().size() > tokens.getChildren().size()) {
             ImageView eldritchToken = new ImageView(eldritch);
             tokens.getChildren().add(eldritchToken);
             double x =random.nextDouble()*220;
             double y = random.nextDouble()*125;
            eldritchToken.setX(x);
            eldritchToken.setY(y);
            numberOfTokens.setText(tokens.getChildren().size()+"");
            numberOfTokens.setVisible(true);
        }else{
            tokens.getChildren().clear();
            numberOfTokens.setVisible(false);
        }
        GameService.getInstance().getActiveMystery().getUpdate().setValue(false);
    }

    private class UpdateListener implements ChangeListener<Boolean> {
        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if (newValue) {
                update();

            }
        }
    }
}
