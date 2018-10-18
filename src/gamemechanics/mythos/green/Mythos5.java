package gamemechanics.mythos.green;

import enums.Dificulty;
import enums.ItemType;
import gamemechanics.MythosGreen;
import model.effects.Reckoning;

import java.util.Collections;

public class Mythos5 extends MythosGreen {

    public Mythos5() {
        super(Dificulty.HARD);
        this.getEffects().add(new Reckoning(Collections.singletonList(ItemType.CONDITION), true));
    }

    @Override
    public String getId() {
        return "&mythos0";
    }

    @Override
    public String getNameId() {
        return "${mythos_0}";
    }




}
