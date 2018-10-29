package enums;

import utils.ResourceUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum ItemType {
    NONE("", Collections.singletonList("")),
    TRINKET("${trinket}", Collections.singletonList("Trinket")),
    TRINKET_WEAPON("${trinket_weapon}", Arrays.asList("Trinket","Weapon")),
    ITEM("${item}", Collections.singletonList("Item")),
    ITEM_WEAPON("${item_weapon}", Arrays.asList("Item","Weapon")),
    ITEM_WEAPON_MAGICAL("${item_weapon_magical}", Arrays.asList("Item","Weapon","Magical")),
    ITEM_TOME("${item_tome}", Arrays.asList("Item","Tome")),
    ITEM_MAGICAL("${item_magical}", Arrays.asList("Item","Magical") ),
    ITEM_TOME_MAGICAL("${item_tome_magical}", Arrays.asList("Item","Tome","Magical")),
    ALLEY("${alley}", Collections.singletonList("Alley")),
    INCANTATION("${incantation}", Collections.singletonList("Incantation")),
    RITUAL("${ritual}", Collections.singletonList("Ritual")),
    ENCOUNTER("${encounter}", Collections.singletonList("encounter")),
    ASSET("${asset}", Collections.singletonList("asset")),
    ARTIFACT("${artifact}", Collections.singletonList("artifacts")),
    INVESTIGATOR("${investigator}", Collections.singletonList("Investigator") ),
    MONSTER("${monster}", Collections.singletonList("Monster") ),
    SPELL("${spell}", Collections.singletonList("Spell")  ),
    TOKEN("${token}", Collections.singletonList("Token")  ),
    CLUE_TOKEN("${clue_token}", Collections.singletonList("Clue")  ),
    GATE_TOKEN("${gate_token}", Collections.singletonList("Gate")  ),
    RUMOR_TOKEN("${rumor_token}", Collections.singletonList("Rumor")  ),
    MYSTERY_TOKEN("${mystery_token}", Collections.singletonList("Mystery")  ),
    ELDRITCH_TOKEN("${eldritch_token}", Collections.singletonList("Eldritch")  ),
    EXPEDITION_TOKEN("${expedition_token}", Collections.singletonList("Expedition")  ),
    ANY("${any}",null ),
    CONDITION("${condition}", Collections.singletonList("Condition")  ),
    BLESSED_CONDITION("${blessed_condition}", Collections.singletonList("Blessed Condition")  ),
    MYTHOS("${mythos}", Collections.singletonList("Mythos")  ),
    ANCIENT_ONE("${ancient_one}", Collections.singletonList("Ancient One") ),
    AMNESIA_CONDITION("${amnesia_condition}", Collections.singletonList("Amnesia Condition")  ),
    MYSTERY("${mystery}", Collections.singletonList("Mystery")  ),
    FOCUS_TOKEN("${focus}", Collections.singletonList("Focus")   ),
    SERVICE("${service}", Collections.singletonList("Service")  ),
    SERVICE_TEAMWORK("${service_teamwork}",Arrays.asList("Service","Teamwork")  ),
    CURSED_CONDITION("${cursed_condition}", Collections.singletonList("Cursed Condition") ),
    ITEM_MAGICAL_TEAMWORK("${item_magical_teamwork}", Arrays.asList("Item","Magical","Teamwork") );

    private String key;
    private List<String> parts;

    private ItemType(String key, List<String> parts) {
        this.key = key;
       this.parts = parts;
    }

    public String getText() {
        return ResourceUtil.get(key,this.getClass());
    }


    public List<String> getParts(){
         return parts;
    }

    public boolean equalsWithAny(ItemType o){
        return o.equals(this) || this.equals(ANY);
    }

    public boolean equalsWithParts(ItemType o){
        if(o.equals(ANY)){
            return true;
        }
     return this.parts.containsAll(o.parts);

    }


}