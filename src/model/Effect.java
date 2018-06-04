package model;

import Service.GameService;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public abstract class Effect {



   public  void execute(){
      GameService.getInstance().addEffect(this);
   }
}
