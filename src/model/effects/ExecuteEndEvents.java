package model.effects;


import Service.GameService;
import enums.EffectTyps;
import javafx.application.Platform;
import lombok.Getter;
import model.Effect;
import utils.ResourceUtil;

import java.util.Arrays;
import java.util.List;

@Getter
public class ExecuteEndEvents extends Effect {
    private final Runnable r;

    public ExecuteEndEvents(Runnable r) {
        super(EffectTyps.EXECUTE_END_EVENTS);
        this.r =r;
    }


    @Override
    public void execute() {
        init();
        if(!isAccepted()) return;
         r.run();


    }

    @Override
    public String getText() {
        return "";
    }
}
