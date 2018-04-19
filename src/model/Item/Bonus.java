package model.Item;

import enums.EventTimeType;
import enums.SituationTyp;
import enums.TestTyp;

public interface Bonus {
    void execute(Object object);
    SituationTyp getSituation();
    EventTimeType getEventTime();
    TestTyp getTest();
    String getText();
}
