package model.Item;

import Service.GameService;
import enums.*;
import gamemechanics.choice.Choice;
import gamemechanics.encounter.Encounter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public abstract class ItemBonus implements Bonus {
    protected Item parentItem;
    protected boolean activated = true;
    protected boolean usable = true;
    protected RangeType range = RangeType.SELF;
    protected FieldType field = FieldType.ALL;
    protected TestType test = TestType.NONE;
    protected SituationType situation = SituationType.ALL;
    protected EventTimeType eventTime = EventTimeType.NONE;
    protected boolean perRound=false;
    protected boolean passive=false;
    protected Choice condition=null;
    protected GameService game = GameService.getInstance();





    public ItemBonus(Item parentItem){
        this.parentItem=parentItem;
    }

    @Override
    public String getParentName(){
        if(getParentItem()==null){
            return "";
        }
        return getParentItem().getName();
    }

    protected boolean isExecutable(){
        if(condition!= null){
            game.addChoice(condition);
            if(!condition.isAccepted()){
                return false;
            }
        }
        if(!isActivated()){
            return false;
        }
        return true;
    }

    @Override
    public void execute(Encounter encounter) {
        if(parentItem.getItemType().equals(ItemType.SPELL)){
            GameService.getInstance().addUsedSpell((Spell)parentItem);
        }

    }
}
