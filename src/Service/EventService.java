package Service;

import enums.SpendType;
import model.Item.Investigator;
import model.Item.Monster;
import model.effects.Loose;

public class EventService {


    public void gainHealth(Investigator inv,int value){
        inv.addHealth(value);
    }
    public void looseHealth(Investigator inv,int value){
        if(value<=0){
            return;
        }
        GameService.getInstance().addEffect(new Loose(SpendType.HEALTH,-value,inv));
        if(inv.getActualHealth()==0) {
            System.out.println(inv.getFirstName() + " is dead");
        }
    }

    public void gainSanity(Investigator inv,int value){
        inv.addSanity(value);
    }
    public void looseSanity(Investigator inv,int value){
        if(value<=0){
            return;
        }
        GameService.getInstance().addEffect(new Loose(SpendType.SANITY,-value,inv));
        if(inv.getActualSanity()==0) {
            System.out.println(inv.getFirstName() + " is dead");
        }
    }

    public void gainHealth(Monster monster, int value){
        monster.addDamage(value);
    }

    public void looseHealth(Monster monster, int value){
        if(value<=0){
            return;
        }
       GameService.getInstance().addEffect(new Loose(SpendType.HEALTH,-value,monster));
        if(monster.getActualToughness()==0) {
            System.out.println(monster.getName() + " is dead");
        }
    }
}
