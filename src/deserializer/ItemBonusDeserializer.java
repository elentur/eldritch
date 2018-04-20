package deserializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import enums.SituationTyp;
import enums.ItemBonusTyp;
import enums.TestTyp;
import model.Item.*;

import java.io.IOException;
import java.util.List;

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
        JsonStreamContext parsingContext = jsonParser.getParsingContext();
        JsonStreamContext parent = parsingContext.getParent().getParent();
        Item currentValue = null;
        if(parent !=null) {
             currentValue = (Item) parent.getCurrentValue();
        }

        ItemBonus bonus = ItemBonus_Null.value();
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        ItemBonusTyp typ = valueOf(node.get("typ").textValue());
        JsonNode value = node.get("value");
        ObjectMapper mapper = new ObjectMapper();

        switch (typ) {
            case GAIN_DICE:
                bonus = new ItemBonus_GainDice(
                        value.get("value").intValue(),
                        TestTyp.valueOf(value.get("test").textValue()),
                        SituationTyp.valueOf(value.get("situation").textValue()));
                break;
            case DICE_RESULT:
                bonus = new ItemBonus_DiceResult( value.get("num").intValue(),
                        value.get("value").intValue(),
                        TestTyp.valueOf(value.get("test").textValue()),
                        SituationTyp.valueOf(value.get("situation").textValue()));
                break;
            case STRENGTH_TO_LORE:

                bonus = new ItemBonus_SwitchSkill(
                        SituationTyp.valueOf(value.get("situation").textValue()),TestTyp.STRENGTH,TestTyp.LORE,
                        mapper.readValue(value.get("consequence").toString(),new TypeReference<List<SpellConsequence>>(){}));
                break;
            case REPEAT_ROLL:

                bonus = new ItemBonus_RepeatRoll(
                        value.get("value").intValue(),
                        TestTyp.valueOf(value.get("test").textValue()),
                        SituationTyp.valueOf(value.get("situation").textValue()));
                break;
            default:
                break;
        }

        bonus.setParentItem(currentValue);
        return bonus;
    }
}
