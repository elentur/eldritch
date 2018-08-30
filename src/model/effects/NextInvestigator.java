package model.effects;


import Service.GameService;
import enums.EffectTyps;
import lombok.Getter;
import model.Effect;
import model.Item.Investigator;
import utils.ResourceUtil;

@Getter
public class NextInvestigator extends Effect {


    public NextInvestigator() {
      super(EffectTyps.NEXT_INVESTIGATOR);
    }

    @Override
    public void execute() {
        super.execute();
        GameService.getInstance().setActiveInvestigator();

    }

    @Override
    public String getText() {


            return ResourceUtil.get("${next_Investigator}","effect"  ) ;


    }
}
