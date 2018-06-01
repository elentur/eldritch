package enums;

import utils.ResourceUtil;

public enum SpendType {

    NONE(""),
    SANITY("${sanity}"),
    HEALTH("${health}"),
    CLUE("${clue}");


    private String key;

    SpendType(String key) {
        this.key = key;
    }

    public String getText() {
        return ResourceUtil.get(key,this.getClass());
    }

    public boolean equalsWithAll(SpendType o){
        return o.equals(this);
    }
}
