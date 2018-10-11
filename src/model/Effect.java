package model;

import Service.GameService;
import enums.EffectTyps;
import gamemechanics.choice.Choice;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString(of = {"effectType"})
public abstract class Effect {
private final EffectTyps effectType;
@Getter
private boolean executed =false;


protected Choice condition=null;
protected GameService game = GameService.getInstance();

public Effect(EffectTyps effectType){
   this.effectType =effectType;
}


   public  void execute() {

        executed = true;

    //  GameService.getInstance().addEffect(this);
   }
   public abstract String getText();

    public void init() {

    }


}
