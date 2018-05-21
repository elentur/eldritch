package gamemechanics;

import container.Result;
import lombok.Getter;
import lombok.Setter;
import preparation.Preparation;

@Getter
@Setter
public class Encounter {
    int encounterPart;
    public Result getResult(){
        return null;
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
