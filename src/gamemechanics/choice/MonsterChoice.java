package gamemechanics.choice;


import Service.GameService;
import enums.ChoiceType;
import enums.FieldID;
import lombok.Getter;
import model.Effect;
import model.Item.Monster;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

public class MonsterChoice extends Choice{
    private final List<Monster> monsters;
    private final boolean singleSelect;
    @Getter
    private final ArrayList<Monster> selectedMonster;

    public MonsterChoice(FieldID fieldID){
        super(ChoiceType.COMBAT_ENCOUNTER, fieldID.getText() +" - " +fieldID.getType().getText()+"\n" +ResourceUtil.get("${monster_choice}","ui"),"");
        this.monsters = GameService.getInstance().getGameBoard().getField(fieldID).getMonster();
        this.singleSelect=true;
        this.selectedMonster = new ArrayList<>();
    }
    public MonsterChoice(List<Monster> monsters){
        this(monsters,ChoiceType.COMBAT_ENCOUNTER);
    }
    public MonsterChoice(List<Monster> monsters, ChoiceType type){
        super(type, ResourceUtil.get("${monster_choice}","ui"),"");
        this.monsters = monsters;
        this.singleSelect=true;
        this.selectedMonster = new ArrayList<>();
    }
    public MonsterChoice(FieldID fieldID, boolean singleSelect){
        super(ChoiceType.MONSTER_CHOICE, fieldID.getText() +" - " +fieldID.getType().getText()+"\n" +ResourceUtil.get("${monster_choice}","ui"),"");
        this.monsters = GameService.getInstance().getGameBoard().getField(fieldID).getMonster();
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
            accepted=true;
        }
    }




    public List<Monster> getMonsters() {
        return monsters;
    }
}
