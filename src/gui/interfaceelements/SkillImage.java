package gui.interfaceelements;

import enums.TestType;
import gui.Fonts;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;
import model.SkillSet;
import utils.ResourceUtil;


public class SkillImage extends Group {

    private final Label valueLabel;
    private final Tooltip tooltip;
    private final ImageView imageView;
    private final TestType testType;

    public SkillImage(TestType testType, double size) {
        this.testType = testType;
        imageView = new ImageView("images/interface/" + testType.getKey().replaceAll("[{}\\$]", "") + ".png");
        imageView.setFitWidth(size);
        imageView.setFitHeight(size * 1.25);
        valueLabel = new Label("6");
        valueLabel.setPrefWidth(size * 0.5);
        valueLabel.setPrefHeight(size * 0.625);
        valueLabel.setTranslateX(size / 2.5);
        valueLabel.setTranslateY((size * 1.25) / 2);
        valueLabel.setAlignment(Pos.CENTER);
        valueLabel.setTextAlignment(TextAlignment.CENTER);
        valueLabel.styleProperty().bind(Fonts.getFont(0.15, Fonts.WHITE, Fonts.FontTyp.BOLD));
        this.getChildren().addAll(imageView, valueLabel);

        // valueLabel.setBorder(new Border(new BorderStroke(Fonts.RED, BorderStrokeStyle.SOLID, new CornerRadii(10.0), BorderStroke.MEDIUM)));
        tooltip = new Tooltip();
        tooltip.getStyleClass().add("tooltip");
        tooltip.styleProperty().bind(Fonts.getFont(0.17, Fonts.DARK, Fonts.FontTyp.NORMAL));
        tooltip.setTextAlignment(TextAlignment.CENTER);
        tooltip.setWrapText(true);
        tooltip.setMaxWidth(200);

    }

    protected void setTooltipText(String text) {
        Tooltip.install(imageView, tooltip);
        tooltip.setText(text);

    }

    public void setValue(SkillSet skillSet) {
        valueLabel.setText(skillSet.getSkill(testType) + "");
        StringBuilder text = new StringBuilder(testType.getText() + "\n");
        switch (testType) {
            case LORE:
                text.append(skillSet.getLore()+"\n");
                text.append(ResourceUtil.get("${improved}","ui")+": " + skillSet.getLoreMod());
                break;
            case INFLUENCE:
                text.append(skillSet.getInfluence()+"\n");
                text.append(ResourceUtil.get("${improved}","ui")+": " + skillSet.getInfluenceMod());
                break;
            case OBSERVATION:
                text.append(skillSet.getObservation()+"\n");
                text.append(ResourceUtil.get("${improved}","ui")+": " + skillSet.getObservationMod());
                break;
            case STRENGTH:
                text.append(skillSet.getStrength()+"\n");
                text.append(ResourceUtil.get("${improved}","ui")+": " + skillSet.getStrengthMod());
                break;
            case WILL:
                text.append(skillSet.getWill()+"\n");
                text.append(ResourceUtil.get("${improved}","ui")+": " + skillSet.getWillMod());
                break;
            default:
                break;
        }

        setTooltipText(text.toString());
    }
}
