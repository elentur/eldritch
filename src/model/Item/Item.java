package model.Item;


import container.ItemStack;
import enums.ItemType;
import gamemechanics.encounter.Encounter;
import model.Effect;

import java.util.List;

public interface Item {
    String getNameId();

    String getName();

    String getId();

    String getUniqueId();

    List<ItemBonus> createBonus();

    List<ItemBonus> getBonus();

    List<Effect> getDrawEffects(Investigator investigator);

    Encounter getEncounter();

    ItemType getSubType();

    ItemType getItemType();
    void executeReckoning(Investigator inv, boolean autoFail);


    void setStack(ItemStack itemStack);
    void discard();
    Item  draw();
}
