package enums;

import lombok.Getter;
import utils.ResourceUtil;

public enum SpendType {

    NONE(""),
    SANITY("${sanity}"),
    HEALTH("${health}"),
    CLUE("${clue}"),
    TRAIN_TICKET("${train_ticket}"),
    SHIP_TICKET("${ship_ticket}"),
    FOCUS("${focus}");


    @Getter
    private String key;

    SpendType(String key) {
        this.key = key;
    }

    public String getText() {
        return ResourceUtil.get(key,this.getClass());
    }

    public boolean equalsWithAll(SpendType o){
        return o.equals(this);
    }
}
