package model.Item;

import enums.ItemType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import model.Effect;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(of = {"uniqueId"})
@ToString(exclude = {"id", "effects",})
public abstract class Monster implements Item, IMonster {
    public String uniqueId = UUID.randomUUID().toString();
    public String id;
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

    public Monster clone(){
        Monster m = new Monster() {
        };
        m.setUniqueId(this.uniqueId);
        m.setId(this.id);
        m.setName(this.name);
        m.setWillTest(this.willTest);
        m.setHorror(this.horror);
        m.setStrengthTest(this.strengthTest);
        m.setDamage(this.damage);
        m.setToughness(this.toughness);
        m.setActualToughness(this.actualToughness);
        m.setEffects(this.effects);
        return m;
    }
}
