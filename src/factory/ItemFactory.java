package factory;

import container.*;
import enums.FieldID;
import enums.FieldType;
import enums.OldOnes;
import gamemechanics.*;
import gamemechanics.encounter.*;
import lombok.extern.java.Log;
import model.Item.*;
import model.Item.token.ClueToken;
import model.Item.token.GateToken;

import java.io.File;

@Log
public class ItemFactory {

    private static ItemContainer<Asset> assets;
    private static ItemContainer<Artifact> artifacts;
    private static ItemContainer<Spell> spells;

    private static ItemContainer<Condition> conditions;

    private static ItemContainer<StandardEncounter> standardEncounters;
    private static ItemContainer<ExpeditionEncounter> expeditionEncounters;
    private static ItemContainer<SpecialEncounter> specialEncounters;
    private static ItemContainer<ResearchEncounter> researchEncounters;
    private static ItemContainer<OtherWorldEncounter> otherWorldEncounters;
    private static ItemContainer<AmericaEncounter> americaEncounters;
    private static ItemContainer<AsiaEncounter> asiaEncounters;
    private static ItemContainer<EuropeEncounter> europeEncounters;
    private static ItemContainer<Mystery> mysteries;
    private static ItemContainer<Mythos> mythosGreen;
    private static ItemContainer<Mythos> mythosBlue;
    private static ItemContainer<Mythos> mythosYellow;


    private static ItemContainer<ClueToken> clueTokens;
    private static ItemContainer<GateToken> gateTokens;


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
        return new LoopedItemStack<>(assets);
    }
    public static ItemStack<Artifact> getArtifacts() {

        if (artifacts == null) {
            File f = new File("./src/model/item/artifacts");
            artifacts = new ItemContainer<>();
            for (String name: f.list()){
                try {
                    Artifact artifact = (Artifact) Class.forName("model.Item.artifacts."+name.replace(".java","")).newInstance();
                    artifacts.add(artifact);
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return new LoopedItemStack<>(artifacts);
    }

    public static ItemStack<Spell> getSpells() {
        if (spells == null) {
            File f = new File("./src/model/item/spells");
            spells = new ItemContainer<>();
            for (String name: f.list()){
                try {
                    for(int i = 0; i <Spell.NUMBER_OF_DIFFERENT_SPELLS;i++){
                        Spell spell = (Spell) Class.forName("model.Item.spells."+name.replace(".java","")).newInstance();
                        spells.add(spell);
                    }
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        return new RandomItemStack<>(spells);
    }
    public static ItemStack<Condition> getConditions() {
        if (conditions == null) {
            File f = new File("./src/model/item/conditions");
            conditions = new ItemContainer<>();
            for (String name: f.list()){
                try {
                    Condition condition = (Condition) Class.forName("model.Item.conditions."+name.replace(".java","")).newInstance();
                    conditions.add(condition);
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        return new RandomItemStack<>(conditions);
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
        return new RandomItemStack<>(standardEncounters);
    }
    public static ItemStack<SpecialEncounter> getSpecialEncounters(OldOnes oldOnes) {

        if (specialEncounters == null) {
            File f = new File("./src/gamemechanics/encounter/specialencounter/"+ oldOnes.getText().toLowerCase().replace(" ","_"));
            specialEncounters = new ItemContainer<>();
            if(f.list()!=null) {
                for (String name : f.list()) {
                    try {
                        SpecialEncounter specialEncounter = (SpecialEncounter) Class.forName("gamemechanics.encounter.specialencounter." + oldOnes.getText().toLowerCase().replace(" ", "") + "." + name.replace(".java", "")).newInstance();
                        specialEncounters.add(specialEncounter);
                    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return new RandomItemStack<>(specialEncounters);
    }
    public static ItemStack<Mystery> getMysteries(OldOnes oldOnes) {

        if (mysteries == null) {
            File f = new File("./src/gamemechanics/mystery/"+ oldOnes.getText().toLowerCase().replace(" ","_"));
            mysteries = new ItemContainer<>();
            if(f.list()!=null) {
                for (String name : f.list()) {
                    try {
                        Mystery mystery = (Mystery) Class.forName("gamemechanics.mystery." + oldOnes.getText().toLowerCase().replace(" ", "") + "." + name.replace(".java", "")).newInstance();
                        mysteries.add(mystery);
                    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return new FiniteItemStack<>(mysteries);
    }

    public static ItemStack<Mythos> getMythosGreen() {

        if (mythosGreen == null) {
            File f = new File("./src/gamemechanics/mythos/green");
            mythosGreen = new ItemContainer<>();
            if(f.list()!=null) {
                for (String name : f.list()) {
                    try {
                        MythosGreen mythos = (MythosGreen) Class.forName("gamemechanics.mythos.green"  + "." + name.replace(".java", "")).newInstance();
                        mythosGreen.add(mythos);
                    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return new FiniteItemStack<>(mythosGreen);
    }
    public static ItemStack<Mythos> getMythosBlue() {

        if (mythosBlue == null) {
            File f = new File("./src/gamemechanics/mythos/blue");
            mythosBlue = new ItemContainer<>();
            if(f.list()!=null) {
                for (String name : f.list()) {
                    try {
                        MythosBlue mythos = (MythosBlue) Class.forName("gamemechanics.mythos.blue"  + "." + name.replace(".java", "")).newInstance();
                        mythosBlue.add(mythos);
                    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return new FiniteItemStack<>(mythosBlue);
    }
    public static ItemStack<Mythos> getMythosYellow() {

        if (mythosYellow == null) {
            File f = new File("./src/gamemechanics/mythos/yellow");
            mythosYellow = new ItemContainer<>();
            if(f.list()!=null) {
                for (String name : f.list()) {
                    try {
                        MythosYellow mythos = (MythosYellow) Class.forName("gamemechanics.mythos.yellow"  + "." + name.replace(".java", "")).newInstance();
                        mythosYellow.add(mythos);
                    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return new FiniteItemStack<>(mythosYellow);
    }
    public static ItemStack<ResearchEncounter> getResearchEncounters(OldOnes oldOnes) {

        if (researchEncounters == null) {
            File f = new File("./src/gamemechanics/encounter/researchencounter/"+ oldOnes.getText().toLowerCase().replace(" ","_"));
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
        return new RandomItemStack<>(researchEncounters);
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
        return new RandomItemStack<>(otherWorldEncounters);
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
        return new LoopedItemStack<>(expeditionEncounters);
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
        return new RandomItemStack<>(europeEncounters);
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
        return new RandomItemStack<>(americaEncounters);
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
        return new RandomItemStack<>(asiaEncounters);
    }

    public static ItemStack<ClueToken> getClueTokens() {
        if (clueTokens == null) {
            clueTokens = new ItemContainer<>();
           for(FieldID fieldID : FieldID.values()){
               if(fieldID.getType().equals(FieldType.NONE)){
                   continue;
               }
               clueTokens.add(new ClueToken(fieldID));
           }
        }
        return new RandomItemStack<>(clueTokens);
    }

    public static ItemStack<GateToken> getGateTokens() {
        if (gateTokens == null) {
            File f = new File("./src/model/Item/token/gatetoken");
            gateTokens = new ItemContainer<>();
            for (String name: f.list()){
                try {
                    GateToken gateToken = (GateToken) Class.forName("model.Item.token.gatetoken."+name.replace(".java","")).newInstance();
                    gateTokens.add(gateToken);
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return new LoopedItemStack<>(gateTokens);
    }
}
