package enums;

import utils.ResourceUtil;

public enum SituationTyp {
    NONE(""),
    COMBAT_ENCOUNTER("${combat_encounter}"),
    SPELL_EFFECT("${spell_effect}"),
    ALL("${all}");

    private String key;

     SituationTyp(String key) {
        this.key = key;
    }

    public String getText() {
        return ResourceUtil.get(key,this.getClass());
    }

    public boolean equalsWithAll(SituationTyp o){
        return o.equals(this) || this.equals(SituationTyp.ALL)|| o.equals(SituationTyp.ALL);
    }
}