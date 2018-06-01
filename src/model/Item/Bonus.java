package model.Item;

import enums.*;
import gamemechanics.Encounter;
import model.Item.Item;

public interface Bonus {
    void execute(Encounter encounter);
    void reckoning();
    BonusType getBonusType();
    SituationType getSituation();
    EventTimeType getEventTime();
    TestType getTest();
    String getText();
    FieldType getField();
    boolean isActivated();
    boolean isPassive();
    boolean isUsable();

    /**
     * Means if true execute deactivates this bonus for this round
     * @return
     */
    boolean isPerRound();
    Item getParentItem();
    String getParentName();

}
