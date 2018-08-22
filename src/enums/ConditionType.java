package enums;

import utils.ResourceUtil;

import java.util.List;

public enum ConditionType {
    BLESSED("${blessed}"),
    CURSED("${cursed}"),
    DETAINED("${detained}"),
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
