package gamemechanics;

import container.Result;
import lombok.Getter;
import lombok.Setter;
import model.Investigator;
import preparation.Preparation;

@Getter
@Setter
public class Encounter {
    int encounterPart;
     Result result;
     Investigator investigator;
    public Result getResult(){
        return result;
    }

    public Result check() {
        return null;
    }
    public Preparation getPreparation(){
        return null;
    }

    public int completeEncounterPart(){
        return ++encounterPart;
    }
}
