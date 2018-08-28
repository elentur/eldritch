package gui.buttons;

import model.Item.Investigator;
import model.Item.Item;

public class InvestigatorButton extends ItemButton {
    private final Investigator inv;
    public InvestigatorButton(Investigator inv) {
        super("investigator", inv);
        this.inv = inv;
        setTooltipText(inv.getName());
    }
}
