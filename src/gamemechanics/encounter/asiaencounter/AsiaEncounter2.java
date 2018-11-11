package gamemechanics.encounter.asiaencounter;

import Service.GameService;
import enums.ConditionType;
import enums.EffectTyps;
import enums.TestType;
import gamemechanics.choice.MonsterChoice;
import gamemechanics.encounter.AsiaEncounter;
import gamemechanics.encounter.CombatEncounter;
import model.Effect;
import model.Field;
import model.Item.Monster;
import model.effects.*;

import java.util.ArrayList;
import java.util.List;

public class AsiaEncounter2 extends AsiaEncounter {

    public AsiaEncounter2( ) {
        super("ase_2");

     

    }

    @Override
    public void init() {
        super.init();
        switch (getField().getFieldID()){
            case TOKYO:
                getEffect()[0][START]=new Improve(TestType.LORE,1,getInvestigator());
                getEffect()[0][PASS]=new NullEffect();
                getEffect()[0][FAIL]=new GainCondition(ConditionType.HALLUCINATIONS, getInvestigator());
                setEncounterPart(0);
                break;
            case SHANGHAI:
                Effect effect = new Effect(EffectTyps.CUSTOM) {

                    @Override
                    public void execute() {
                        super.execute();
                        List<Monster> monsters = new ArrayList<>();
                        for(Field field:GameService.getInstance().getGameBoard().getFields()){
                            monsters.addAll(field.getMonster());
                        }
                        condition = new MonsterChoice(monsters);
                        super.init();
                        if (!isAccepted()) {
                            return;
                        }
                        GameService.getInstance().addEffect(new ChooseSpace(new MoveMonster(  ((MonsterChoice) condition).getSelectedMonster().get(0),
                                GameService.getInstance().getFieldOfInvestigator(getInvestigator()).getFieldID())));
                        GameService.getInstance().addEncounter(new CombatEncounter(((MonsterChoice) condition).getSelectedMonster().get(0),new ArrayList<>(),getInvestigator()));
                    }

                    @Override
                    public String getText() {
                        return "";
                    }
                };
                getEffect()[1][START]=effect;
                getEffect()[1][PASS]=new NullEffect();
                getEffect()[1][FAIL]=new NullEffect();
                setEncounterPart(1);
                break;
            case SYDNEY:
                getEffect()[2][START]=new Improve( TestType.STRENGTH,1,getInvestigator());
                getEffect()[2][PASS]=new NullEffect();
                getEffect()[2][FAIL]=new GainCondition(ConditionType.DETAINED, getInvestigator());
                setEncounterPart(2);
                break;
        }
        getTestType()[0] = TestType.WILL;
        getMod()[0]=0;
        getTestType()[1] = TestType.NONE;
        getMod()[1]=0;
        getTestType()[2] = TestType.INFLUENCE;
        getMod()[2]=0;
    }

}
