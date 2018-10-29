package gamemechanics.encounter;

import enums.EffectTyps;
import enums.EncounterType;
import enums.OtherWorldID;
import enums.SituationType;
import gamemechanics.choice.InformationChoice;
import lombok.Getter;
import lombok.Setter;
import model.Effect;
import model.effects.And;
import model.effects.NullEffect;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class OtherWorldEncounter extends ExpeditionEncounter {

    private OtherWorldID otherWorldID;
    private boolean gateClosed;

    public OtherWorldEncounter( String encounterID) {
        super( encounterID, EncounterType.OTHER_WORLD_ENCOUNTER);
        setSituationType(SituationType.OTHER_WORLD_ENCOUNTER);

        setEncounterPart(0);
    }


    @Override
    public String getNameId() {
        return "${other_world_encounter}";
    }


    @Override
    public String getId() {
        return "&other_world_encounter";
    }

    @Override
    public int completeEncounterPart() {
        checkForSpellConsequences();
        String header = "";
        String text = "";
        List<Effect> effects = new ArrayList<>();
        if (!(getEffect()[getEncounterPart()][START] instanceof NullEffect)) {
            checkGateClose(Collections.singletonList(getEffect()[getEncounterPart()][START]));
            getGame().addEffect(getEffect()[getEncounterPart()][START]);

        }
        if (result.isSuccess()) {
            header = ResourceUtil.get("${success}", "ui");
            text = getEncounterSuccessText() + "\n" + getEffect()[getEncounterPart()][PASS].getText();
            effects.add(getEffect()[getEncounterPart()][PASS]);
            setEncounterPart(getEncounterPart() == 0 ? 1 : 3);
        } else {
            header = ResourceUtil.get("${fail}", "ui");
            text = getEncounterFailText() + "\n" + getEffect()[getEncounterPart()][FAIL].getText();
            effects.add(getEffect()[getEncounterPart()][FAIL]);
            setEncounterPart(getEncounterPart() == 0 ? 2 : 3);
        }

        if (getEncounterPart() == 3) {
            checkGateClose(effects);
            getGame().addChoice(new InformationChoice(header, text, effects));
        }

        getPreparation().getUsedBoni().clear();
        return getEncounterPart();
    }

    private void checkGateClose(List<Effect> effects) {
        for(Effect effect : effects){
            if(effect.getEffectType().equals(EffectTyps.CLOSE_GATE)){
                gateClosed=true;
                break;
            }else if(effect.getEffectType().equals(EffectTyps.AND)){
                checkGateClose(((And)effect).getEffects());
            }
        }
    }


}
