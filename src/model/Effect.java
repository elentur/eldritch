package model;

import enums.EffectTyps;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Effect {

   private EffectTyps typ;
   private List<List<String>> parts;
}
