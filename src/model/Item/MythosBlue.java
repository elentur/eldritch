package model.Item;

import enums.Dificulty;
import enums.MythosType;
import gamemechanics.encounter.Encounter;
import gamemechanics.encounter.RumorEncounter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import model.effects.SpawnClue;

@Getter
@Setter
@EqualsAndHashCode
public abstract class MythosBlue extends Mythos {
    private final RumorEncounter encounter;
    public MythosBlue(RumorEncounter encounter,Dificulty dificulty){
           super(MythosType.BLUE,dificulty);
           this.encounter=encounter;
                this.getEffects().add(new SpawnClue(1));
    }

    public Encounter getEncounter(){
        return encounter;
    }
}
