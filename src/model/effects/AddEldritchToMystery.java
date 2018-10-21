package model.effects;


import Service.GameService;
import enums.EffectTyps;
import lombok.Getter;
import model.Effect;
import model.Item.token.EldritchToken;
import utils.ResourceUtil;

@Getter
public class AddEldritchToMystery extends Effect {
    private final int value;

    public AddEldritchToMystery(int value) {
        super(EffectTyps.ADD_ELDRITCH_TO_MYSTERY);
        this.value=value;
    }

    @Override
    public void execute() {
        if(isExecuted()){
            return;
        }
        super.execute();
        if(!isAccepted()) return;
            for (int i = 0; i < value; i++) {
                GameService.getInstance().getActiveMystery().addToken(new EldritchToken(null));
            }

    }

    @Override
    public String getText() {
            return ResourceUtil.get("${add_eldritch_to_mystery}","effect", value+""  ) ;
    }
}
