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

    public List<Monster> getMonster() {
        ObjectMapper mapper = new ObjectMapper();
        List<Monster> monster = new ArrayList<>();
        try {
            File file = new File("./resources/monster/monster.json");
           monster =  mapper.readValue(file,  new TypeReference<List<Monster>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("läuft");
        return monster;
    }
}
