package enums;

import utils.ResourceUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum ItemType {
    NONE("", Collections.singletonList("")),
    TRINKET_WEAPON("${trinketWeapon}", Arrays.asList("Trinket","Weapon")),
    ITEM("${item}", Collections.singletonList("Item")),
    ITEM_WEAPON("${itemWeapon}", Arrays.asList("Item","Weapon")),
    ITEM_WEAPON_MAGICAL("${itemWeaponMagical}", Arrays.asList("Item","Weapon","Magical")),
    ITEM_TOME("${itemTome}", Arrays.asList("Item","Tome")),
    ITEM_TOME_MAGICAL("${itemTomeMagical}", Arrays.asList("Item","Tome","Magical")),
    ALLEY("${alley}", Collections.singletonList("Alley")),
    INCANTATION("${incantation}", Collections.singletonList("Incantation")),
    ENCOUNTER("${encounter}", Collections.singletonList("encounter")),
    ASSET("${asset}", Collections.singletonList("asset")),
    ARTIFACT("${artifact}", Collections.singletonList("artifact")),
    INVESTIGATOR("${investigator}", Collections.singletonList("Investigator") ),
    MONSTER("${monster}", Collections.singletonList("Monster") ),
    SPELL("${spell}", Collections.singletonList("Spell")  ),
    TOKEN("${token}", Collections.singletonList("Token")  ),
    CLUE_TOKEN("${clue_token}", Collections.singletonList("Clue")  ),
    GATE_TOKEN("${gate_token}", Collections.singletonList("Gate")  ),
    RUMOR_TOKEN("${rumor_token}", Collections.singletonList("Rumor")  ),
    MYSTERY_TOKEN("${mytsery_token}", Collections.singletonList("Mystery")  ),
    ELDRITCH_TOKEN("${eldritch_token}", Collections.singletonList("Eldritch")  ),
    EXPEDITION_TOKEN("${expedition_token}", Collections.singletonList("Expedition")  );
    private String key;
    private List<String> parts;

    private ItemType(String key, List<String> parts) {
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