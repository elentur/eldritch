package enums;

import lombok.Getter;
import utils.ResourceUtil;

public enum FieldID {

    FIELD_1("${field_1}",FieldType.CITY),
    FIELD_2("${field_2}",FieldType.SEA),
    FIELD_3("${field_3}",FieldType.SEA),
    FIELD_4("${field_4}",FieldType.WILDERNESS,FIELD_1),
    FIELD_5("${field_5}",FieldType.CITY,FIELD_4),
    FIELD_6("${field_6}",FieldType.CITY),
    FIELD_7("${field_7}",FieldType.CITY,FIELD_6),
    FIELD_8("${field_8}",FieldType.SEA,FIELD_7),
    FIELD_9("${field_9}",FieldType.WILDERNESS),
    FIELD_10("${field_10}",FieldType.WILDERNESS,FIELD_8),
    FIELD_11("${field_11}",FieldType.SEA),
    FIELD_12("${field_12}",FieldType.SEA),
    FIELD_13("${field_13}",FieldType.SEA),
    FIELD_14("${field_14}",FieldType.CITY),
    FIELD_15("${field_15}",FieldType.WILDERNESS,FIELD_10),
    FIELD_16("${field_16}",FieldType.WILDERNESS,FIELD_14),
    FIELD_17("${field_17}",FieldType.CITY,FIELD_15),
    FIELD_18("${field_18}",FieldType.SEA,FIELD_15),
    FIELD_19("${field_19}",FieldType.WILDERNESS,FIELD_1),
    FIELD_20("${field_20}",FieldType.CITY,FIELD_17),
    FIELD_21("${field_21}",FieldType.WILDERNESS),
    ARKHAM("${arkham}",FieldType.CITY,FIELD_5,FIELD_6,FIELD_8,FIELD_9),
    SAN_FRANCISCO("${san_francisco}",FieldType.CITY,FIELD_1,FIELD_2,FIELD_5,FIELD_6,FIELD_7),
    BUENOS_AIRES("${buenos_aires}",FieldType.CITY,FIELD_3,FIELD_7,FIELD_8,FIELD_11,FIELD_12),
    LONDON("${london}",FieldType.CITY,FIELD_13,ARKHAM),
    ROME("${rome}",FieldType.CITY,FIELD_10,FIELD_14,LONDON),
    ISTANBUL("${istanbul}",FieldType.CITY,FIELD_16,FIELD_17,ROME),
    TOKYO("${tokyo}",FieldType.CITY,FIELD_2,FIELD_19,FIELD_20),
    SHANGHAI("${shanghai}",FieldType.CITY,FIELD_17,FIELD_19,FIELD_20,TOKYO),
    SYDNEY("${sydney}",FieldType.CITY,FIELD_3,FIELD_18,FIELD_20,FIELD_21),
    AMAZON("${amzon}",FieldType.WILDERNESS,FIELD_7,BUENOS_AIRES),
    PYRAMIDS("${pyramids}",FieldType.WILDERNESS,FIELD_10,ROME,ISTANBUL),
    HEART_OF_AFRICA("${heart_of_africa}",FieldType.WILDERNESS,FIELD_15,PYRAMIDS),
    TUNGUSKA("${tunguska}",FieldType.WILDERNESS,FIELD_16,FIELD_19),
    ANTARCITCA("${antarctica}",FieldType.SEA,FIELD_12,SYDNEY),
    HIMALAYA("${himalaya}",FieldType.WILDERNESS,FIELD_17,SHANGHAI);


    @Getter
    private String key;
    @Getter
    private FieldType type;
    @Getter
    private final FieldID[] neigbours;

    FieldID(String key,FieldType type, FieldID... neigbours) {
        this.key = key;
        this.type=type;
        this.neigbours=neigbours;

    }

    public String getText() {
        return ResourceUtil.get(key,this.getClass());
    }


}
