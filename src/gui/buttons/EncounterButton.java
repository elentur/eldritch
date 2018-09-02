package gui.buttons;

import Service.GameService;
import gamemechanics.encounter.Encounter;
import gui.Effects;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class EncounterButton extends ItemButton{
    private final Encounter encounter;
    public EncounterButton(Encounter encounter){
        super("encounter",encounter);
        this.encounter =encounter;
        setClickEvent();


        setTooltipText(encounter.getName());

    }



    private void setClickEvent() {
        addEventHandler(MouseEvent.MOUSE_CLICKED,e->{
            if (e.getButton().equals(MouseButton.PRIMARY)) {
                GameService.getInstance().addEncounter(encounter.draw());
            }
        });
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
