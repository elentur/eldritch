package gui.interfaceelements;

import Service.GameService;
import enums.SpendType;
import enums.TestType;
import gui.*;
import gui.buttons.FieldButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;
import model.Field;
import model.Item.Investigator;

public class ActiveInvestigatorGUI extends Group {
    private final static Image frameImage = new Image("images/interface/InvestigatorFrame.png", 250, 242, true, true, true);

    private final ImageView portrait;
    private final Label name;
    private final TokenImage healthImage;
    private final TokenImage sanityImage;
    private final TokenImage clueImage;
    private final TokenImage focusImage;
    private final TokenImage trainImage;
    private final TokenImage shipImage;

    private final SkillImage loreImage;
    private final SkillImage influenceImage;
    private final SkillImage observationImage;
    private final SkillImage strengthImage;
    private final SkillImage willImage;

    private final UpdateListener listener;
    private Investigator inv;

    public ActiveInvestigatorGUI() {
        ImageView frame = new ImageView(frameImage);
        portrait = new ImageView();
        // frameImage.setClip(frameImage);
        Circle clip = new Circle(100);
        clip.setCenterX(125);
        clip.setCenterY(121);
        portrait.setClip(clip);
        portrait.setOnMouseClicked(e->{
            if(e.getButton().equals(MouseButton.PRIMARY)){
                zoomTo(inv);
            }else{
                InventoryDialog inventoryDialog= new InventoryDialog(inv.getInventory());
                InterfaceLinking.root.getChildren().add( InterfaceLinking.root.getChildren().size() - 1, inventoryDialog);
                inventoryDialog.showAndWait();
            }
        });

        name = new Label();
        name.styleProperty().bind(Fonts.getFont(0.22, Fonts.DARK, Fonts.FontTyp.ITALIC));
        name.setPrefWidth(250);
        name.setPrefHeight(50);
        name.setAlignment(Pos.CENTER);
        name.setTranslateY(180);
        name.setTextAlignment(TextAlignment.CENTER);

        healthImage = new TokenImage(SpendType.HEALTH, 50);
        healthImage.setTranslateX(190);
        healthImage.setTranslateY(130);

        sanityImage = new TokenImage(SpendType.SANITY, 50);
        sanityImage.setTranslateX(200);
        sanityImage.setTranslateY(80);

        clueImage = new TokenImage(SpendType.CLUE, 50);
        clueImage.setTranslateX(10);
        clueImage.setTranslateY(130);

        focusImage = new TokenImage(SpendType.FOCUS, 50);
        focusImage.setTranslateX(0);
        focusImage.setTranslateY(80);

        trainImage = new TokenImage(SpendType.TRAIN_TICKET, 50);
        trainImage.setTranslateX(180);
        trainImage.setTranslateY(40);

        shipImage = new TokenImage(SpendType.SHIP_TICKET, 50);
        shipImage.setTranslateX(160);
        shipImage.setTranslateY(10);

        loreImage = new SkillImage(TestType.LORE,40);
        loreImage.setTranslateX(0);
        loreImage.setTranslateY(-40);

        influenceImage = new SkillImage(TestType.INFLUENCE,40);
        influenceImage.setTranslateX(45);
        influenceImage.setTranslateY(-40);

        observationImage = new SkillImage(TestType.OBSERVATION,40);
        observationImage.setTranslateX(90);
        observationImage.setTranslateY(-40);

        strengthImage = new SkillImage(TestType.STRENGTH,40);
        strengthImage.setTranslateX(135);
        strengthImage.setTranslateY(-40);

        willImage = new SkillImage(TestType.WILL,40);
        willImage.setTranslateX(180);
        willImage.setTranslateY(-40);


        listener = new UpdateListener();
        this.getChildren().addAll(portrait, frame, name, healthImage, sanityImage, clueImage,
                focusImage, trainImage, shipImage,loreImage,influenceImage,observationImage,strengthImage,willImage);
        this.setEffect(Effects.dropShadow);
    }

    public void update() {
        if (inv != GameService.getInstance().getActiveInvestigator()) {
            if (inv != null) {
                inv.getUpdate().removeListener(listener);
            }
            inv = GameService.getInstance().getActiveInvestigator();
            inv.getUpdate().addListener(listener);
            portrait.setImage(new Image("images/investigator/" + inv.getId() + ".jpg", 250, 242, true, true, true));
            name.setText(inv.getName());
            zoomTo(inv);
        }
        if (inv != null) {

            healthImage.setValue(inv.getActualHealth() + "");
            sanityImage.setValue(inv.getActualSanity() + "");
            clueImage.setValue(inv.getClues().size()+"");
            focusImage.setValue(inv.getFocus()+"");
            trainImage.setValue(inv.getTrainTicket()+"");
            shipImage.setValue(inv.getShipTicket()+"");
            loreImage.setValue(inv.getSkillSet());
            influenceImage.setValue(inv.getSkillSet());
            observationImage.setValue(inv.getSkillSet());
            strengthImage.setValue(inv.getSkillSet());
            willImage.setValue(inv.getSkillSet());
        }
    }

    private class UpdateListener implements ChangeListener<Boolean> {
        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if (newValue) {
                update();
                inv.getUpdate().setValue(false);
            }
        }
    }

    private void zoomTo(Investigator inv) {
        Field field = GameService.getInstance().getFieldOfInvestigator(inv);
        FieldButton fieldButton = InterfaceLinking.gameBoardGUI.getFieldButton(field);
        if (fieldButton != null) {
            Animations.zoomTo(fieldButton);
        }

    }
}
