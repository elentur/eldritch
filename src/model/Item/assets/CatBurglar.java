package model.Item.assets;

import Service.GameService;
import enums.ItemType;
import gamemechanics.Action;
import gamemechanics.choice.InformationChoice;
import gamemechanics.encounter.Encounter;
import model.Effect;
import model.Item.Asset;
import model.Item.Investigator;
import model.Item.ItemBonus;
import model.effects.AssetFromReserve;
import model.effects.ChoiceEffect;
import model.effects.Discard;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;


public class CatBurglar extends Asset {

    private Function<Encounter,Void> listener;
    public CatBurglar() {
        super(ItemType.ALLEY, 1);
    }

    @Override
    public String getId() {
        return "&catBurglar";
    }

    @Override
    public String getNameId() {
        return "${cat_burglar}";
    }
    @Override
    public Encounter getEncounter() {

        Investigator inv = GameService.getInstance().getEncounteringInvestigator();
        Random r = new Random();
        int i =4;//r.nextInt(6);
        if(i ==0){
            Effect effect =  new Discard(CatBurglar.this);
            effect.setCondition(new InformationChoice( this.getName(),effect.getText(),null));
           return new Action(inv,
                    "cat_burglar",
                  effect
            );
        }else if(i >=4) {

            return new Action(inv,
                    "cat_burglar",
                    new AssetFromReserve(GameService.getInstance().getEncounteringInvestigator(),ItemType.ITEM,ItemType.TRINKET)
            );
        }
        return new Action(inv,
                "cat_burglar",
                new ChoiceEffect(new InformationChoice(getName(),ResourceUtil.get(getNameId().replace("}","_fail}"),"asset"),null))
        );
    }
    @Override
    public List<ItemBonus> createBonus() {
        return new ArrayList<>();
    }


}
