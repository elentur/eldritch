package model.effects;


import Service.GameService;
import enums.EffectTyps;
import lombok.Getter;
import model.Effect;
import model.Item.Investigator;
import utils.ResourceUtil;

import java.util.Arrays;
import java.util.List;

@Getter
public class ZoomTo extends Effect {
    private final Investigator investigator;

    public ZoomTo(Investigator investigator) {
        super(EffectTyps.ZOOM_TO);
        this.investigator = investigator;
    }


    @Override
    public void execute() {
        init();
        if(!isAccepted()) return;
      GameService.getInstance().zoomTo(investigator);


    }

    @Override
    public String getText() {
        return "";
    }
}
