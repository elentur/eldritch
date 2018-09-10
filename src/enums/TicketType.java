package enums;

import utils.ResourceUtil;

public enum TicketType {
    SHIP("${ship}"),
    TRAIN("${train}");

    private String key;

    TicketType(String key) {
        this.key = key;
    }

    public String getText() {
        return ResourceUtil.get(key,this.getClass());
    }
}
