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

@Getter
@Log
public class DiscardMonster extends Effect {
    private Monster monster;


    public DiscardMonster(Monster monster) {
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
            monster.discard();
        }
    }

    @Override
    public void init() {
        super.init();
        if (condition != null && condition.getChoiceType().equals(ChoiceType.MONSTER_CHOICE)) {
            monster = ((MonsterChoice) condition).getSelectedMonster().get(0);
        }
    }

    @Override
    public String getText() {
        if(monster!=null) {
            return ResourceUtil.get("${discard_monster}", "effect", monster.getName());
        }else{
            return ResourceUtil.get("${discard_monster}", "effect", EffectSelector.ANY.getText());
        }
    }
}
