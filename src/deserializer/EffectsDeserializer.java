package deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import model.Effect;

import java.io.IOException;

public class EffectsDeserializer extends StdDeserializer<Effect> {

    public EffectsDeserializer() {
        this(null);
    }

    public EffectsDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Effect deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        int id = (Integer) ((IntNode) node.get("id")).numberValue();
        String itemName = node.get("itemName").asText();
        int userId = (Integer) ((IntNode) node.get("createdBy")).numberValue();

        return null;
    }
}
