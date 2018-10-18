package gamemechanics;

import Service.GameService;
import enums.Dificulty;
import enums.MythosType;
import gamemechanics.encounter.Encounter;
import gamemechanics.encounter.RumorEncounter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import model.Item.Investigator;
import model.Item.token.EldritchToken;
import model.effects.SpawnClue;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public abstract class MythosBlue extends Mythos {
    private final RumorEncounter encounter;
    private final List<EldritchToken> tokens;
    public MythosBlue(RumorEncounter encounter,Dificulty dificulty){
           super(MythosType.BLUE,dificulty);
           this.encounter=encounter;
                this.getEffects().add(new SpawnClue(1));
                tokens=new ArrayList<>();
    }

    public String getName() {
        return ResourceUtil.get(getNameId(), "mythosblue");
    }

    public String getText() {
        return ResourceUtil.get(getNameId().replace("}", "_text}"), "mythosblue");
    }

    public Encounter getEncounter(){
        return encounter;
    }

    @Override
    public void execute() {
        super.execute();
    }

    @Override
    public abstract void executeReckoning(Investigator inv, boolean autoFail);
}
