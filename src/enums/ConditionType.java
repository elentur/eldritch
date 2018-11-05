package enums;

import lombok.Getter;
import utils.ResourceUtil;

public enum ConditionType {

    AMNESIA("${amnesia}",ItemType.AMNESIA_CONDITION),
    BACK_INJURY("${back_injury}",ItemType.BACK_INJURY_CONDITION),
    BANE("${bane}",ItemType.BANE_CONDITION),
    BLESSED("${blessed}",ItemType.BLESSED_CONDITION),
    BOON("${boon}",ItemType.BOON_CONDITIONS),
    CURSED("${cursed}",ItemType.CURSED_CONDITION),
    DARK_PACT("${dark_pact}",ItemType.DARK_PACT),
    DEAL("${deal}",ItemType.DEAL_CONDITION),
    DEPT("${dept}",ItemType.DEPT_CONDITION),
    DETAINED("${detained}",ItemType.DETAINED_CONDITION),
    HALLUCINATIONS("${hallucinations}",ItemType.HALLUCINATIONS_CONDITION),
    INJURY("${injury}",ItemType.INJURY_CONDITION),
    INTERNAL_INJURY("${internal_injury}",ItemType.INTERNAL_INJURY_CONDITION),
    LEG_INJURY("${leg_injury}",ItemType.LEG_INJURY_CONDITION),
    MADNESS("${madness}",ItemType.MADNESS_CONDITION),
    PARANOIA("${paranoia}",ItemType.PARANOIA_CONDITION),
    RESTRICTION("${restriction}",ItemType.RESTRICTION_CONDITION),

    NONE("",ItemType.NONE);









    @Getter
    private final ItemType itemType;
    private String key;

    private ConditionType(String key, ItemType itemType) {
        this.key = key;
        this.itemType =itemType;
    }
    public String getText() {
        return ResourceUtil.get(key,this.getClass());
    }
}
