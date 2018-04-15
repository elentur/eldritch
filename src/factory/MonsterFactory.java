package factory;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import model.Monster;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Log
public class MonsterFactory {

    List<Monster> monster = new ArrayList<>();

    public List<Monster> getMonster() {

        ObjectMapper mapper = new ObjectMapper();
        if (monster.isEmpty()) {
            try {
                File file = new File("./resources/monster/monster.json");
                log.info("Beginn unmarschalling monster");
                monster = mapper.readValue(file, new TypeReference<List<Monster>>() {
                });
                log.info("Unmarschalling monster successful");
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        return monster;
    }
}
