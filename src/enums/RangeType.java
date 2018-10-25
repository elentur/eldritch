package enums;

import utils.ResourceUtil;

public enum RangeType {

    NONE(""),
    SELF("${self}"),
    LOCAL("${local}"),
    ALL("${all}");


    private String key;

    private RangeType(String key) {
        this.key = key;
    }

    public String getText() {
        return ResourceUtil.get(key,this.getClass());
    }

}
