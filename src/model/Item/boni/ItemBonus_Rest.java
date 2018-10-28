package model.Item.boni;

import Service.GameService;
import enums.*;
import gamemechanics.encounter.Encounter;
import lombok.Getter;
import lombok.Setter;
import model.Effect;
import model.Item.Bonus;
import model.Item.Item;
import model.Item.ItemBonus;
import utils.ResourceUtil;

import java.util.List;

@Getter
@Setter
public class ItemBonus_Rest extends ItemBonus {
    private List<Encounter> encounters;
    private Effect effects;
    private BonusType bonusType = BonusType.REST;
    private final RangeType rangeType;


    public ItemBonus_Rest(Effect effects,RangeType rangeType, Item parentItem) {
        super(parentItem);
        this.effects = effects;
        this.rangeType=rangeType;
        init();
    }

    public ItemBonus_Rest(List<Encounter> encounters, RangeType rangeType, Item parentItem) {
        super(parentItem);
        this.encounters = encounters;
        this.rangeType=rangeType;
        init();
    }

    private void init() {
        this.test = TestType.ALL;
        this.situation = SituationType.REST;
        this.eventTime = EventTimeType.BEFORE;
        this.passive = true;
    }


    @Override
    public void execute(Encounter encounter) {
        if (!isExecutable()) {
            return;
        }
     if(effects!=null){
         GameService.getInstance().addEffect(effects);
     }else if(encounters!=null){
         for(Encounter encounter1 : encounters){
             //TODO
             //GameService.getInstance().addEncounter(encounter1);
         }
     }
    }



    @Override
    public String getText() {
        return ResourceUtil.get("${rest}", Bonus.class);
    }
}
