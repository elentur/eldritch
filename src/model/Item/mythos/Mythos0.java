package model.Item.mythos;

import enums.*;
import model.Item.Item;
import model.Item.MythosGreen;
import model.effects.Reckoning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Function;

public class Mythos0 extends MythosGreen {

    public Mythos0() {
        super(Dificulty.EASY);
        getEffects().add(new Reckoning(Collections.singletonList(ItemType.CONDITION), true));
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
