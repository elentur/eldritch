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
public abstract class MythosGreen extends Mythos {
    public MythosGreen( Dificulty dificulty) {
        super(MythosType.GREEN,dificulty);
        this.getEffects().add(new AdvanceOmen(1));
        this.getEffects().add(new MonsterSurge());
        this.getEffects().add(new SpawnClue(1));

    }

}
