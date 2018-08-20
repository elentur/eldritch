package enums;

import utils.ResourceUtil;

public enum EffectSelector {
    THIS("${this}"),
    ADDITIONAL("${additional}"),
    RANDOM("${random}"), ANY("${any}");

    private String key;

    EffectSelector(String key) {
        this.key = key;
    }

    public String getText() {
        return ResourceUtil.get(key,this.getClass());
    }
}
