package gui.buttons;

import enums.SpendType;
import enums.TestType;
import gui.interfaceelements.TokenImage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import model.Item.Investigator;

public class InvestigatorButton extends ItemButton {
    private final Investigator inv;

    private final TokenImage healthImage;
    private final TokenImage sanityImage;
    private final UpdateListener listener;

    public InvestigatorButton(Investigator inv) {
        super("investigator", inv);
        this.inv = inv;

        this.setShowBackSide(false);
        healthImage = new TokenImage(SpendType.HEALTH, 50);
        healthImage.setTranslateX(100);
        healthImage.setTranslateY(50);
        healthImage.setMouseTransparent(true);

        sanityImage = new TokenImage(SpendType.SANITY, 50);
        sanityImage.setTranslateX(100);
        sanityImage.setTranslateY(0);
        sanityImage.setMouseTransparent(true);
        this.getChildren().addAll(healthImage, sanityImage);
        listener = new UpdateListener();
        inv.getUpdate().addListener(listener);
        update();
    }


    public void clearListener() {
        inv.getUpdate().removeListener(listener);
    }

    private void update() {
        healthImage.setValue(inv.getActualHealth() + "");
        sanityImage.setValue(inv.getActualSanity() + "");
        setTooltipText(
                inv.getName() + "\n" +
                        TestType.LORE.getSymbol() + inv.getSkill(TestType.LORE) + ", " +
                        TestType.INFLUENCE.getSymbol() + inv.getSkill(TestType.INFLUENCE) + ", " +
                        TestType.OBSERVATION.getSymbol() + inv.getSkill(TestType.OBSERVATION) + ", " +
                        TestType.STRENGTH.getSymbol() + inv.getSkill(TestType.STRENGTH) + ", " +
                        TestType.WILL.getSymbol() + inv.getSkill(TestType.WILL) );
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
}
