package gui.buttons;

import gamemechanics.encounter.Encounter;
import gui.Effects;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;

public class EncounterButton extends ItemButton{
    private final Encounter encounter;
    public EncounterButton(Encounter encounter){
        super(encounter);
        this.encounter =encounter;


        setTooltipText(encounter.getName());

    }





    @Override
    protected void setEventsForButton(Node node) {
        node.setOnMouseEntered(e -> actualNode.setEffect(Effects.hover));
        node.setOnMouseExited(e -> {
            actualNode.setEffect(null);
            this.setEffect(Effects.dropShadow);
        });
        node.setOnMousePressed(e -> {
            if (e.getButton().equals(MouseButton.PRIMARY)) {
                this.setEffect(null);
            }
        });
        node.setOnMouseReleased(e -> {
            if (e.getButton().equals(MouseButton.PRIMARY)) {
                this.setEffect(Effects.dropShadow);
            }
        });
    }
}
