package model.effects;


import Service.GameService;
import enums.EffectTyps;
import gamemechanics.choice.InvestigatorChoice;
import gamemechanics.choice.TradeChoice;
import lombok.Getter;
import model.Effect;
import model.Item.Investigator;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Trade extends Effect {
    private  Investigator investigator;

    public Trade(Investigator investigator) {
        super(EffectTyps.TRADE);
        this.investigator = investigator;
    }

    @Override
    public void execute() {
        if(isExecuted()){
            return;
        }
        super.execute();
        if(!isAccepted()) return;
        List<Investigator> investigators =new ArrayList<>(GameService.getInstance().getFieldOfInvestigator(investigator).getInvestigators());
        investigators.remove(investigator);
       if(investigators.size()==1){
           TradeChoice tradeChoice= new TradeChoice(investigator,investigators.get(0),false);
           GameService.getInstance().addChoice(tradeChoice);
       }else if(investigators.size()>1){
            InvestigatorChoice choice = new InvestigatorChoice(investigators,true);
        GameService.getInstance().addChoice(choice);
        if(!choice.getSelectedInvs().isEmpty()){
            TradeChoice tradeChoice= new TradeChoice(investigator,choice.getSelectedInvs().get(0),false);
            GameService.getInstance().addChoice(tradeChoice);
        }



       }
    }

    @Override
    public String getText() {
            return ResourceUtil.get("${trade}","effect"   ) ;


    }
}
