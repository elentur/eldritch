package gamemechanics.encounter;

import Service.GameService;
import container.ItemStack;
import container.Result;
import enums.EncounterType;
import enums.ItemType;
import enums.SituationType;
import enums.TestType;
import gamemechanics.SkillTest;
import lombok.Getter;
import lombok.Setter;
import model.Effect;
import model.Field;
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
    protected static final int START =0;
    protected static final int PASS =1;
    protected static final int FAIL =2;
    public String uniqueId = UUID.randomUUID().toString();
    private GameService game;
    int encounterPart;
    protected Result result;
    Investigator investigator;
    private TestType[] testType;
    private int[] minNumberOfSuccesses;
    private int[] mod;
    private Effect[][] effect;
    private SituationType situationType;
    private Field field;
    private ItemStack stack;

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
    public ItemType getItemType() {
        return ItemType.ENCOUNTER;
    }

    @Override
    public ItemType getSubType() {
        return ItemType.NONE;
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

    public void init() {
        setEncounterPart(0);
        game=GameService.getInstance();
        setInvestigator(game.getEncounteringInvestigator());
        this.field = game.getFieldOfInvestigator(getInvestigator());
    }
    @Override
    public void setStack(ItemStack itemStack){
        stack=itemStack;
    }
    @Override
    public void discard(){
        stack.discard(this);
    }
}
