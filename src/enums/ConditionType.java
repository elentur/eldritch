package enums;

import utils.ResourceUtil;

public enum ConditionType {
    BLESSED("${blessed}"),
    CURSED("${cursed}"),
    DETAINED("${detained}"),
    DEPT("${dept}"),
    NONE(""),
    AMNESIA("${amnesia}"),
    INTERNAL_INJURY("${internal_injury}"),
    INJURY("${injury}"),
    HALLUCINATIONS_CONDITION("${hallucinations_condition}"),
    PARANOIA_CONDITION("${paranoia_condition}");

    private String key;

    private ConditionType(String key) {
        this.key = key;
    }
    public String getText() {
        return ResourceUtil.get(key,this.getClass());
    }
}
