package model.effects;


import Service.GameService;
import enums.EffectTyps;
import lombok.Getter;
import lombok.extern.java.Log;
import model.Effect;
import model.Field;
import model.Item.Investigator;
import model.Item.token.ClueToken;
import utils.ResourceUtil;

@Getter
@Log
public class SpawnClue extends Effect {
    private final int value;
    private final Investigator investigator;
    private ClueToken token;

    public SpawnClue(int value, Investigator investigator) {
        super(EffectTyps.SPAWN_CLUE);
        this.value = value;
        this.investigator = investigator;
    }


    public void init() {
         token = GameService.getInstance().getClueTokens().draw();
    }

    @Override
    public void execute() {
        super.execute();
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
