package model;

import container.ItemContainer;
import enums.ConditionTyp;
import enums.TestTyp;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class Investigator {
    private String id;
    private String firstName;
    private String lastName;
    private String occupation;
    private SkillSet skillSet;
    private int health;
    private int sanity;
    private String startingSpace;
    private ItemContainer<StartingPossession> startingPossessions;
    public String getOccupation(){
        return  ResourceUtil.get(occupation,this.getClass());
    }

    public int getSkill(TestTyp typ) {
        return skillSet.getSkill(typ);
    }

    public List<ConditionTyp> getConditions() {
        List<ConditionTyp>conditions = new ArrayList<>();
        return conditions;

    }
}
