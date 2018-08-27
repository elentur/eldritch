package gamemechanics.choice;


import enums.ChoiceType;
import model.Effect;
import model.Field;
import model.Item.Monster;
import utils.ResourceUtil;

import java.util.List;

public class MonsterChoice extends Choice{
    private final List<Monster> monsters;

    public MonsterChoice(Field field){
        super(ChoiceType.COMBAT_ENCOUNTER, field.getName() +" - " +field.getType().getText()+"\n" +ResourceUtil.get("${monster_choice}","ui"),"");
        this.monsters = field.getMonster();
    }
    public MonsterChoice(List<Monster> monsters){
        super(ChoiceType.COMBAT_ENCOUNTER, ResourceUtil.get("${monster_choice}","ui"),"");
        this.monsters = monsters;
    }


    private void executeEffects(List<Effect> effects) {
        if(effects==null){
            return;
        }
        for(Effect effect:effects){
            effect.execute();
        }
    }


    public List<Monster> getMonsters() {
        return monsters;
    }
}
