package enums;

import lombok.Getter;
import utils.ResourceUtil;

public enum FieldID {

    FIELD_1("${field_1}",FieldType.CITY),
    FIELD_2("${field_2}",FieldType.SEA),
    FIELD_3("${field_3}",FieldType.SEA),
    FIELD_4("${field_4}",FieldType.WILDERNESS),
    FIELD_5("${field_5}",FieldType.CITY),
    FIELD_6("${field_6}",FieldType.CITY),
    FIELD_7("${field_7}",FieldType.CITY),
    FIELD_8("${field_8}",FieldType.SEA),
    FIELD_9("${field_9}",FieldType.WILDERNESS),
    FIELD_10("${field_10}",FieldType.WILDERNESS),
    FIELD_11("${field_11}",FieldType.SEA),
    FIELD_12("${field_12}",FieldType.SEA),
    FIELD_13("${field_13}",FieldType.SEA),
    FIELD_14("${field_14}",FieldType.CITY),
    FIELD_15("${field_15}",FieldType.WILDERNESS),
    FIELD_16("${field_16}",FieldType.WILDERNESS),
    FIELD_17("${field_17}",FieldType.CITY),
    FIELD_18("${field_18}",FieldType.SEA),
    FIELD_19("${field_19}",FieldType.WILDERNESS),
    FIELD_20("${field_20}",FieldType.CITY),
    FIELD_21("${field_21}",FieldType.WILDERNESS),
    ARKHAM("${arkham}",FieldType.CITY),
    SAN_FRANCISCO("${san_francisco}",FieldType.CITY),
    BUENOS_AIRES("${buenos_aires}",FieldType.CITY),
    LONDON("${london}",FieldType.CITY),
    ROME("${rome}",FieldType.CITY),
    ISTANBUL("${istanbul}",FieldType.CITY),
    TOKYO("${tokyo}",FieldType.CITY),
    SHANGHAI("${shanghai}",FieldType.CITY),
    SYDNEY("${sydney}",FieldType.CITY),
    AMAZON("${amzon}",FieldType.WILDERNESS),
    PYRAMIDS("${pyramids}",FieldType.WILDERNESS),
    HEART_OF_AFRICA("${heart_of_africa}",FieldType.WILDERNESS),
    TUNGUSKA("${tunguska}",FieldType.WILDERNESS),
    ANTARCITCA("${antarctica}",FieldType.SEA),
    HIMALAYA("${himalaya}",FieldType.WILDERNESS);


    @Getter
    private String key;
    @Getter
    private FieldType type;

    FieldID(String key,FieldType type) {
        this.key = key;
        this.type=type;
    }

    public String getText() {
        return ResourceUtil.get(key,this.getClass());
    }


}
