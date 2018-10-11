package enums;

import lombok.Getter;
import lombok.Setter;
import utils.ResourceUtil;

public enum TestType {

    LORE("${lore}","%"),
    INFLUENCE("${influence}","&"),
    OBSERVATION("${observation}","$"),
    STRENGTH("${strength}","§"),
    WILL("${will}","\""),
    NONE("", " "),
    ALL("${all}"," ");


    private final String symbol;
    @Getter
    private String key;
    @Getter
    @Setter
    private int mod;

     TestType(String key, String symbol) {
        this.key = key;
        this.symbol = symbol;
    }


    public String getText() {
        return ResourceUtil.get(key,this.getClass());
    }

    public boolean equalsWithAll(TestType o){
       return o.equals(this) || this.equals(TestType.ALL) || o.equals(TestType.ALL);

    }

    public String getSymbol() {
        return symbol;
    }
}
