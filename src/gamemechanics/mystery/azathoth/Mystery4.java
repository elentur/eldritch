package gamemechanics.mystery.azathoth;

import Service.GameService;
import enums.OldOnes;
import gamemechanics.Mystery;
import gamemechanics.encounter.mysterieencounter.azathoth.MysteryEncounter1;
import model.Field;
import model.Item.token.EldritchToken;
import model.effects.SpawnEldritchToken;

import java.util.HashSet;

public class Mystery4 extends Mystery {


    public Mystery4() {
        super(3, OldOnes.AZATHOTH);
    }

    @Override
    public void init() {
      int num = (int)Math.ceil(GameService.getInstance().getInvestigators().size()/2);
      HashSet<Field> fields = new HashSet<>();
      while (fields.size()<num){
          fields.add(GameService.getInstance().getRandomField());
      }
      for(Field f:fields){
          EldritchToken e = new EldritchToken(new MysteryEncounter1());
          GameService.getInstance().addEffect(new SpawnEldritchToken(f,e));
      }
    }

    @Override
    public boolean isFinished() {
        return getTokens().size()>= Math.ceil(GameService.getInstance().getInvestigators().size()/2);
    }
}
