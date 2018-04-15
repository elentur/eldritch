package factory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import container.ItemContainer;
import deserializer.StartingPossessionDeserializer;
import lombok.extern.java.Log;
import model.Investigator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log
public class InvestigatorFactory {

    public List<Investigator> getInvestigators() {

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(ItemContainer.class, new StartingPossessionDeserializer());
        mapper.registerModule(module);
        List<Investigator> investigators = new ArrayList<>();
        try {
            File file = new File("./resources/investigator/investigator.json");
            log.info("Beginn unmarschalling investigators");
            investigators =  mapper.readValue(file,  new TypeReference<List<Investigator>>(){});
            log.info("Unmarschalling investigators successful");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return investigators;
    }
}
