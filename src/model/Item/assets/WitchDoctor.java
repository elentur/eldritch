package model.Item.assets;

import Service.GameService;
import enums.ItemType;
import enums.RangeType;
import enums.SpendType;
import model.Effect;
import model.Item.Asset;
import model.Item.Investigator;
import model.Item.ItemBonus;
import model.Item.boni.ItemBonus_Rest;
import model.effects.Discard;
import model.effects.LooseOrGainHealthSanity;
import model.effects.NullEffect;
import model.effects.Or;

import java.util.ArrayList;
import java.util.List;


public class WitchDoctor extends Asset {

    public WitchDoctor() {
        super(ItemType.ALLEY, 3);
    }

    @Override
    public String getId() {
        return "&witchDoctor";
    }

    @Override
    public String getNameId() {
        return "${witch_doctor}";
    }

    @Override
    public List<ItemBonus> createBonus() {
        List<ItemBonus> boni = new ArrayList<>();


        return boni;
    }

    @Override
    public List<ItemBonus> getBonus() {
        List<ItemBonus> boni = new ArrayList<>();
        Investigator inv = GameService.getInstance().getEncounteringInvestigator();
        Effect effect = new NullEffect();
        if(inv.getHealth() -inv.getActualHealth()>=2){
            if(!inv.getInventory().getItemsWidthTypeFilter(item->item.getSubType().equals(ItemType.CURSED_CONDITION)).isEmpty()){
                effect = new Or(
                        new LooseOrGainHealthSanity(SpendType.HEALTH,1,inv),
                        new Discard(inv.getInventory().getItemsWidthTypeFilter(item->item.getSubType().equals(ItemType.CURSED_CONDITION)).get(0)));
            }else {
                effect =  new LooseOrGainHealthSanity(SpendType.HEALTH,1,inv);
            }
        }else {
            if(!inv.getInventory().getItemsWidthTypeFilter(item->item.getSubType().equals(ItemType.CURSED_CONDITION)).isEmpty()){
                new Discard(inv.getInventory().getItemsWidthTypeFilter(item->item.getSubType().equals(ItemType.CURSED_CONDITION)).get(0));
            }else{
                return boni;
            }

        }


        ItemBonus_Rest bonus1 = new ItemBonus_Rest(effect,RangeType.LOCAL,this);
        boni.add(bonus1);
        return super.getBonus();
    }
}
