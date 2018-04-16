package factory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import container.InvestigatorContainer;
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

    private static  InvestigatorContainer investigators;
    public InvestigatorContainer getInvestigators() {
        if (investigators == null) {
            ObjectMapper mapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addDeserializer(ItemContainer.class, new StartingPossessionDeserializer());
            mapper.registerModule(module); investigators = new InvestigatorContainer();
            try {
                File file = new File("./resources/investigator/investigator.json");
                log.info("Beginn unmarschalling investigators");
                investigators = new InvestigatorContainer(mapper.readValue(file, new TypeReference<List<Investigator>>() {
                }));
                log.info("Unmarschalling investigators successful");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return investigators;
    }


}
