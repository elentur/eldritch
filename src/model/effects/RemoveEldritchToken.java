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
public class RemoveEldritchToken extends Effect {
    private final EldritchToken token;
    private final FieldID fieldID;


    public RemoveEldritchToken(FieldID fieldID, EldritchToken token) {
        super(EffectTyps.REMOVE_ELDRITCH_TOKEN);
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
            field.removeEldritchToken(token);
            log.info("Remove Eldritch token on Field " + field.getFieldID().getText());
        }
    }

    @Override
    public String getText() {

        return ResourceUtil.get("${remove_eldritch_token}","effect"  );
    }
}
