package model;

import enums.EffectTyps;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class Effect {

   private EffectTyps typ;
   private List<List<String>> parts;
}
