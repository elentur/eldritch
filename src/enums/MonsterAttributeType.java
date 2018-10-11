package enums;

import lombok.Getter;
import utils.ResourceUtil;

public enum MonsterAttributeType {
    HORROR("${horror}"),
    DAMAGE("${damage}"),
    TOUGHNESS("${toughness}");


    @Getter
    private String key;

    private MonsterAttributeType(String key) {
        this.key = key;
    }
    public String getText() {
        return ResourceUtil.get(key,this.getClass());
    }
}
