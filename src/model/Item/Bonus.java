package model.Item;

import enums.*;
import gamemechanics.encounter.Encounter;

public interface Bonus {
    void execute(Encounter encounter);
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
