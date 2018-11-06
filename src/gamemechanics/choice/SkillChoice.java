package gamemechanics.choice;


import Service.GameService;
import enums.ChoiceType;
import enums.TestType;
import expetions.ItemChoiceException;
import expetions.SkillChoiceException;
import lombok.Getter;
import model.Effect;
import model.Item.Investigator;
import model.effects.Improve;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

public class SkillChoice extends Choice {


    private final List<TestType> testTypes;
    @Getter
    private final List<TestType> chosen = new ArrayList<>();
    @Getter
    private final int number;
    private final Investigator investigator;


    public SkillChoice(int number, List<TestType> testTypes,Investigator investigator) {
        super(ChoiceType.SKILL_CHOICE, ResourceUtil.get("${skill_choice}", "ui"), "");
        this.testTypes=testTypes;
        this.number = number;
        this.investigator = investigator;
    }

    public SkillChoice(int number,Investigator investigator) {
        super(ChoiceType.SKILL_CHOICE, ResourceUtil.get("${skill_choice}", "ui"), "");
        this.number = number;
        this.investigator = investigator;
        this.testTypes=new ArrayList<>();
            if(investigator.getSkillSet().getInfluenceMod()<2){
                testTypes.add(TestType.INFLUENCE);
            }
        if(investigator.getSkillSet().getObservationMod()<2){
            testTypes.add(TestType.OBSERVATION);
        }
        if(investigator.getSkillSet().getLoreMod()<2){
            testTypes.add(TestType.LORE);
        }
        if(investigator.getSkillSet().getWillMod()<2){
            testTypes.add(TestType.WILL);
        }
        if(investigator.getSkillSet().getStrengthMod()<2){
            testTypes.add(TestType.STRENGTH);
        }


    }


    public List<TestType> get() {
        if(testTypes.isEmpty()){
            throw new ItemChoiceException(ResourceUtil.get("${no_skill_for_choice}","exception"));
        }
        return testTypes;
    }

    private void executeEffects(List<Effect> effects) {
        if (effects == null) {
            return;
        }
        for (Effect effect : effects) {
            effect.execute();
        }
    }

    public void addTestType(TestType testType) {
        chosen.add(testType);
    }

    public void acceptChoice() {
        if(chosen.size()<=number){
            for(TestType type: chosen){
                GameService.getInstance().addEffect(new Improve(type,1,investigator));
            }
        }else {
            throw new SkillChoiceException(ResourceUtil.get("${skill_number_to_low}","exception",chosen.size()+"",number+""));
        }
    }
}
