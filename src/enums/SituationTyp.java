package enums;

import utils.ResourceUtil;

public enum SituationTyp {
    NONE(""),
    COMBAT_ENCOUNTER("${combat_encounter}"),
    SPELL_EFFECT("${spell_effect}"),
    ALL("${all}");

    private String key;

    private SituationTyp(String key) {
        this.key = key;
    }

    public String getText() {
        return ResourceUtil.get(key,this.getClass());
    }
    }
