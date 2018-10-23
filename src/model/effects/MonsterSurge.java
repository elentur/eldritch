package model.effects;


import Service.GameService;
import enums.EffectTyps;
import lombok.Getter;
import model.Effect;
import model.Field;
import model.Item.token.GateToken;
import utils.ResourceUtil;

@Getter
public class MonsterSurge extends Effect {


    public MonsterSurge() {
      super(EffectTyps.MONSTER_SURGE);
    }

    @Override
    public void execute() {
        if(isExecuted()){
            return;
        }
        super.execute();
        if(!isAccepted()) return;
        boolean spawnedAMonster = false;
        GameService game = GameService.getInstance();
        for(Field field: game.getGameBoard().getFields()){
            GateToken gate=  field.getGate();
            if(gate!= null && gate.getOmenState().equals(game.getOmenTrack().getOmen())){
                game.addEffectAfter(new SpawnMonster(field.getFieldID()));
                spawnedAMonster=true;
            }
        }
        if(!spawnedAMonster){
            game.addEffectAfter(new SpawnGate());
            game.getAncientOne();
        }


    }

    @Override
    public String getText() {
            return ResourceUtil.get("${monster_surge}","effect"  ) ;


    }
}
