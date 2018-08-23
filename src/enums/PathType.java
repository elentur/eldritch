package enums;

import utils.ResourceUtil;

public enum PathType {
    SHIP("${ship}"),
    TRAIN("${train}"),
    UNCHARTED("${uncharted}");

    private String key;

    private PathType(String key) {
        this.key = key;
    }
    public String getText() {
        return ResourceUtil.get(key,this.getClass());
    }
}
