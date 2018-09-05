package enums;

import utils.ResourceUtil;

public enum PhaseTypes {
    ACTION("${action_phase}"),
    ENCOUNTER("${encounter_phase}"),
    MYTHOS("${mythos_phase}");

    private String key;

    PhaseTypes(String key) {
        this.key = key;
    }

    public String getText() {
        return ResourceUtil.get(key,this.getClass());
    }


}
