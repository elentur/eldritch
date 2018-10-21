package model;

import Service.GameService;
import enums.*;
import gamemechanics.Action;
import lombok.Getter;
import model.Item.Investigator;
import model.effects.*;

@Getter
public class FieldActions {


    private final Action gainFocus;
    private final Action trade;
    private final Action rest;
    private final Action acquireAsset;
    private final Action buyTrain;
    private final Action buyShip;

    private boolean hasTrainTicket;
    private boolean hasShipTicket;
    private boolean hasRestAction;
    private boolean hasAcquireAsset;
    private boolean hasTradeAction;
    private boolean hasFocus;

    public FieldActions(Field field) {
        Investigator inv = GameService.getInstance().getEncounteringInvestigator();
        hasTrainTicket = false;
        hasShipTicket = false;
        hasAcquireAsset = false;
        gainFocus = new Action(inv,
                "gain_focus",
                new GainFocus(1, inv)
        );

        rest = new Action(inv,
                "rest",
                new And(new LooseOrGainHealthSanity(SpendType.HEALTH,1, inv),
                        new LooseOrGainHealthSanity(SpendType.SANITY,1, inv))
        );
        buyShip = new Action(inv,
                "buy_ship",
                new GainTicket(TicketType.SHIP,1,inv )
        );
        buyTrain = new Action(inv,
                "buy_train",
                new GainTicket(TicketType.TRAIN,1,inv )
        );
        acquireAsset = new Action(inv,
                "acquire_asset",
                new NullEffect(),
                new AssetFromReserve(inv),
                new NullEffect(),
                TestType.INFLUENCE,
                0,
                1

        );
        trade = new Action(inv,
                "trade",
                new Trade(inv)
        );


        if ( field.getFieldID().getType().equals(FieldType.CITY)) {
            if (inv.getShipTicket() + inv.getTrainTicket() < 2){
                for (Neighbour neighbour : field.getNeighbours()) {
                    if (neighbour.getPath().equals(PathType.TRAIN)) {
                        hasTrainTicket = true;
                    }
                    if (neighbour.getPath().equals(PathType.SHIP)) {
                        hasShipTicket = true;
                    }
                }
        }
            hasAcquireAsset = field.getMonster().isEmpty();

        }
        hasTrainTicket = hasTrainTicket && !inv.getDoneActions().contains(buyTrain);
        hasShipTicket = hasShipTicket&& !inv.getDoneActions().contains(buyShip);
        hasAcquireAsset = hasAcquireAsset&& !inv.getDoneActions().contains(acquireAsset);
        hasFocus = inv.getFocus().size() < 2 && !inv.getDoneActions().contains(gainFocus);
        hasRestAction = field.getMonster().isEmpty() &&(inv.getActualHealth()<inv.getHealth()|| inv.getActualSanity() < inv.getSanity()) && !inv.getDoneActions().contains(rest);
        hasTradeAction = field.getInvestigators().size() > 1 && !inv.getDoneActions().contains(trade);

    }

    public void buyTrainTicket() {
        GameService.getInstance().addEncounter(buyTrain);
    }

    public void buyShipTicket() {
        GameService.getInstance().addEncounter(buyShip);
    }

    public void acquireAsset() {
        GameService.getInstance().addEncounter(acquireAsset);
    }

    public void rest() {
        GameService.getInstance().addEncounter(rest);
    }

    public void trade() {
        GameService.getInstance().addEncounter(trade);
    }

    public void gainFocus() {

        GameService.getInstance().addEncounter(gainFocus);
    }

}
