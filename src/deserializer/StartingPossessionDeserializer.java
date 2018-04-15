package deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import container.ItemContainer;
import model.Item.Item;
import model.StartingPossession;

import java.io.IOException;
import java.util.List;

public class StartingPossessionDeserializer extends StdDeserializer<ItemContainer<Item>> {

    public StartingPossessionDeserializer() {
        this(null);
    }

    public StartingPossessionDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public ItemContainer<Item> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        ObjectMapper mapper = new ObjectMapper();
        return new ItemContainer<>(mapper.readValue(node.toString(), new TypeReference<List<StartingPossession>>() {
        }));
    }
}
