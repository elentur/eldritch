package factory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import container.ItemContainer;
import deserializer.ItemBonusDeserializer;
import lombok.extern.java.Log;
import model.Item.Asset;
import model.Item.ItemBonus;
import model.Item.Spell;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Log
public class ItemFactory {

    private static ItemContainer<Asset> assets;
    private static ItemContainer<Spell> spells;

    public ItemContainer<Asset> getAssets() {

        if (assets == null) {
            ObjectMapper mapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addDeserializer(ItemBonus.class, new ItemBonusDeserializer());
            mapper.registerModule(module);

            try {
                File file = new File("./resources/items/asset.json");
                log.info("Beginn unmarschalling assets");
                assets = new ItemContainer<>(mapper.readValue(file, new TypeReference<List<Asset>>() {
                }));
                log.info("Unmarschalling assets successful");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return assets;
    }

    public ItemContainer<Spell> getSpells() {
        if (spells == null) {
            ObjectMapper mapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addDeserializer(ItemBonus.class, new ItemBonusDeserializer());
            mapper.registerModule(module);
            try {
                File file = new File("./resources/items/spell.json");
                log.info("Beginn unmarschalling spells");
                spells = new ItemContainer<>(mapper.readValue(file, new TypeReference<List<Spell>>() {
                }));
                log.info("Unmarschalling spells successful");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return spells;
    }
}
