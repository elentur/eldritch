package model;

import enums.EffectTyps;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Effect {

   private EffectTyps typ;
   private Action action;
}
