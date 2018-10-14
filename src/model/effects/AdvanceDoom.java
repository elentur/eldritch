package model.effects;


import Service.GameService;
import enums.EffectTyps;
import lombok.Getter;
import model.Effect;
import model.Item.Investigator;
import utils.ResourceUtil;

@Getter
public class AdvanceDoom extends Effect {
    private final int value;
    private  Investigator investigator;

    public AdvanceDoom(  int value,Investigator investigator) {
        super(EffectTyps.ADVANCE_DOOM);
        this.investigator = investigator;
        this.value=value;
    }

    @Override
    public void execute() {
        super.execute();
        if(!isAccepted()) return;
        GameService.getInstance().getDoomTrack().advanceDoom(value);

    }

    @Override
    public String getText() {


            return ResourceUtil.get("${advance_doom}","effect", value+""  ) ;


    }
}
