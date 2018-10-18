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
public abstract class MythosYellow extends Mythos {


    public MythosYellow( Dificulty dificulty) {
       super(MythosType.YELLOW,dificulty);
        this.getEffects().add(new AdvanceOmen(1));
        this.getEffects().add(new Reckoning());
        this.getEffects().add(new SpawnGate());

    }

    public String getName() {
        return ResourceUtil.get(getNameId(), "mythosyellow");
    }

    public String getText() {
        return ResourceUtil.get(getNameId().replace("}", "_text}"), "mythosyellow");
    }

    @Override
    public void execute() {
        super.execute();
        discard();
    }

}
