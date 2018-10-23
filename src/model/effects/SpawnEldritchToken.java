package model.effects;


import Service.GameService;
import enums.EffectTyps;
import enums.FieldID;
import lombok.Getter;
import lombok.extern.java.Log;
import model.Effect;
import model.Field;
import model.Item.token.EldritchToken;
import utils.ResourceUtil;

@Getter
@Log
public class SpawnEldritchToken extends Effect {
    private final EldritchToken token;
    private final FieldID fieldID;


    public SpawnEldritchToken(FieldID fieldID, EldritchToken token) {
        super(EffectTyps.SPAWN_ELDRITCH_TOKEN);
        this.fieldID=fieldID;
        this.token=token;

    }




    @Override
    public void execute() {
        if(isExecuted()){
            return;
        }
        super.execute();
        if(!isAccepted()) return;
        if(token!=null){
            Field field = GameService.getInstance().getGameBoard().getField(fieldID);
            field.addEldritchToken(token);
            log.info("Spawn Eldritch token on Field " + field.getFieldID().getText());
        }
    }

    @Override
    public String getText() {

        return ResourceUtil.get("${spawn_eldritch_token}","effect"  );
    }
}
