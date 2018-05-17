package model.Item;

import enums.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public abstract class ItemBonus implements Bonus{
    protected Item parentItem;
    protected boolean active = true;
    protected RangeType range = RangeType.SELF;
    protected FieldType field = FieldType.ALL;
    protected TestTyp test = TestTyp.NONE;
    protected SituationTyp situation = SituationTyp.ALL;
    protected EventTimeType eventTime = EventTimeType.NONE;
    protected List<SpellConsequence> consequence = new ArrayList<>();

    @Override
    public String getParentName(){
        if(getParentItem()==null){
            return "";
        }
        return getParentItem().getName();
    }



}
