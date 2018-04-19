package model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import utils.ResourceUtil;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = {"id", "effects",})
public class Monster implements IMonster {
    private String id;
    private String name;
    private int willTest;
    private int horror;
    private int strengthTest;
    private int damage;
    private int toughness;
    private List<Effect> effects;


    public String getName(){
      return  ResourceUtil.get(name,this.getClass());
    }


    public Monster getInstance(){
        Monster monster = new Monster();
        monster.setId(id);
        monster.setName(name);
        monster.setWillTest(willTest);
        monster.setHorror(horror);
        monster.setStrengthTest(strengthTest);
        monster.setDamage(damage);
        monster.setToughness(toughness);
        monster.setEffects(effects);
        return monster;
    }
}
