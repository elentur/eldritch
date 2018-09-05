package model.effects;


import Service.GameService;
import enums.EffectTyps;
import lombok.Getter;
import model.Effect;
import utils.ResourceUtil;

@Getter
public class MonsterFlood extends Effect {


    public MonsterFlood() {
      super(EffectTyps.MONSTER_FLOOD);
    }

    @Override
    public void execute() {
        super.execute();
        GameService.getInstance().doMonsterFlood();


    }

    @Override
    public String getText() {
            return ResourceUtil.get("${monster_flood}","effect"  ) ;


    }
}
