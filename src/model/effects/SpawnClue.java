package model.effects;


import Service.GameService;
import enums.EffectTyps;
import lombok.Getter;
import lombok.extern.java.Log;
import model.Effect;
import model.Field;
import model.Item.token.ClueToken;
import utils.ResourceUtil;

@Getter
@Log
public class SpawnClue extends Effect {
    private final int value;
    private ClueToken token;

    public SpawnClue(int value) {
        super(EffectTyps.SPAWN_CLUE);
        this.value = value;
    }


    public void init() {
         token = GameService.getInstance().getClueTokens().draw();
    }

    @Override
    public void execute() {
        super.execute();
        if(!isAccepted()) return;
        if(token!=null){
          Field field=   GameService.getInstance().getGameBoard().getField(token.getFieldID());
            field.addClue(token);
            log.info("Spawn Clue on Field " + field.getFieldID().getText());
        }
    }

    @Override
    public String getText() {

        return ResourceUtil.get("${spawn_clue}","effect" ,value+"" );
    }
}
