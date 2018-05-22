package enums;

import utils.ResourceUtil;

public enum TestTyp {

    LORE("${lore}","%"),
    INFLUENCE("${influence}","&"),
    OBSERVATION("${observation}","$"),
    STRENGTH("${strength}","§"),
    WILL("${will}","\""),
    NONE("", " "),
    ALL("${all}"," ");


    private final String symbol;
    private String key;

     TestTyp(String key,String symbol) {
        this.key = key;
        this.symbol = symbol;
    }

    public String getText() {
        return ResourceUtil.get(key,this.getClass());
    }

    public boolean equalsWithAll(TestTyp o){
       return o.equals(this) || this.equals(TestTyp.ALL) || o.equals(TestTyp.ALL);

    }

    public String getSymbol() {
        return symbol;
    }
}
