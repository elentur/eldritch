package model.Item.boni;

import Service.GameService;
import enums.*;
import gamemechanics.Action;
import gamemechanics.encounter.Encounter;
import javafx.beans.value.ChangeListener;
import lombok.Getter;
import lombok.Setter;
import model.Item.Bonus;
import model.Item.Item;
import model.Item.ItemBonus;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter

public class ItemBonus_Delayed extends ItemBonus {
    private  int rounds;
    private final List<Action> allowedActions;
    private BonusType bonusType = BonusType.DELAYED;
    private final ChangeListener phaseListener;
    private final Function<Encounter,Encounter> encounterListener;

    public ItemBonus_Delayed(int rounds, List<Action> allowedActions, Item parentItem) {
        super(parentItem);
        this.situation = SituationType.ALL;
        this.test = TestType.NONE;
        this.eventTime = EventTimeType.NONE;
        this.rounds = rounds;
        this.allowedActions = allowedActions==null?new ArrayList<>():allowedActions;
        this.phaseListener = (a, b, c)->checkState();
        this.encounterListener = (a)->checkAction(a);
    }

    private Encounter checkAction(Encounter encounter) {
        if(encounter != null && encounter.getEncounterType().equals(EncounterType.ACTION)){
            if(!allowedActions.contains(encounter)){
              return null;
            }
        }
      return encounter;
    }

    private void checkState() {
        rounds--;
        if(rounds==0){
           destroy();
        }
    }

    public  void create(){
        GameService.getInstance().getPhases().getUpdate().addListener(phaseListener);
        GameService.getInstance().addEncounterListener(encounterListener);
    }
    public void destroy() {
        GameService.getInstance().getPhases().getUpdate().removeListener(phaseListener);
        GameService.getInstance().removeEncounterListener(encounterListener);
    }


    @Override
    public void execute(Encounter encounter) {
        if(!isExecutable()){
            return;
        }

    }


    @Override
    public String getText() {
        return ResourceUtil.get("${delayed}",Bonus.class );
    }

}
