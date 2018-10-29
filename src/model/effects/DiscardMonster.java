package model.effects;


import enums.ChoiceType;
import enums.EffectSelector;
import enums.EffectTyps;
import gamemechanics.choice.Choice;
import gamemechanics.choice.MonsterChoice;
import lombok.Getter;
import lombok.extern.java.Log;
import model.Effect;
import model.Item.Monster;
import utils.ResourceUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
@Log
public class DiscardMonster extends Effect {
    private List<Monster> monster;


    public DiscardMonster(Monster monster) {
        super(EffectTyps.DISCARD_MONSTER);
        this.monster = Collections.singletonList(monster);

    }
    public DiscardMonster(List<Monster> monster) {
        super(EffectTyps.DISCARD_MONSTER);
        this.monster = monster;

    }

    public DiscardMonster(Choice choice) {
        super(EffectTyps.DISCARD_MONSTER);
        this.condition = choice;
    }


    @Override
    public void execute() {
        if(isExecuted()){
            return;
        }
        super.execute();
        if (!isAccepted()) return;
        if (monster != null) {
            for(Monster m: monster) {
                m.discard();
            }
        }
    }

    @Override
    public void init() {
        super.init();
        if (condition != null && condition.getChoiceType().equals(ChoiceType.MONSTER_CHOICE)) {
            monster = ((MonsterChoice) condition).getSelectedMonster();
        }
    }

    @Override
    public String getText() {
        if(monster!=null && !monster.isEmpty()) {
            StringBuilder s = new StringBuilder(monster.get(0).getName());

            for(int i =1; i< monster.size();i++){
                s.append(" and " +monster.get(i).getName());
            }

            return ResourceUtil.get("${discard_monster}", "effect", s.toString());
        }else{
            return ResourceUtil.get("${discard_monster}", "effect", EffectSelector.ANY.getText());
        }
    }
}
