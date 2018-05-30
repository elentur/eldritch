package model.Item.boni;

import enums.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import model.Item.Item;
import model.Item.SpellConsequence;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public abstract class ItemBonus implements Bonus {
    protected Item parentItem;
    protected boolean active = true;
    protected boolean usable = true;
    protected RangeType range = RangeType.SELF;
    protected FieldType field = FieldType.ALL;
    protected TestType test = TestType.NONE;
    protected SituationType situation = SituationType.ALL;
    protected EventTimeType eventTime = EventTimeType.NONE;
    protected List<SpellConsequence> consequence = new ArrayList<>();
    protected boolean perRound=false;


    @Override
    public void reckoning(){}


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



}
