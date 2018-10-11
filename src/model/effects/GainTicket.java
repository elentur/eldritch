package model.effects;


import enums.EffectTyps;
import enums.TicketType;
import lombok.Getter;
import model.Effect;
import model.Item.Investigator;
import utils.ResourceUtil;

@Getter
public class GainTicket extends Effect {
    private final int value;
    private final TicketType ticketType;
    private Investigator investigator;

    public GainTicket(TicketType ticketType, int value, Investigator investigator) {
        super(EffectTyps.GAIN_TICKET);
        this.value = value;
        this.investigator = investigator;
        this.ticketType = ticketType;
    }

    @Override
    public void execute() {
        super.execute();
        switch (ticketType) {
            case SHIP:
                investigator.addShipTicket(value);
                break;
            case TRAIN:
                investigator.addTrainTicket(value);
                break;
        }


    }

    @Override
    public String getText() {
        if (ticketType == null || value == 0) {
            return ResourceUtil.get("${gain}", "effect", investigator.getName(),ResourceUtil.get("${nothing}", "effect") );
        }

            return ResourceUtil.get("${gain}", "effect", investigator.getName(),value + " " + ticketType.getText() );


    }
}
