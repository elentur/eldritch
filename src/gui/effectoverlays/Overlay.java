package gui.effectoverlays;

import Service.GameService;
import javafx.scene.Group;
import lombok.Getter;
import lombok.Setter;
import model.Effect;
import model.Item.Investigator;


public class Overlay extends Group{
    private final Effect effect;
    @Getter
    @Setter
    private double x;
    @Getter
    @Setter
    private double y;

    public Overlay(Effect effect) {
         this.effect=effect;

    }

    protected void setCord(Investigator inv){
        if (inv.equals(GameService.getInstance().getActiveInvestigator())){
            setX(-800);
            setY(350);
        }
        else if (inv.equals(GameService.getInstance().getInactiveInvestigators()[0])){
            setX(-850);
            setY(150);
        } else if (inv.equals(GameService.getInstance().getInactiveInvestigators()[1])){
            setX(-850);
            setY(0);
        } else if (inv.equals(GameService.getInstance().getInactiveInvestigators()[2])){
            setX(-850);
            setY(-200);
        }
    }

    public int init() {

        if(!effect.isAccepted()){
            return -1;
        }
        return 0;
    }
}
