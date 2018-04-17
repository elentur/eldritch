package model.Item;

import enums.EventTimeType;
import enums.SituationTyp;

public interface ItemBonus {

    void execute();
    SituationTyp getSituation();
    EventTimeType getEventTime();

}
