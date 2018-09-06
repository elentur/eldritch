package model.Item;

import Service.GameService;
import container.ItemStack;
import enums.Dificulty;
import enums.ItemType;
import enums.MythosType;
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
public abstract class MythosYellow extends Mythos {


    public MythosYellow( Dificulty dificulty) {
       super(MythosType.YELLOW,dificulty);
        getEffects().add(new AdvanceOmen(1));
        getEffects().add(new Reckoning());
        getEffects().add(new SpawnGate());

    }

}
