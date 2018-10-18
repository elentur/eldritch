package gamemechanics;

import enums.Dificulty;
import enums.MythosType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import model.effects.*;
import utils.ResourceUtil;

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
    public String getName() {
        return ResourceUtil.get(getNameId(), "mythosgreen");
    }

    public String getText() {
        return ResourceUtil.get(getNameId().replace("}", "_text}"), "mythosgreen");
    }


    @Override
    public void execute() {
        super.execute();
        discard();
    }
}
