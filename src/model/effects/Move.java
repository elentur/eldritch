package model.effects;


import Service.GameService;
import enums.EffectTyps;
import enums.FieldID;
import lombok.Getter;
import model.Effect;
import model.Field;
import model.Item.Investigator;
import utils.ResourceUtil;

@Getter
public class Move extends Effect {
    private final int value;
    private final Investigator investigator;
    private  FieldID fieldID;

    public Move(int value, Investigator investigator) {
        super(EffectTyps.MOVE);
        this.value = value;
        this.investigator = investigator;
    }
    public Move(FieldID fieldID, Investigator investigator) {
        super(EffectTyps.MOVE);
        this.value=0;
        this.fieldID = fieldID;
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
        Field field;
        if(fieldID ==null){
            field = GameService.getInstance().getGameBoard().getFieldWithDistance(
                    GameService.getInstance().getFieldOfInvestigator(investigator),value);
        }else{
            field = GameService.getInstance().getGameBoard().getField(fieldID);
        }
        if(field!=null) {
            GameService.getInstance().moveTo(investigator, field);
        }
    }

    @Override
    public String getText() {

        if(fieldID!=null) {
            return ResourceUtil.get("${move}", "effect", investigator.getName(),fieldID.getText());
        }
        return "";
    }
}
