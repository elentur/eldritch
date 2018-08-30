package model.effects;


import enums.EffectTyps;
import lombok.Getter;
import model.Effect;
import model.Item.Investigator;
import utils.ResourceUtil;

@Getter
public class SpawnClue extends Effect {
    private final int value;
    private final Investigator investigator;

    public SpawnClue(int value, Investigator investigator) {
        super(EffectTyps.SPAWN_CLUE);
        this.value = value;
        this.investigator = investigator;
    }


    @Override
    public void execute() {
        super.execute();


    }

    @Override
    public String getText() {

        return ResourceUtil.get("${spawn_clue}","effect" ,value+"" );
    }
}
