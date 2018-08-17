package gamemechanics.encounter;

import Service.GameService;
import container.Result;
import enums.EncounterType;
import enums.ItemType;
import enums.SituationType;
import enums.TestType;
import gamemechanics.SkillTest;
import gamemechanics.choice.InformationChoice;
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
    private final EncounterType encounterType;

    public String uniqueId = UUID.randomUUID().toString();
    private GameService game;
    int encounterPart;
    Result result;
    Investigator investigator;
    private TestType[] testType;
    private SituationType situationType;

    public Encounter(EncounterType type) {
        encounterType = type;
    }


    public Result getResult() {
        return result;
    }

    public Result check() {
        SkillTest skillTest = new SkillTest(getPreparation().getTestTyp(), getPreparation().getModificationForSkillTest());
        result = skillTest.execute(investigator);
        result.setMinNumberOfSuccesses(1);

        return result;
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

    public void showResultInformation(){
        InformationChoice informationChoice;
        if (result.isSuccess()) {
            informationChoice = new InformationChoice(ResourceUtil.get("${success}", "ui"), getEncounterSuccessText(), new ArrayList<>());
        } else {
            informationChoice = new InformationChoice(ResourceUtil.get("${fail}", "ui"), getEncounterFailText(), new ArrayList<>());
        }

        getGame().addChoice(informationChoice);
    }

    public String getEncounterStartText() {
        return "";
    }

    public String getEncounterFailText() {
        return "";
    }

    public String getEncounterSuccessText() {
        return "";
    }
}
