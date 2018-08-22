package enums;

import lombok.Getter;
import utils.ResourceUtil;

public enum OldOnes {
    AZATHOTH("${azathoth}"),
    YOG_SOTHOTH("${yog_sothoth}"),
    SHUB_NIGGURATH("${shub_niggurath}"),
    CTHULHU("${cthulhu}");

    @Getter
    private String key;

    private OldOnes(String key) {
        this.key = key;
    }
    public String getText() {
        return ResourceUtil.get(key,this.getClass());
    }
}
