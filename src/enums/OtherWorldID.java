package enums;

import utils.ResourceUtil;

public enum OtherWorldID {

    YUGGOTH("${yuggoth}");



    private String key;

    OtherWorldID(String key) {
        this.key = key;
    }

    public String getText() {
        return ResourceUtil.get(key,this.getClass());
    }
}
