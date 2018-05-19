package gui;

import gamemechanics.Encounter;

public class EncounterGui extends DialogGui {
    private final Encounter encounter;
    public EncounterGui(Encounter encounter) {
        super("", 0.7,0.7 );
        this.encounter=encounter;

    }
}
