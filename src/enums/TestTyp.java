package enums;

import utils.ResourceUtil;

public enum TestTyp {

    LORE("${lore}"),INFLUENCE("${influence}"),OBSERVATION("${observation}"),STRENGTH("${strength}"), NONE(""), ALL("${all}"), WILL("${will}");


    private String key;

     TestTyp(String key) {
        this.key = key;
    }

    public String getText() {
        return ResourceUtil.get(key,this.getClass());
    }

    public boolean equalsWithAll(TestTyp o){
       return o.equals(this) || this.equals(TestTyp.ALL) || o.equals(TestTyp.ALL);

    }

}
