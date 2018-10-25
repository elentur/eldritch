package model.effects;


import Service.GameService;
import enums.EffectTyps;
import enums.FieldID;
import enums.ItemType;
import gamemechanics.choice.Choice;
import gamemechanics.choice.InvestigatorChoice;
import gamemechanics.choice.MonsterChoice;
import gamemechanics.choice.TradeChoice;
import lombok.Getter;
import lombok.Setter;
import model.Effect;
import model.Item.Investigator;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Trade extends Effect {
    private final int tradeMode;
    private  FieldID fieldID;
    private Investigator investigator;
    @Setter
    private ItemType itemType = ItemType.ITEM;


    public Trade(Investigator investigator) {
        super(EffectTyps.TRADE);
        this.investigator = investigator;
        tradeMode=0;
    }

    public Trade(Investigator investigator,FieldID fieldID,int tradeMode) {
        super(EffectTyps.TRADE);
        this.investigator = investigator;
        this.fieldID = fieldID;
        this.tradeMode =tradeMode;
    }

    @Override
    public void execute() {
        if (isExecuted()) {
            return;
        }
        super.execute();
        if (!isAccepted()) return;
        List<Investigator> investigators;
        if(fieldID==null) {
            investigators = new ArrayList<>(GameService.getInstance().getFieldOfInvestigator(investigator).getInvestigators());
        }else{
            investigators = new ArrayList<>(GameService.getInstance().getGameBoard().getField(fieldID).getInvestigators());
        }
        investigators.remove(investigator);
        if (investigators.size() == 1) {
            TradeChoice tradeChoice = new TradeChoice(investigator, investigators.get(0), false,tradeMode,itemType);
            GameService.getInstance().addChoice(tradeChoice);
        } else if (investigators.size() > 1) {
            InvestigatorChoice choice = new InvestigatorChoice(investigators, true);
            GameService.getInstance().addChoice(choice);
            if (!choice.getSelectedInvs().isEmpty()) {
                TradeChoice tradeChoice = new TradeChoice(investigator, choice.getSelectedInvs().get(0), false,tradeMode,itemType);
                GameService.getInstance().addChoice(tradeChoice);
            }


        }
    }

    @Override
    public String getText() {
        return ResourceUtil.get("${trade}", "effect");


    }


}
