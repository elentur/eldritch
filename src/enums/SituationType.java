package enums;

import utils.ResourceUtil;

public enum SituationType {
    NONE(""),
    COMBAT_ENCOUNTER("${combat_encounter}"),
    SPELL_EFFECT("${spell_effect}"),
    ALL("${all}"),
    STANDARD_ENCOUNTER("${standard_encounter}");

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