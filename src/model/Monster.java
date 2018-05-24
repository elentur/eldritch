package model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import model.Item.Item;
import utils.ResourceUtil;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = {"id", "effects",})
public class Monster extends Item implements IMonster {
    private String id;
    private String name;
    private int willTest;
    private int horror;
    private int strengthTest;
    private int damage;
    private int toughness;
    private int actualToughness;
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
        monster.setActualToughness(toughness);
        monster.setEffects(effects);
        return monster;
    }

    public void addDamage(int value) {
        actualToughness+=value;
        if(actualToughness> toughness){
            actualToughness=toughness;
        }else if(actualToughness<0){
            actualToughness=0;
        }
    }
}
