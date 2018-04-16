package container;

import enums.ItemBonusTyp;
import enums.ItemTyp;
import enums.SituationTyp;
import model.Item.Asset;
import model.Item.Item;
import model.Item.Spell;

import java.util.*;
import java.util.stream.Collectors;


public class ItemContainer<T extends Item> extends ArrayList<T>{

    public ItemContainer(List<T> list){
       super(list);
    }
    public ItemContainer(){
        super();
    }



    public T get(String s) {
        if(s == null){
            return null;
        }
        Optional<T> opt= this.stream().filter(item -> s.equals(item.getId())).findFirst();
        if(opt.isPresent()){
            return opt.get();
        }
        return null;
    }


    public ItemContainer<Asset> getAssetsWidthSituationTyp(SituationTyp situation){
        return new ItemContainer<>(this.stream().filter( Asset.class::isInstance )
                .map( Asset.class::cast ).filter(item-> item.getBonus().stream().filter(bonus->bonus.getSituation().equals(situation)).count()>0).collect(Collectors.toList()));
    }

    public ItemContainer<Spell> getSpellsWidthSituationTyp(SituationTyp situation){
        return new ItemContainer<>(this.stream().filter( Spell.class::isInstance )
                .map( Spell.class::cast ).filter(item-> item.getBonus().stream().filter(bonus->bonus.getSituation().equals(situation)).count()>0).collect(Collectors.toList()));
    }

    public ItemContainer<Item> getItemsWidthSituationTyp(SituationTyp situation) {
        ItemContainer<Item> bonusItems = new ItemContainer<>();
        bonusItems.addAll(getAssetsWidthSituationTyp(situation));
        bonusItems.addAll(getSpellsWidthSituationTyp(situation));
        return bonusItems;
    }
}
