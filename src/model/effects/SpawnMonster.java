package model.effects;


import Service.GameService;
import enums.EffectTyps;
import lombok.Getter;
import model.Effect;
import model.Field;
import model.Item.Monster;
import model.Item.token.GateToken;
import utils.ResourceUtil;

@Getter
public class SpawnMonster extends Effect {
    private Monster monster;
    private final Field field;
    public SpawnMonster(Field field) {
        super(EffectTyps.SPAWN_MONSTER);
        this.field = field;
    }


    public void init() {
        monster = GameService.getInstance().getMonsterPool().draw();
    }

    @Override
    public void execute() {
        super.execute();
        if(monster!=null){
            field.addMonster(monster);
        }
    }

    @Override
    public String getText() {

        return ResourceUtil.get("${spawn_monster}","effect" );
    }
}
