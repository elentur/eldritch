package gamemechanics.mythos.blue;

import Service.GameService;
import enums.Dificulty;
import enums.FieldID;
import enums.SpendType;
import gamemechanics.Mythos;
import gamemechanics.encounter.rumorencounter.RumorEncounter0;
import gamemechanics.MythosBlue;
import model.Effect;
import model.Item.Investigator;
import model.Item.epicmonster.WindWalker;
import model.Item.token.EldritchToken;
import model.effects.And;
import model.effects.BecomeDelayed;
import model.effects.LooseOrGainHealthSanity;
import model.effects.SpawnMonster;

import java.util.List;

public class Mythos0 extends MythosBlue {

    public Mythos0() {
        super(null,Dificulty.NORMAL);
        for(int i=0; i < 4 ; i++) {
            getTokens().add(new EldritchToken(null));
        }
    }

    @Override
  public void execute(){
        Effect effect1 = new SpawnMonster(
                new WindWalker(this),
                GameService.getInstance().getGameBoard().getField(FieldID.FIELD_4));
        getEffects().add(effect1);
       super.execute();
    }

    @Override
    public String getId() {
        return "&mythos0";
    }

    @Override
    public String getNameId() {
        return "${mythos_0}";
    }


    @Override
    public void executeReckoning(Investigator inv, boolean autoFail) {
        getTokens().remove(0);
        if(getTokens().isEmpty()){
            discard();
            for(Investigator investigator : GameService.getInstance().getInvestigators()){
                Effect effect1 = new BecomeDelayed(investigator);
                Effect effect2 = new LooseOrGainHealthSanity(SpendType.HEALTH,6,investigator);
                GameService.getInstance().addEffect(new And(effect1,effect2));
            }
        }
    }
}
