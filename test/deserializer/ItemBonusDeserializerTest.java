package deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import enums.SituationTyp;
import enums.TestTyp;
import model.Item.ItemBonus;
import model.Item.ItemBonus_GainDice;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class ItemBonusDeserializerTest {


    @InjectMocks
    ItemBonusDeserializer itemBonusDeserializer;

    @Mock
    JsonParser parser;

    @Mock
    ObjectCodec codec;

    ItemBonus_GainDice itemBonusGain;


    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        itemBonusGain = new ItemBonus_GainDice(2,TestTyp.STRENGTH,SituationTyp.COMBAT_ENCOUNTER);
        Mockito.when(parser.getCodec()).thenReturn(codec);
        String jsonString = "{\n" +
                "        \"typ\": \"GAIN_DICE\",\n" +
                "        \"value\": {\n" +
                "          \"value\": 2,\n" +
                "          \"test\": \"STRENGTH\",\n" +
                "          \"situation\": \"COMBAT_ENCOUNTER\"\n" +
                "        }\n" +
                "      }";

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonString);

        Mockito.when(codec.readTree(parser)).thenReturn(node);
    }

    @Test
    public void testDeserialize() {

        try {
            ItemBonus bonus = itemBonusDeserializer.deserialize(parser,null);
            Assert.assertEquals(bonus, itemBonusGain);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}