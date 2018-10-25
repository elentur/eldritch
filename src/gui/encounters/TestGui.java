package gui.encounters;

import gamemechanics.Action;
import gamemechanics.Test;
import model.effects.NullEffect;


public class TestGui extends EncounterGui {
    final gamemechanics.encounter.Encounter test;


    public TestGui(Test test) {
        super(test);
        this.test = test;
        //Wenn es keiner Interaktion erfordert öffne nicht die Gui sondern füre gleich den Effekt aus
        if(!(test.getEffect()[0][0] instanceof NullEffect)){
            this.setVisible(false);
            test.completeEncounterPart();
            this.close();
            return;
        }

    }


}
