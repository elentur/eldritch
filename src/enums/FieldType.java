package enums;

import utils.ResourceUtil;

public enum FieldType {

    NONE(""),
    CITY("${city}"),
    WILDNESS("${wildness}"),
    SEA("${sea}"),
    OTHER_WORLD("${other_world}");


    private String key;

    private FieldType(String key) {
        this.key = key;
    }

    public String getText() {
        return ResourceUtil.get(key,this.getClass());
    }

}
