package model.effects;


import enums.ChoiceType;
import enums.EffectSelector;
import enums.EffectTyps;
import gamemechanics.choice.Choice;
import gamemechanics.choice.InvestigatorChoice;
import gamemechanics.choice.MonsterChoice;
import lombok.Getter;
import lombok.extern.java.Log;
import model.Effect;
import model.Item.Investigator;
import model.Item.Monster;
import utils.ResourceUtil;

import java.util.Collections;
import java.util.List;

@Getter
@Log
public class Devoured extends Effect {
    private List<Investigator> investigators;


    public Devoured(Investigator inv) {
        super(EffectTyps.DEVOURED);
        this.investigators =Collections.singletonList(inv);

    }

    public Devoured(List<Investigator> investigators) {
        super(EffectTyps.DEVOURED);
        this.investigators =investigators;

    }
    public Devoured(Choice choice) {
        super(EffectTyps.DEVOURED);
        this.condition = choice;
    }

    @Override
    public void init() {
        super.init();
        if (condition != null && condition.getChoiceType().equals(ChoiceType.INVESTIGATOR_CHOICE)) {
            investigators = ((InvestigatorChoice) condition).getSelectedInvs();
        }
    }

    @Override
    public void execute() {
        if(isExecuted()){
            return;
        }
        init();
        super.execute();
        if (!isAccepted()) return;
        log.info(investigators.get(0).getName() + " is  devoured");
       //TODO
    }


    @Override
    public String getText() {
        if(investigators!=null && !investigators.isEmpty()) {
            StringBuilder s = new StringBuilder(investigators.get(0).getName());

            for(int i =1; i< investigators.size();i++){
                s.append(" and " +investigators.get(i).getName());
            }

            return ResourceUtil.get("${devoured}", "effect", s.toString());
        }else{
            return ResourceUtil.get("${devoured}", "effect", EffectSelector.ANY.getText());
        }
    }
}
