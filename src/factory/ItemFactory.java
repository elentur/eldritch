package factory;

import container.ItemContainer;
import lombok.extern.java.Log;
import model.Item.Asset;
import model.Item.Spell;

import java.io.File;

@Log
public class ItemFactory {

    private static ItemContainer<Asset> assets;
    private static ItemContainer<Spell> spells;

    public static ItemContainer<Asset> getAssets() {

        if (assets == null) {
           File f = new File("./src/model/item/assets");
            assets = new ItemContainer<>();
           for (String name: f.list()){
               try {
                   Asset asset = (Asset) Class.forName("model.Item.assets."+name.replace(".java","")).newInstance();
                   assets.add(asset);
               } catch (InstantiationException e) {
                   e.printStackTrace();
               } catch (IllegalAccessException e) {
                   e.printStackTrace();
               } catch (ClassNotFoundException e) {
                   e.printStackTrace();
               }
           }
        }
        return assets;
    }


    public static ItemContainer<Spell> getSpells() {
        if (spells == null) {
            File f = new File("./src/model/item/spells");
            spells = new ItemContainer<>();
            for (String name: f.list()){
                try {
                    Spell spell = (Spell) Class.forName("model.Item.spells."+name.replace(".java","")).newInstance();
                    spells.add(spell);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        return spells;
    }
}
