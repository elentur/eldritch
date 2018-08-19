package model;

import Service.GameService;
import enums.EffectTyps;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public abstract class Effect {
private final EffectTyps effectTyp;
public Effect(EffectTyps effectTyp){
   this.effectTyp=effectTyp;
}


   public  void execute(){
      GameService.getInstance().addEffect(this);
   }
}
