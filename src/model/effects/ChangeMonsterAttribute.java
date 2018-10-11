package model.effects;


import enums.EffectTyps;
import enums.MonsterAttributeType;
import lombok.Getter;
import model.Effect;
import model.Item.Monster;
import utils.ResourceUtil;

@Getter
public class ChangeMonsterAttribute extends Effect {
    private final int value;
    private final MonsterAttributeType type;
    private  Monster monster;

    public ChangeMonsterAttribute(MonsterAttributeType type, int value, Monster monster) {
        super(EffectTyps.CHANGE_MONSTER_ATTRIBUTE);
        this.monster = monster;
        this.value=value;
        this.type = type;
    }

    @Override
    public void execute() {
        super.execute();
        switch (type){
            case DAMAGE:
                monster.setDamage(Math.max(monster.getDamage()+value,1));
                break;
            case HORROR:
                monster.setHorror(Math.max(monster.getHorror()+value,1));
                break;
            case TOUGHNESS:
                monster.setToughness(Math.max(monster.getToughness()+value,1));
                break;
        }

    }

    @Override
    public String getText() {


            return ResourceUtil.get("${change_monster_attribute}","effect",  type.getText(), value+""  ) ;


    }
}
