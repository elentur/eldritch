package model.Item.ancientOnes;

import enums.OldOnes;
import gamemechanics.encounter.Encounter;
import model.Item.AncientOne;

public class Azathoth extends AncientOne {

    public Azathoth() {
        super(OldOnes.AZATHOTH);
        minNumberOfSolvedMysteries=2;
    }

    @Override
    public Encounter getEncounter() {
        return null;
    }

    @Override
    public String getNameId() {
        return null;
    }

    @Override
    public String getId() {
        return null;
    }
}
