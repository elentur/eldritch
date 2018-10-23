package model.effects;


import Service.GameService;
import enums.EffectSelector;
import enums.EffectTyps;
import lombok.Getter;
import model.Effect;
import model.Field;
import model.Item.token.GateToken;
import utils.ResourceUtil;

@Getter
public class SpawnGate extends Effect {
    private GateToken token;

    public SpawnGate() {
        super(EffectTyps.SPAWN_GATE);
    }


    public void init() {

        token = GameService.getInstance().getGateTokens().draw();
    }

    @Override
    public void execute() {
        if(isExecuted()){
            return;
        }
        super.execute();
        if(!isAccepted()) return;
        if(token!=null){
            Field field=   GameService.getInstance().getGameBoard().getField(token.getFieldID());
            field.addGate(token);
            GameService.getInstance().addEffectAfter(new SpawnMonster(token.getFieldID()));
        }else{
            GameService.getInstance().addEffectAfter(new AdvanceOmen(EffectSelector.THIS,1));
        }

    }

    @Override
    public String getText() {

        return ResourceUtil.get("${spawn_gate}","effect" );
    }
}
