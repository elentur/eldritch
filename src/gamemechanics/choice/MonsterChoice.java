package gamemechanics.choice;


import Service.GameService;
import enums.ChoiceType;
import lombok.Getter;
import model.Effect;
import model.Field;
import model.Item.Monster;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

public class MonsterChoice extends Choice{
    private final List<Monster> monsters;
    private final boolean singleSelect;
    @Getter
    private final ArrayList<Monster> selectedMonster;

    public MonsterChoice(Field field){
        super(ChoiceType.COMBAT_ENCOUNTER, field.getName() +" - " +field.getType().getText()+"\n" +ResourceUtil.get("${monster_choice}","ui"),"");
        this.monsters = field.getMonster();
        this.singleSelect=true;
        this.selectedMonster = new ArrayList<>();
    }
    public MonsterChoice(List<Monster> monsters){
        super(ChoiceType.COMBAT_ENCOUNTER, ResourceUtil.get("${monster_choice}","ui"),"");
        this.monsters = monsters;
        this.singleSelect=true;
        this.selectedMonster = new ArrayList<>();
    }
    public MonsterChoice(Field field, boolean singleSelect){
        super(ChoiceType.MONSTER_CHOICE, field.getName() +" - " +field.getType().getText()+"\n" +ResourceUtil.get("${monster_choice}","ui"),"");
        this.monsters = field.getMonster();
        this.singleSelect = singleSelect;
        this.selectedMonster = new ArrayList<>();
    }


    private void executeEffects(List<Effect> effects) {
        if(effects==null){
            return;
        }
        for(Effect effect:effects){
            effect.execute();
        }
    }

    public boolean isSingleSelect() {
        return singleSelect;
    }

    public void addSelection(Monster monster) {
        this.selectedMonster.add(monster);
        GameService.getInstance().setLastChosenMonster(monster);
        if(singleSelect) {
            getChoiceTakenProperty().setValue(true);
        }
    }




    public List<Monster> getMonsters() {
        return monsters;
    }
}
