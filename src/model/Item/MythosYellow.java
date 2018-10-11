package model.Item;

import enums.Dificulty;
import enums.MythosType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import model.effects.*;

@Getter
@Setter
@EqualsAndHashCode
public abstract class MythosYellow extends Mythos {


    public MythosYellow( Dificulty dificulty) {
       super(MythosType.YELLOW,dificulty);
        this.getEffects().add(new AdvanceOmen(1));
        this.getEffects().add(new Reckoning());
        this.getEffects().add(new SpawnGate());

    }

}
