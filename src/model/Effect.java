package model;

import Service.GameService;
import enums.EffectTyps;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import oldVersion.gameBuild.Game;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public abstract class Effect {



   public  void execute(){
      GameService.getInstance().addEffect(this);
   }
}
