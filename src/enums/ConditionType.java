package enums;

import utils.ResourceUtil;

public enum ConditionType {
    BLESSED("${blessed}"),
    CURSED("${cursed}"),
    DETAINED("${detained}"),
    DEPT("${dept}"),
    NONE(""),
    AMNESIA("${amnesia}"),
    INTERNAL_INJURY("${internal_injury}");

    private String key;

    private ConditionType(String key) {
        this.key = key;
    }
    public String getText() {
        return ResourceUtil.get(key,this.getClass());
    }
}
