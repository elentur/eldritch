package model.Item;

import enums.EventTimeType;
import enums.SituationTyp;

public interface Bonus {
    void execute(Object object);
    SituationTyp getSituation();
    EventTimeType getEventTime();
    String getText();
}
