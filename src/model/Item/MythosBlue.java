package model.Item;

import Service.GameService;
import container.ItemStack;
import enums.Dificulty;
import enums.ItemType;
import enums.MythosType;
import gamemechanics.encounter.Encounter;
import gamemechanics.encounter.RumorEncounter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import model.Effect;
import model.effects.*;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public abstract class MythosBlue extends Mythos {
    private final RumorEncounter encounter;
    public MythosBlue(RumorEncounter encounter,Dificulty dificulty){
           super(MythosType.BLUE,dificulty);
           this.encounter=encounter;
                getEffects().add(new SpawnClue(1));
    }

    public RumorEncounter getEncounter(){
        return encounter;
    }
}
