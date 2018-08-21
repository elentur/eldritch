package enums;

import utils.ResourceUtil;

public enum FieldType {

    NONE(""),
    CITY("${city}"),
    WILDERNESS("${wilderness}"),
    SEA("${sea}"),
    OTHER_WORLD("${other_world}"),
    ALL("${all}");


    private String key;

    FieldType(String key) {
        this.key = key;
    }

    public String getText() {
        return ResourceUtil.get(key,this.getClass());
    }

    public boolean equalsWithAll(FieldType o){
        return o.equals(this) || this.equals(FieldType.ALL)|| this.equals(FieldType.ALL);
    }
}
