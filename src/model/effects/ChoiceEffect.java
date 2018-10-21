package model.effects;


import Service.GameService;
import enums.EffectTyps;
import gamemechanics.choice.Choice;
import lombok.Getter;
import model.Effect;

@Getter
public class ChoiceEffect extends Effect {


    private final Choice choice;

    public ChoiceEffect(Choice choice) {
        super(EffectTyps.CHOICE_EFFECT);
        this.choice = choice;
    }


    @Override
    public void execute() {
        if(isExecuted()){
            return;
        }
        init();
        if(!isAccepted()) return;
        GameService.getInstance().addChoice(choice);


    }

    @Override
    public String getText() {

        return "";
    }
}
