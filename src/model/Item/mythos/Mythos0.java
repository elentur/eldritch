package model.Item.mythos;

import enums.Dificulty;
import enums.ItemType;
import model.Item.MythosGreen;
import model.effects.Reckoning;

import java.util.Collections;

public class Mythos0 extends MythosGreen {

    public Mythos0() {
        super(Dificulty.EASY);
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
