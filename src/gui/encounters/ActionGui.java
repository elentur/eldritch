package gui.encounters;

import gamemechanics.Action;
import model.effects.NullEffect;


public class ActionGui extends EncounterGui {
    final gamemechanics.encounter.Encounter action;


    public ActionGui(Action action) {
        super(action);
        this.action = action;
        //Wenn es keiner Interaktion erfordert öffne nicht die Gui sondern füre gleich den Effekt aus
        if(!(action.getEffect()[0][0] instanceof NullEffect)){
            this.setVisible(false);
            action.completeEncounterPart();
            this.close();
            return;
        }

    }


}
