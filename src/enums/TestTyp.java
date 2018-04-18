package enums;

import utils.ResourceUtil;

public enum TestTyp {

    LORE("${lore}"),INFLUENCE("${influence}"),OBSERVATION("${observation}"),STRENGTH("${strength}"), NONE(""), WILL("${will}");


    private String key;

    private TestTyp(String key) {
        this.key = key;
    }

    public String getText() {
        return ResourceUtil.get(key,this.getClass());
    }

}
