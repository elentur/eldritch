package enums;

import utils.ResourceUtil;

public enum SituationType {
    NONE(""),
    COMBAT_ENCOUNTER("${combat_encounter}"),
    SPELL_EFFECT("${spell_effect}"),
    ALL("${all}"),
    STANDARD_ENCOUNTER("${standard_encounter}"),
    ACTION("${action}"),
    ACQUIRE_ASSETS("${acquire_assets}"),
    BUY_TRAIN_TICKET("${buy_train_ticket}"),
    BUY_SHIP_TICKET("${buy_ship_ticket}"),
    REST("${rest}"),
    GAIN_FOCUS("${gain_focus}"),
    TRADE("${trade}"),
    MOVE("${move}"),
    TEST("${test}"),
    OTHER_WORLD_ENCOUNTER("${other_world_encounter}");

    private String key;

     SituationType(String key) {
        this.key = key;
    }

    public String getText() {
        return ResourceUtil.get(key,this.getClass());
    }

    public boolean equalsWithAll(SituationType o){
        return o.equals(this) || this.equals(SituationType.ALL)|| o.equals(SituationType.ALL);
    }
}