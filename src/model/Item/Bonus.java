package model.Item;

import enums.EventTimeType;
import enums.SituationTyp;

public interface Bonus {
    void execute();
    SituationTyp getSituation();
    EventTimeType getEventTime();
    String getText();
}
