package gamemechanics.choice;


import enums.ChoiceType;
import lombok.Getter;
import model.Effect;
import model.Field;
import model.Item.Investigator;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

public class InvestigatorChoice extends Choice{
    private final List<Investigator> investigators;
    @Getter
    private final List<Investigator> selectedInvs;
private final boolean singleSelect;
    public InvestigatorChoice(Field field,boolean singleSelect){
        super(ChoiceType.INVESTIGATOR_CHOICE, ResourceUtil.get("${investigator_choice}","ui"),"");
        this.investigators = field.getInvestigators();
        this.singleSelect = singleSelect;
        this.selectedInvs = new ArrayList<>();
    }
    public InvestigatorChoice(List<Investigator> investigators,boolean singleSelect){
        super(ChoiceType.INVESTIGATOR_CHOICE, ResourceUtil.get("${investigator_choice}","ui"),"");
        this.investigators = investigators;
        this.singleSelect = singleSelect;
        this.selectedInvs = new ArrayList<>();
    }


    private void executeEffects(List<Effect> effects) {
        if(effects==null){
            return;
        }
        for(Effect effect:effects){
            effect.execute();
        }
    }


    public List<Investigator> getInvestigators() {
        return investigators;
    }

    public boolean isSingleSelect() {
        return singleSelect;
    }

    public void addSelection(Investigator investigator) {
        this.selectedInvs.add(investigator);
       if(singleSelect) {
           getChoiceTakenProperty().setValue(true);
       }
    }

}
