package gamemechanics.mystery.azathoth;

import Service.GameService;
import enums.FieldID;
import enums.OldOnes;
import gamemechanics.Mystery;
import gamemechanics.encounter.mysterieencounter.azathoth.MysteryEncounter0;
import gamemechanics.encounter.mysterieencounter.azathoth.MysteryEncounter1;
import model.Field;
import model.Item.token.EldritchToken;
import model.Item.token.MysteryToken;
import model.effects.SpawnClue;
import model.effects.SpawnEldritchToken;

import java.util.HashSet;

public class Mystery3 extends Mystery {


    public Mystery3() {
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
