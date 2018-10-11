package factory;


import container.ItemContainer;
import container.ItemStack;
import container.RandomItemStack;
import lombok.extern.java.Log;
import model.Item.Monster;

import java.io.File;


@Log
public class MonsterFactory {

    private static ItemContainer<Monster> monster=null;

    public static ItemStack<Monster> getMonster() {
        if (monster == null) {
            File f = new File("./src/model/item/monsters");
            monster = new ItemContainer<>();
            for (String name: f.list()){
                try {
                    Monster m = (Monster) Class.forName("model.Item.monsters."+name.replace(".java","")).newInstance();
                    monster.add(m);
                   for(int i =1; i <m.getCount();i++){
                       monster.add(m.getClass().newInstance());
                   }

                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        return new RandomItemStack<>(monster);
    }
}
