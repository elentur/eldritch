package model.effects;


import Service.GameService;
import enums.EffectTyps;
import enums.SpendType;
import gamemechanics.choice.Choice;
import gamemechanics.choice.InvestigatorChoice;
import gamemechanics.choice.MonsterChoice;
import lombok.Getter;
import model.Effect;
import model.Item.Investigator;
import model.Item.Monster;
import utils.ResourceUtil;

@Getter
public class LooseOrGainHealthSanity extends Effect {
    public final static int START_INVESTIGATOR = 1;
    public final static int ACTIVE_INVESTIGATOR = 2;
    public final static int ENCOUNTERING_INVESTIGATOR = 3;

    private final SpendType spendType;
    private final int value;
    private Investigator investigator;
    private Monster monster;
    private int invType;

    public LooseOrGainHealthSanity(SpendType spendType, int value, int invType) {
        super(EffectTyps.LOOSE_OR_GAIN_HEALTH_SANITY);
        this.spendType = spendType;
        this.value = value;
        this.invType = invType;
    }

    public LooseOrGainHealthSanity(SpendType spendType, int value, Investigator investigator) {
        super(EffectTyps.LOOSE_OR_GAIN_HEALTH_SANITY);
        this.spendType = spendType;
        this.value = value;
        this.investigator = investigator;
    }

    public LooseOrGainHealthSanity(SpendType spendType, int value, Monster monster) {
        super(EffectTyps.LOOSE_OR_GAIN_HEALTH_SANITY);
        this.spendType = spendType;
        this.value = value;
        this.monster = monster;
    }

    public LooseOrGainHealthSanity(SpendType spendType, int value, Choice choice) {
        super(EffectTyps.LOOSE_OR_GAIN_HEALTH_SANITY);
        this.spendType = spendType;
        this.value = value;
        this.condition = choice;
    }

    @Override
    public void execute() {
        if(isExecuted()){
            return;
        }
        super.execute();
        if(!isAccepted()) return;
        switch (spendType) {
            case HEALTH:
                if (investigator != null) {
                    investigator.addHealth(value);
                } else if (monster != null) {
                    if(monster.getActualToughness()>0) {
                        monster.addDamage(value);
                    }
                }
                break;
            case SANITY:
                investigator.addSanity(value);
                break;
            default:
                break;
        }

    }

    @Override
    public String getText() {
        init();
        String name = investigator != null ? investigator.getName() : "The " + monster.getName();
        if (spendType == null || value == 0) {
            return ResourceUtil.get("${loose}", "effect", name, ResourceUtil.get("${nothing}", "effect"));
        }
        if (value < 0) {
            return ResourceUtil.get("${loose}", "effect", name, Math.abs(value) + " " + spendType.getText());
        } else {
            return ResourceUtil.get("${gain}", "effect", name, value + " " + spendType.getText());
        }
    }

    @Override
    public void init() {
        super.init();
        if (condition != null) {
            switch (condition.getChoiceType()) {
                case INVESTIGATOR_CHOICE:
                    investigator = ((InvestigatorChoice) condition).getSelectedInvs().get(0);
                    break;
                case MONSTER_CHOICE:
                    monster = ((MonsterChoice) condition).getSelectedMonster().get(0);
                    break;
            }
        }
        if(invType!=0){
            switch (invType){
                case START_INVESTIGATOR:
                    investigator = GameService.getInstance().getStartInvestigator();
                    break;
                case ACTIVE_INVESTIGATOR:
                    investigator = GameService.getInstance().getActiveInvestigator();
                    break;
                case ENCOUNTERING_INVESTIGATOR:
                    investigator = GameService.getInstance().getEncounteringInvestigator();
                    break;

            }
        }
    }

}
