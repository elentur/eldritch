package factory;

import container.ItemContainer;
import container.ItemStack;
import enums.FieldID;
import enums.OldOnes;
import gamemechanics.encounter.*;
import lombok.extern.java.Log;
import model.Item.Asset;
import model.Item.Spell;
import model.Item.token.ClueToken;

import java.io.File;

@Log
public class ItemFactory {

    private static ItemContainer<Asset> assets;
    private static ItemContainer<Spell> spells;

    private static ItemContainer<StandardEncounter> standardEncounters;
    private static ItemContainer<ExpeditionEncounter> expeditionEncounters;
    private static ItemContainer<SpecialEncounter> specialEncounters;
    private static ItemContainer<ResearchEncounter> researchEncounters;
    private static ItemContainer<OtherWorldEncounter> otherWorldEncounters;
    private static ItemContainer<AmericaEncounter> americaEncounters;
    private static ItemContainer<AsiaEncounter> asiaEncounters;
    private static ItemContainer<EuropeEncounter> europeEncounters;

    private static ItemContainer<ClueToken> clueTokens;

    public static ItemStack<Asset> getAssets() {

        if (assets == null) {
           File f = new File("./src/model/item/assets");
            assets = new ItemContainer<>();
           for (String name: f.list()){
               try {
                   Asset asset = (Asset) Class.forName("model.Item.assets."+name.replace(".java","")).newInstance();
                   assets.add(asset);
               } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                   e.printStackTrace();
               }
           }
        }
        return new ItemStack<>(assets);
    }


    public static ItemStack<Spell> getSpells() {
        if (spells == null) {
            File f = new File("./src/model/item/spells");
            spells = new ItemContainer<>();
            for (String name: f.list()){
                try {
                    Spell spell = (Spell) Class.forName("model.Item.spells."+name.replace(".java","")).newInstance();
                    spells.add(spell);
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        return new ItemStack(spells);
    }

    public static ItemStack<StandardEncounter> getStandardEncounters() {

        if (standardEncounters == null) {
            File f = new File("./src/gamemechanics/encounter/standardencounter");
            standardEncounters = new ItemContainer<>();
            for (String name: f.list()){
                try {
                    StandardEncounter standardEncounter = (StandardEncounter) Class.forName("gamemechanics.encounter.standardencounter."+name.replace(".java","")).newInstance();
                    standardEncounters.add(standardEncounter);
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return new ItemStack<>(standardEncounters);
    }
    public static ItemStack<SpecialEncounter> getSpecialEncounters(OldOnes oldOnes) {

        if (specialEncounters == null) {
            File f = new File("./src/gamemechanics/encounter/specialencounter/"+ oldOnes.getText().toLowerCase().replace(" ",""));
            specialEncounters = new ItemContainer<>();
            for (String name: f.list()){
                try {
                    SpecialEncounter specialEncounter = (SpecialEncounter) Class.forName("gamemechanics.encounter.specialencounter."+ oldOnes.getText().toLowerCase().replace(" ","")+"."+name.replace(".java","")).newInstance();
                    specialEncounters.add(specialEncounter);
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return new ItemStack<>(specialEncounters);
    }

    public static ItemStack<ResearchEncounter> getResearchEncounters(OldOnes oldOnes) {

        if (researchEncounters == null) {
            File f = new File("./src/gamemechanics/encounter/researchencounter/"+ oldOnes.getText().toLowerCase().replace(" ",""));
            researchEncounters = new ItemContainer<>();
            for (String name: f.list()){
                try {
                    ResearchEncounter researchEncounter = (ResearchEncounter) Class.forName("gamemechanics.encounter.researchencounter."+ oldOnes.getText().toLowerCase().replace(" ","")+"."+name.replace(".java","")).newInstance();
                    researchEncounters.add(researchEncounter);
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return new ItemStack<>(researchEncounters);
    }

    public static ItemStack<OtherWorldEncounter> getOtherWorldEncounter() {

        if (otherWorldEncounters == null) {
            File f = new File("./src/gamemechanics/encounter/otherworldencounter");
            otherWorldEncounters = new ItemContainer<>();
            for (String name: f.list()){
                try {
                    OtherWorldEncounter otherWorldEncounter = (OtherWorldEncounter) Class.forName("gamemechanics.encounter.otherworldencounter."+name.replace(".java","")).newInstance();
                    otherWorldEncounters.add(otherWorldEncounter);
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return new ItemStack<>(otherWorldEncounters);
    }

    public static ItemStack<ExpeditionEncounter> getExpeditionEncounter() {

        if (expeditionEncounters == null) {
            File f = new File("./src/gamemechanics/encounter/expeditionencounter");
            expeditionEncounters = new ItemContainer<>();
            for (String name: f.list()){
                try {
                    ExpeditionEncounter otherWorldEncounter = (ExpeditionEncounter) Class.forName("gamemechanics.encounter.expeditionencounter."+name.replace(".java","")).newInstance();
                    expeditionEncounters.add(otherWorldEncounter);
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return new ItemStack<>(expeditionEncounters);
    }

    public static ItemStack<EuropeEncounter> getEuropeEncounter() {

        if (europeEncounters == null) {
            File f = new File("./src/gamemechanics/encounter/europeencounter");
            europeEncounters = new ItemContainer<>();
            for (String name: f.list()){
                try {
                    EuropeEncounter europeEncounter = (EuropeEncounter) Class.forName("gamemechanics.encounter.europeencounter."+name.replace(".java","")).newInstance();
                    europeEncounters.add(europeEncounter);
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return new ItemStack<>(europeEncounters);
    }

    public static ItemStack<AmericaEncounter> getAmericaEncounter() {

        if (americaEncounters == null) {
            File f = new File("./src/gamemechanics/encounter/americaencounter");
            americaEncounters = new ItemContainer<>();
            for (String name: f.list()){
                try {
                    AmericaEncounter americaEncounter = (AmericaEncounter) Class.forName("gamemechanics.encounter.americaencounter."+name.replace(".java","")).newInstance();
                    americaEncounters.add(americaEncounter);
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return new ItemStack<>(americaEncounters);
    }
    public static ItemStack<AsiaEncounter> getAsiaEncounter() {

        if (asiaEncounters == null) {
            File f = new File("./src/gamemechanics/encounter/asiaencounter");
            asiaEncounters = new ItemContainer<>();
            for (String name: f.list()){
                try {
                    AsiaEncounter asiaEncounter = (AsiaEncounter) Class.forName("gamemechanics.encounter.asiaencounter."+name.replace(".java","")).newInstance();
                    asiaEncounters.add(asiaEncounter);
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return new ItemStack<>(asiaEncounters);
    }

    public static ItemStack<ClueToken> getClueTokens() {
        if (clueTokens == null) {
            clueTokens = new ItemContainer<>();
           for(FieldID fieldID : FieldID.values()){
               clueTokens.add(new ClueToken(fieldID));
           }
        }
        return new ItemStack<>(clueTokens);
    }
}
