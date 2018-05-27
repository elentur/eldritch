package model;

import enums.ItemType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import model.Item.Item;
import model.Item.ItemBonus;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = {"id", "effects",})
public abstract class Monster implements Item,IMonster {
    private String id;
    private String name;
    private int willTest;
    private int horror;
    private int strengthTest;
    private int damage;
    private int toughness;
    private int actualToughness;
    private List<Effect> effects;


    @Override
    public String getNameId() {
        return name;
    }

    public String getName(){
      return  ResourceUtil.get(name,"monster");
    }

    @Override
    public List<ItemBonus> createBonus() {
        return new ArrayList<>();
    }

    @Override
    public List<ItemBonus> getBonus() {
        return new ArrayList<>();
    }

    @Override
    public ItemType getItemTyp() {
        return ItemType.NONE;
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
