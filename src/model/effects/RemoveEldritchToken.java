package model.effects;


import enums.EffectTyps;
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
    private final Field field;


    public RemoveEldritchToken(Field field, EldritchToken token) {
        super(EffectTyps.REMOVE_ELDRITCH_TOKEN);
        this.field=field;
        this.token=token;

    }




    @Override
    public void execute() {
        super.execute();
        if(!isAccepted()) return;
        if(token!=null){
            field.removeEldritchToken(token);
            log.info("Remove Eldritch token on Field " + field.getFieldID().getText());
        }
    }

    @Override
    public String getText() {

        return ResourceUtil.get("${remove_eldritch_token}","effect"  );
    }
}
