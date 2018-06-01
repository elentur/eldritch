package Service;

import model.Item.Investigator;
import model.Item.Monster;

public class EventService {


    public void gainHealth(Investigator inv,int value){
        inv.addHealth(value);
    }
    public void looseHealth(Investigator inv,int value){
        inv.addHealth(-value);
        if(inv.getActualHealth()==0) {
            System.out.println(inv.getFirstName() + " is dead");
        }
    }

    public void gainSanity(Investigator inv,int value){
        inv.addSanity(value);
    }
    public void looseSanity(Investigator inv,int value){
        inv.addSanity(-value);
        if(inv.getActualSanity()==0) {
            System.out.println(inv.getFirstName() + " is dead");
        }
    }

    public void gainHealth(Monster monster, int value){
        monster.addDamage(value);
    }

    public void looseHealth(Monster monster, int value){
        monster.addDamage(-value);
        if(monster.getActualToughness()==0) {
            System.out.println(monster.getName() + " is dead");
        }
    }
}
