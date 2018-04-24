package enums;

import utils.ResourceUtil;

import java.util.Arrays;
import java.util.List;

public enum ItemTyp {
    NONE("", Arrays.asList("")),
    TRINKET_WEAPON("&trinketWeapon", Arrays.asList("Trinket","Weapon")),
    ITEM_WEAPON("&itemWeapon", Arrays.asList("Item","Weapon")),
    ITEM_TOME("&itemTome", Arrays.asList("Item","Tome")),
    INCANTATION("&incantation", Arrays.asList("Incantation"));
    private String key;
    private List<String> parts;

    private ItemTyp(String key,List<String> parts) {
        this.key = key;
        parts = parts;
    }

    public String getText() {
        return ResourceUtil.get(key,this.getClass());
    }


    public List<String> getParts(){
         return parts;
    }
}