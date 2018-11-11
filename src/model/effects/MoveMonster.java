package model.effects;


import Service.GameService;
import enums.EffectTyps;
import enums.FieldID;
import gamemechanics.choice.MonsterChoice;
import lombok.Getter;
import model.Effect;
import model.Field;
import model.Item.Monster;
import utils.ResourceUtil;

@Getter
public class MoveMonster extends Effect {
    private  Monster monster;
    private  FieldID fieldID;

    public MoveMonster(Monster monster,FieldID fieldID) {
        super(EffectTyps.MOVE_MONSTER);
        this.monster = monster;
        this.fieldID = fieldID;
    }
    public MoveMonster(MonsterChoice choice,FieldID fieldID) {
        super(EffectTyps.MOVE_MONSTER);
        this.fieldID = fieldID;
        this.condition = choice;
    }


    @Override
    public void init(){
        super.init();
        if(monster == null && condition != null){
            monster = ((MonsterChoice)condition).getSelectedMonster().get(0);
        }
        if(monster== null){
            return;
        }
        GameService.getInstance().getGameBoard().removeMonster(monster);

    }

    @Override
    public void execute() {
        if(isExecuted()){
            return;
        }
        super.execute();
        if(!isAccepted()) return;
        Field field;
        field = GameService.getInstance().getGameBoard().getField(fieldID);
        if(field!=null) {
            GameService.getInstance().moveTo(monster, field);
        }
    }

    @Override
    public String getText() {

        if(fieldID!=null) {
            return ResourceUtil.get("${move}", "effect", monster.getName(),fieldID.getText());
        }
        return "";
    }
}
