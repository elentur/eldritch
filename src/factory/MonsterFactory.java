package factory;


import lombok.extern.java.Log;
import model.Item.monsters.Monster;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


@Log
public class MonsterFactory {

    List<Monster> monster=null;

    public List<Monster> getMonster() {
        if (monster == null) {
            File f = new File("./src/model/item/monsters");
            monster = new ArrayList<>();
            for (String name: f.list()){
                try {
                    Monster m = (Monster) Class.forName("model.Item.monsters."+name.replace(".java","")).newInstance();
                    monster.add(m);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        return monster;
    }
}
