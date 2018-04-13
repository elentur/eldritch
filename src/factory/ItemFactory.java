package factory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import deserializer.ItemBonusDeserializer;
import lombok.extern.java.Log;
import model.Item.Asset;
import model.Item.ItemBonus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log
public class ItemFactory {

    public List<Asset> getAssets() {

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(ItemBonus.class, new ItemBonusDeserializer());
        mapper.registerModule(module);
        List<Asset> assets = new ArrayList<>();
        try {
            File file = new File("./resources/items/asset.json");
            log.info("Beginn unmarschalling assets");
            assets =  mapper.readValue(file,  new TypeReference<List<Asset>>(){});
            log.info("Unmarschalling assets successful");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return assets;
    }
}
