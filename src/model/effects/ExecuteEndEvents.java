package model.effects;


import enums.EffectTyps;
import lombok.Getter;
import model.Effect;

@Getter
public class ExecuteEndEvents extends Effect {
    private final Runnable r;

    public ExecuteEndEvents(Runnable r) {
        super(EffectTyps.EXECUTE_END_EVENTS);
        this.r =r;
    }


    @Override
    public void execute() {
        if(isExecuted()){
            return;
        }
        init();
        if(!isAccepted()) return;
         r.run();


    }

    @Override
    public String getText() {
        return "";
    }
}
