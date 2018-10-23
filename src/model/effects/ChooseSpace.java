package model.effects;


import Service.GameService;
import enums.EffectTyps;
import lombok.Getter;
import model.Effect;
import model.Field;

import java.util.List;

@Getter
public class ChooseSpace extends Effect {
    private final int distance;
    private final Effect effect;

    public ChooseSpace(Effect effect) {
        this(-1,effect);
    }

    public ChooseSpace(int distance,Effect effect) {
        super(EffectTyps.CHOOSE_SPACE);
        this.distance=distance;
        this.effect=effect;
    }
    @Override
    public void execute() {
        if(isExecuted()){
            return;
        }
        init();
        if(!isAccepted()) return;
        GameService game = GameService.getInstance();
        if(distance==-1){
            game.setChooseFieldMode(true);
           for(Field f : game.getGameBoard().getFields()){
                f.setChooseAble(true);
           }
        }else{
          List<Field> field =   game.getGameBoard().getFieldsWithDistance(
                  game.getFieldOfInvestigator(game.getEncounteringInvestigator()),
                  distance);
            game.setChooseFieldMode(true);
            for(Field f : game.getGameBoard().getFields()){
                f.setChooseAble(field.contains(f));
            }
        }
        game.setChooseFieldEffect(effect);


    }

    @Override
    public String getText() {
        return "";
    }
}
