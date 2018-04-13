package deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import enums.ConditionTyp;
import enums.ItemBonusTyp;
import enums.TestTyp;
import model.Item.ItemBonus;
import model.Item.ItemBonus_Gain;
import model.Item.ItemBonus_Null;

import java.io.IOException;

import static enums.ItemBonusTyp.valueOf;

public class ItemBonusDeserializer extends StdDeserializer<ItemBonus> {

    public ItemBonusDeserializer() {
        this(null);
    }

    public ItemBonusDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public ItemBonus deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ItemBonus bonus = ItemBonus_Null.value();
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        ItemBonusTyp typ = valueOf(node.get("typ").textValue());
        JsonNode value = node.get("value");

        switch (typ) {
            case GAIN:
                bonus = new ItemBonus_Gain(
                        value.get("value").intValue(),
                        TestTyp.valueOf(value.get("test").textValue()),
                        ConditionTyp.valueOf(value.get("condition").textValue()));
                break;
            case DICE_RESULT:
                break;
            default:
                break;
        }


        return bonus;
    }
}
