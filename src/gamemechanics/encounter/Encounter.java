package gamemechanics.encounter;

import Service.GameService;
import container.Result;
import enums.EncounterType;
import enums.ItemType;
import enums.SituationType;
import enums.TestType;
import gamemechanics.SkillTest;
import lombok.Getter;
import lombok.Setter;
import model.Effect;
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
    protected static final int PASS =0;
    protected static final int FAIL =1;
    public String uniqueId = UUID.randomUUID().toString();
    private GameService game;
    int encounterPart;
    Result result;
    Investigator investigator;
    private TestType[] testType;
    private int[] minNumberOfSuccesses;
    private Effect[][] effect;
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
        result.setMinNumberOfSuccesses(getMinNumberOfSuccesses()[getEncounterPart()]);

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

    public String getResultInformation(){

        if (result.isSuccess()) {
           return getEncounterSuccessText();
        } else {
          return getEncounterFailText();
        }


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

    public void executeEffect() {
        if (result.isSuccess()&&effect[encounterPart][0]!=null) {
            effect[encounterPart][0].execute();
        }else if(!result.isSuccess()&&effect[encounterPart][1]!=null){
            effect[encounterPart][1].execute();
        }
    }
}
