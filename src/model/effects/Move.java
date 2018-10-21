package model.effects;


import Service.GameService;
import enums.EffectTyps;
import lombok.Getter;
import model.Effect;
import model.Field;
import model.Item.Investigator;
import utils.ResourceUtil;

@Getter
public class Move extends Effect {
    private final int value;
    private final Investigator investigator;
    private final Field field;

    public Move(int value, Investigator investigator) {
        super(EffectTyps.MOVE);
        this.value = value;
        this.field = GameService.getInstance().getGameBoard().getFieldWithDistance(
                GameService.getInstance().getFieldOfInvestigator(investigator),value);
        this.investigator = investigator;
    }
    public Move(Field field, Investigator investigator) {
        super(EffectTyps.MOVE);
        this.value=0;
        this.field = field;
        this.investigator = investigator;
    }

    @Override
    public void init(){
        super.init();
        GameService.getInstance().removeInvestigator(investigator);
    }

    @Override
    public void execute() {
        if(isExecuted()){
            return;
        }
        super.execute();
        if(!isAccepted()) return;
        if(field!=null) {
            GameService.getInstance().moveTo(investigator, field);
        }
    }

    @Override
    public String getText() {

        if(field!=null) {
            return ResourceUtil.get("${move}", "effect", investigator.getName(),field.getName());
        }
        return "";
    }
}
