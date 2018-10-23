package model.effects;


import Service.GameService;
import enums.EffectTyps;
import enums.FieldID;
import lombok.Getter;
import model.Effect;
import model.Field;
import model.Item.Monster;
import utils.ResourceUtil;

@Getter
public class SpawnMonster extends Effect {
    private Monster monster;
    private final FieldID fieldID;
    public SpawnMonster(FieldID fieldID) {
        super(EffectTyps.SPAWN_MONSTER);
        this.fieldID = fieldID;
    }
    public SpawnMonster(Monster monster, FieldID fieldID) {
        super(EffectTyps.SPAWN_MONSTER);
        this.fieldID = fieldID;
        this.monster=monster;
    }

    public void init() {

        super.init();
        if(monster==null) {
            monster = GameService.getInstance().getMonsterPool().draw();
        }
    }

    @Override
    public void execute() {
        if(isExecuted()){
            return;
        }
        if(!isAccepted()) return;
        super.execute();
        if(monster!=null){
            Field field = GameService.getInstance().getGameBoard().getField(fieldID);
            field.addMonster(monster);
        }
    }

    @Override
    public String getText() {

        return ResourceUtil.get("${spawn_monster}","effect" );
    }
}
