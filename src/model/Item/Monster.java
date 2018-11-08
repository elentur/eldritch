package model.Item;

import Service.GameService;
import container.ItemStack;
import enums.ItemType;
import gamemechanics.Action;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import model.Effect;
import model.effects.DiscardMonster;
import model.effects.NullEffect;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(of = {"uniqueId"})
@ToString(exclude = {"id", "effects",})
public abstract class Monster implements Item, IMonster {
    private final int count;
    public String uniqueId = UUID.randomUUID().toString();
    public String id;
    private String name;
    private int willTest;
    private int horror;
    private int strengthTest;
    private int damage;
    private int toughness;
    private int actualToughness;
    private Effect willTestEffect;
    private Effect strengthTestEffect;
    private Effect dieEffect;
    private ItemStack stack;


    public Monster(int count){
        this.count=count;
        willTestEffect=new NullEffect();
        strengthTestEffect=new NullEffect();
        dieEffect=new NullEffect();
    }
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
    public ItemType getSubType() {
        return ItemType.NONE;
    }



    public void addDamage(int value) {
        actualToughness+=value;
        if(actualToughness> toughness){
            actualToughness=toughness;
        }else if(actualToughness<=0){
            die();
        }
    }

    public  void die(){
        GameService.getInstance().addEffect(new DiscardMonster(this));

    }

    public Monster clone(){
        Monster m = new Monster(count) {
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
        m.setWillTestEffect(this.willTestEffect);
        m.setStrengthTestEffect(this.strengthTestEffect);
        m.setDieEffect(this.dieEffect);
        return m;
    }

    @Override
    public ItemType getItemType() {
        return ItemType.MONSTER;
    }

    @Override
    public void setStack(ItemStack itemStack){
        stack=itemStack;
    }
    @Override
    public void discard(){
        GameService.getInstance().getFieldOfMonster(this).removeMonster(this);
        stack.discard(this);
    }
    @Override
    public void executeReckoning(Investigator inv, boolean autoFail){}


    @Override
    public Monster draw(){
        return (Monster) getStack().draw();
    }

    @Override
    public List<Effect> getDrawEffects(Investigator investigator) {
        return new ArrayList<>();
    }

    @Override
    public Action getEncounter() {
        return null;
    }
}
