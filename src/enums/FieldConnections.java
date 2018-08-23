package enums;

import lombok.Getter;
import model.Field;

import static enums.FieldID.*;
import static enums.PathType.*;


public enum FieldConnections {

    CON_1(FIELD_4,FIELD_1,UNCHARTED),
    CON_2(FIELD_5,FIELD_4,UNCHARTED),
    CON_3(FIELD_7,FIELD_6,TRAIN),
    CON_4(FIELD_8,FIELD_7,SHIP),
    CON_5(FIELD_10,FIELD_8,SHIP),
    CON_6(FIELD_15,FIELD_10,SHIP),
    CON_7(FIELD_16,FIELD_14,TRAIN),
    CON_8(FIELD_17,FIELD_15,SHIP),
    CON_9(FIELD_18,FIELD_15,SHIP),
    CON_10(FIELD_19,FIELD_1,SHIP),
    CON_11(FIELD_20,FIELD_17,SHIP),
    CON_12(ARKHAM,FIELD_5,TRAIN),
    CON_13(ARKHAM,FIELD_6,TRAIN),
    CON_14(ARKHAM,FIELD_8,SHIP),
    CON_15(ARKHAM,FIELD_9,SHIP),
    CON_16(SAN_FRANCISCO,FIELD_1,SHIP),
    CON_17(SAN_FRANCISCO,FIELD_2,SHIP),
    CON_18(SAN_FRANCISCO,FIELD_5,TRAIN),
    CON_19(SAN_FRANCISCO,FIELD_6,SHIP),
    CON_20(SAN_FRANCISCO,FIELD_7,SHIP),
    CON_21(BUENOS_AIRES,FIELD_3,SHIP),
    CON_22(BUENOS_AIRES,FIELD_7,SHIP),
    CON_23(BUENOS_AIRES,FIELD_8,SHIP),
    CON_24(BUENOS_AIRES,FIELD_11,SHIP),
    CON_25(BUENOS_AIRES,FIELD_12,SHIP),
    CON_26(LONDON,FIELD_13,SHIP),
    CON_27(LONDON,ARKHAM,SHIP),
    CON_28(ROME,FIELD_10,SHIP),
    CON_29(ROME,FIELD_14,TRAIN),
    CON_30(ROME,LONDON,SHIP),
    CON_31(ISTANBUL,FIELD_16,TRAIN),
    CON_32(ISTANBUL,FIELD_17,TRAIN),
    CON_33(ISTANBUL,ROME,TRAIN),
    CON_34(TOKYO,FIELD_2,SHIP),
    CON_35(TOKYO,FIELD_19,SHIP),
    CON_36(TOKYO,FIELD_20,SHIP),
    CON_37(SHANGHAI,FIELD_17,TRAIN),
    CON_38(SHANGHAI,FIELD_19,TRAIN),
    CON_39(SHANGHAI,FIELD_20,SHIP),
    CON_40(SHANGHAI,TOKYO,SHIP),
    CON_41(SYDNEY,FIELD_3,SHIP),
    CON_42(SYDNEY,FIELD_18,SHIP),
    CON_43(SYDNEY,FIELD_20,SHIP),
    CON_44(SYDNEY,FIELD_21,UNCHARTED),
    CON_45(AMAZON,FIELD_7,UNCHARTED),
    CON_46(AMAZON,BUENOS_AIRES,UNCHARTED),
    CON_47(PYRAMIDS,FIELD_10,UNCHARTED),
    CON_48(PYRAMIDS,ROME,SHIP),
    CON_49(PYRAMIDS,ISTANBUL,TRAIN),
    CON_51(HEART_OF_AFRICA,FIELD_15,UNCHARTED),
    CON_52(HEART_OF_AFRICA,PYRAMIDS,UNCHARTED),
    CON_53(TUNGUSKA,FIELD_16,TRAIN),
    CON_54(TUNGUSKA,FIELD_19,TRAIN),
    CON_55(ANTARCITCA,FIELD_12,SHIP),
    CON_56(ANTARCITCA,SYDNEY,SHIP),
    CON_57(HIMALAYA,FIELD_17,SHIP),
    CON_58(HIMALAYA,SHANGHAI,SHIP);

    @Getter
    private final FieldID id1;
    @Getter
    private final FieldID id2;
    @Getter
    private final PathType pathType;

    FieldConnections(FieldID id1,FieldID id2,PathType pathType) {

        this.id1=id1;
        this.id2=id2;
        this.pathType=pathType;
    }
    public static FieldConnections getConnection(Field field1, Field field2){
        return getConnection(field1.getFieldID(),field2.getFieldID());
    }
    public static FieldConnections getConnection(FieldID id1,FieldID id2){
        for(FieldConnections connection:FieldConnections.values()){
            if((connection.id1.equals(id1)&&connection.id2.equals(id2))||
                    (connection.id1.equals(id2)&&connection.id2.equals(id1))){
                return connection;
            }
        }
        return null;
    }
}
