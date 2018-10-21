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
public class CloseGate extends Effect {
    private final EffectSelector selector;
    private GateToken token;

    public CloseGate(EffectSelector selector) {
        super(EffectTyps.CLOSE_GATE);
        this.selector=selector;
    }


    public void init() {
        if(isExecuted()){
            return;
        }
        super.init();
        token = GameService.getInstance().getFieldOfInvestigator(GameService.getInstance().getEncounteringInvestigator()).getGate();
    }

    @Override
    public void execute() {
        super.execute();
        if(!isAccepted()) return;
        if(token!=null){
            Field field=   GameService.getInstance().getGameBoard().getField(token.getFieldID());
            field.removeGate();
        }

    }

    @Override
    public String getText() {

        return ResourceUtil.get("${close_gate}","effect",selector.getText() );
    }
}
