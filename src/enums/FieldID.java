package enums;

import lombok.Getter;
import utils.ResourceUtil;

public enum FieldID {

    FIELD_1("${field_1}",FieldType.CITY,SpaceType.OTHER),
    FIELD_2("${field_2}",FieldType.SEA,SpaceType.OTHER),
    FIELD_3("${field_3}",FieldType.SEA,SpaceType.OTHER),
    FIELD_4("${field_4}",FieldType.WILDERNESS,SpaceType.OTHER,FIELD_1),
    FIELD_5("${field_5}",FieldType.CITY,SpaceType.OTHER,FIELD_4),
    FIELD_6("${field_6}",FieldType.CITY,SpaceType.OTHER),
    FIELD_7("${field_7}",FieldType.CITY,SpaceType.OTHER,FIELD_6),
    FIELD_8("${field_8}",FieldType.SEA,SpaceType.OTHER,FIELD_7),
    FIELD_9("${field_9}",FieldType.WILDERNESS,SpaceType.OTHER),
    FIELD_10("${field_10}",FieldType.WILDERNESS,SpaceType.OTHER,FIELD_8),
    FIELD_11("${field_11}",FieldType.SEA,SpaceType.OTHER),
    FIELD_12("${field_12}",FieldType.SEA,SpaceType.OTHER),
    FIELD_13("${field_13}",FieldType.SEA,SpaceType.OTHER),
    FIELD_14("${field_14}",FieldType.CITY,SpaceType.OTHER),
    FIELD_15("${field_15}",FieldType.CITY,SpaceType.OTHER,FIELD_10),
    FIELD_16("${field_16}",FieldType.CITY,SpaceType.OTHER,FIELD_14),
    FIELD_17("${field_17}",FieldType.CITY,SpaceType.OTHER,FIELD_15),
    FIELD_18("${field_18}",FieldType.SEA,SpaceType.OTHER,FIELD_15),
    FIELD_19("${field_19}",FieldType.WILDERNESS,SpaceType.OTHER,FIELD_1),
    FIELD_20("${field_20}",FieldType.CITY,SpaceType.OTHER,FIELD_17),
    FIELD_21("${field_21}",FieldType.WILDERNESS,SpaceType.OTHER),
    ARKHAM("${arkham}",FieldType.CITY,SpaceType.CITY,FIELD_5,FIELD_6,FIELD_8,FIELD_9),
    SAN_FRANCISCO("${san_francisco}",FieldType.CITY,SpaceType.CITY,FIELD_1,FIELD_2,FIELD_5,FIELD_6,FIELD_7),
    BUENOS_AIRES("${buenos_aires}",FieldType.CITY,SpaceType.CITY,FIELD_3,FIELD_7,FIELD_8,FIELD_11,FIELD_12),
    LONDON("${london}",FieldType.CITY,SpaceType.CITY,FIELD_13,ARKHAM),
    ROME("${rome}",FieldType.CITY,SpaceType.CITY,FIELD_10,FIELD_14,LONDON),
    ISTANBUL("${istanbul}",FieldType.CITY,SpaceType.CITY,FIELD_16,FIELD_17,ROME),
    TOKYO("${tokyo}",FieldType.CITY,SpaceType.CITY,FIELD_2,FIELD_19,FIELD_20),
    SHANGHAI("${shanghai}",FieldType.CITY,SpaceType.CITY,FIELD_17,FIELD_19,FIELD_20,TOKYO),
    SYDNEY("${sydney}",FieldType.CITY,SpaceType.CITY,FIELD_3,FIELD_18,FIELD_20,FIELD_21),
    AMAZON("${amzon}",FieldType.WILDERNESS,SpaceType.EXPEDITION,FIELD_7,BUENOS_AIRES),
    PYRAMIDS("${pyramids}",FieldType.WILDERNESS,SpaceType.EXPEDITION,FIELD_10,ROME,ISTANBUL),
    HEART_OF_AFRICA("${heart_of_africa}",FieldType.WILDERNESS,SpaceType.EXPEDITION,FIELD_15,PYRAMIDS),
    TUNGUSKA("${tunguska}",FieldType.WILDERNESS,SpaceType.EXPEDITION,FIELD_16,FIELD_19),
    ANTARCTICA("${antarctica}",FieldType.SEA,SpaceType.EXPEDITION,FIELD_12,SYDNEY),
    HIMALAYA("${himalaya}",FieldType.WILDERNESS,SpaceType.EXPEDITION,FIELD_17,SHANGHAI);



    @Getter
    private final String key;
    @Getter
    private final FieldType type;
    @Getter
    private final FieldID[] neigbours;
    @Getter
    private final SpaceType spaceType;

    FieldID(String key,FieldType type,SpaceType spaceType, FieldID... neigbours) {
        this.key = key;
        this.type=type;
        this.neigbours=neigbours;
        this.spaceType =spaceType;

    }

    public String getText() {
        return ResourceUtil.get(key,this.getClass());
    }


}
