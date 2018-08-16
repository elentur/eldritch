package gamemechanics.encounter;

import container.Result;
import enums.ItemType;
import enums.SituationType;
import enums.TestType;
import lombok.Getter;
import lombok.Setter;
import model.Item.Investigator;
import model.Item.Item;
import model.Item.ItemBonus;
import preparation.Preparation;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public abstract class Encounter implements Item {
    public String uniqueId = UUID.randomUUID().toString();
    int encounterPart;
    Result result;
    Investigator investigator;
    private TestType testType;
    private SituationType situationType;


    public Result getResult() {
        return result;
    }

    public Result check() {
        return null;
    }

    public Preparation getPreparation() {
        return null;
    }

    public int completeEncounterPart() {
        return ++encounterPart;
    }


    @Override
    public List<ItemBonus> createBonus() {
        return new ArrayList<>();
    }

    @Override
    public List<ItemBonus> getBonus() {
        return new ArrayList<>();
    }

    @Override
    public ItemType getItemTyp() {
        return ItemType.ENCOUNTER;
    }

    @Override
    public String getName() {
        return ResourceUtil.get(getNameId(), "encounter");

    }
}
