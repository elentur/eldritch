package model.Item;

import enums.EventTimeType;
import enums.FieldType;
import enums.SituationTyp;
import enums.TestTyp;
import gamemechanics.Encounter;

public interface Bonus {
    void execute(Encounter encounter);
    SituationTyp getSituation();
    EventTimeType getEventTime();
    TestTyp getTest();
    String getText();
    FieldType getField();
    boolean isActive();
    Item getParentItem();
    String getParentName();
}
