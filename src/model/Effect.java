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
@Getter
private boolean executed =false;
public Effect(EffectTyps effectTyp){
   this.effectTyp=effectTyp;
}


   public  void execute(){
    executed=true;
    //  GameService.getInstance().addEffect(this);
   }
   public abstract String getText();

    public void init() {

    }
}
