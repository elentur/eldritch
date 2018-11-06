package gamemechanics.choice;


import Service.GameService;
import enums.ChoiceType;
import enums.FieldID;
import expetions.InvestigatorChoiceException;
import expetions.ItemChoiceException;
import lombok.Getter;
import lombok.Setter;
import model.Effect;
import model.Field;
import model.Item.Investigator;
import model.Item.Item;
import model.effects.Discard;
import utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

public class InvestigatorChoice extends Choice{
    private final List<Investigator> investigators;
    @Getter
    private final List<Investigator> selectedInvs;
    @Setter
    @Getter
    private  int number;
    public InvestigatorChoice(FieldID fieldID, int number){
        super(ChoiceType.INVESTIGATOR_CHOICE, ResourceUtil.get("${investigator_choice}","ui"),"");
        Field field = GameService.getInstance().getGameBoard().getField(fieldID);
        this.investigators = field.getInvestigators();
        this.number = number;
        this.selectedInvs = new ArrayList<>();
    }
    public InvestigatorChoice(List<Investigator> investigators,int number){
        super(ChoiceType.INVESTIGATOR_CHOICE, ResourceUtil.get("${investigator_choice}","ui"),"");
        this.investigators = investigators;
        this.number = number;
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
        if(investigators.isEmpty()){
            throw new InvestigatorChoiceException(ResourceUtil.get("${no_investigator_for_choice}","exception"));
        }
        return investigators;
    }



    public void addSelection(Investigator investigator) {
        this.selectedInvs.add(investigator);

    }

    public void choose(List<Investigator> chosen) {
        if(number >0 && chosen.size()>number){
            throw new InvestigatorChoiceException(ResourceUtil.get("${investigator_number_to_low}","exception",chosen.size()+"",number+""));

        }
        selectedInvs.clear();
        selectedInvs.addAll(chosen);
        accepted=true;
    }
}
