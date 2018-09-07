package gui.buttons;

import gui.Effects;
import gui.Fonts;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;
import model.Item.*;


public class InventoryItemButton extends ItemButton {
    private final static Image labelImage = new Image("images/GraphicButton.png", 198, 48, true, true);
    private final static Image priceImage = new Image("images/RoundButton.png", 50, 50, true, true);
    private final static Image assetImage = new Image("images/effect/Asset.png", 50, 50, true, true);
    private final static Image artifactImage = new Image("images/effect/Artifact.png", 50, 50, true, true);
    private final static Image conditionImage = new Image("images/effect/Condition.png", 50, 50, true, true);
    private final static Image spellImage = new Image("images/effect/Spell.png", 50, 50, true, true);


    private final Label name;
    private final Label backSideText;
    public InventoryItemButton(Item item) {
        super(item);
        ImageView nameImage = new ImageView(labelImage);
        nameImage.setTranslateY(-48);
        name = new Label(item.getName());
        name.styleProperty().bind(Fonts.getFont(0.17,Fonts.DARK, Fonts.FontTyp.ITALIC));
        name.setPrefWidth(labelImage.getWidth());
        name.setPrefHeight(labelImage.getHeight());
        name.setWrapText(true);
        name.setTextAlignment(TextAlignment.CENTER);
        name.setAlignment(Pos.CENTER);
        name.setTranslateY(nameImage.getTranslateY());

        actualNode.getChildren().addAll(nameImage,name);

        backSideText = new Label();
        backSideText.styleProperty().bind(Fonts.getFont(0.12,Fonts.DARK, Fonts.FontTyp.NORMAL));
        backSideText.setPrefWidth(backgroundImage.getWidth()-10);
        backSideText.setPrefHeight(backgroundImage.getHeight());
        backSideText.setTranslateX(5);
        backSideText.setWrapText(true);
        backSideText.setTextAlignment(TextAlignment.CENTER);
        backSideText.setAlignment(Pos.CENTER);

        oldNode.getChildren().addAll(backSideText);
        switch (item.getItemType()) {
            case ARTIFACT:
                buildForArtifact();
                break;
            case ASSET:
                buildForAsset();
                break;
            case SPELL:
                buildForSpell();
                break;
            case CONDITION:
                buildForCondition();
                break;
            default:
                break;
        }


    }

    private void buildForCondition() {
        ImageView imgView = new ImageView(conditionImage);
        imgView.setTranslateX(145);
        imgView.setEffect(Effects.innerShadow);
        actualNode.getChildren().add(imgView);
        backSideText.setText(((Condition)item).getInfoText());
    }

    private void buildForSpell() {
        ImageView imgView = new ImageView(spellImage);
        imgView.setTranslateX(145);
        imgView.setEffect(Effects.innerShadow);
        actualNode.getChildren().add(imgView);
        backSideText.setText(((Spell)item).getInfoText());
    }

    private void buildForAsset() {
        ImageView imgView = new ImageView(assetImage);
        imgView.setTranslateX(145);
        imgView.setEffect(Effects.innerShadow);
        actualNode.getChildren().add(imgView);

        ImageView priceImg = new ImageView(priceImage);
        priceImg.setTranslateY(-10);
        priceImg.setTranslateX(3);
        priceImg.setEffect(Effects.innerShadow);

        Label priceLabel = new Label(((Asset)item).getPrice()+"");
        priceLabel.setPrefWidth(priceImage.getWidth());
        priceLabel.setPrefHeight(priceImage.getHeight());
        priceLabel.setAlignment(Pos.CENTER);
        priceLabel.setTranslateX(priceImg.getTranslateX());
        priceLabel.setTranslateY(priceImg.getTranslateY());
        priceLabel.setTextAlignment(TextAlignment.CENTER);
        priceLabel.styleProperty().bind(Fonts.getFont(0.25,Fonts.DARK, Fonts.FontTyp.BOLD));
        actualNode.getChildren().addAll(priceImg,priceLabel);

        backSideText.setText(((Asset)item).getInfoText());
    }

    private void buildForArtifact() {
        ImageView imgView = new ImageView(artifactImage);
        imgView.setTranslateX(145);
        imgView.setEffect(Effects.innerShadow);
        actualNode.getChildren().add(imgView);
        backSideText.setText(((Artifact)item).getInfoText());
    }
}
