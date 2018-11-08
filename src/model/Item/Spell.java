package model.Item;

import Service.GameService;
import container.ItemStack;
import container.Result;
import enums.ItemType;
import enums.PhaseTypes;
import gamemechanics.Phases;
import gamemechanics.choice.InformationChoice;
import javafx.beans.value.ChangeListener;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import model.Effect;
import utils.RNG;
import utils.ResourceUtil;

import java.util.*;

@Getter
@Setter
@EqualsAndHashCode
public abstract class Spell implements Item {
    public static final int NUMBER_OF_DIFFERENT_SPELLS = 4;
    private ItemStack stack;
    public String uniqueId = UUID.randomUUID().toString();
    private ItemType type;
    private boolean used;
    private ChangeListener<Boolean> listener;


    public List<ItemBonus> getBonus() {
        return bonus;
    }

    private List<ItemBonus> bonus;

    public Spell(ItemType type ){
        this.type = type;
        this.bonus= createBonus();

    }


    public String getName(){
        return  ResourceUtil.get(getNameId(),"spell");
    }

    public String toString() {
        return getName();
    }

    @Override
    public ItemType getSubType() {
        return type;
    }

    @Override
    public ItemType getItemType() {
        return ItemType.SPELL;
    }

    @Override
    public void setStack(ItemStack itemStack){
        stack=itemStack;
    }
    @Override
    public void discard(){

        stack.discard(this);
    }
    @Override
    public Spell draw(){
        return (Spell) getStack().draw();
    }
    @Override
    public void executeReckoning(Investigator inv, boolean autoFail){}

    public String getInfoText() {
        StringBuilder infoText = new StringBuilder();
        for(Bonus bonus: getBonus()){
            infoText.append("Bonus: ").append(bonus.getText()).append("\n");
        }

        String key= getTextKey();
        if(key != null){
            if(key.equals("action")){
                infoText.append("Action: ");
            }
            infoText.append(ResourceUtil.get(getNameId().replace("}","_start}"),key));
        }


        return infoText.toString();
    }

    @Override
    public List<Effect> getDrawEffects(Investigator investigator) {
        return new ArrayList<>();
    }

    protected abstract String getTextKey();


    public abstract List<SpellConsequence> getConsequence(Result result);

    public void executeConsequences(Result result) {
        List<SpellConsequence> consequences = getConsequence(result);
        int i = RNG.getInt(consequences.size());
        SpellConsequence consequence = consequences.get(i);
        InformationChoice choice = new InformationChoice(ResourceUtil.get("${spell_consequence}","ui"),consequence.getText(result.getNumberOfSuccess()), Collections.singletonList(consequence.getEffect(result.getNumberOfSuccess())));
       GameService.getInstance().addChoice(choice);
    }

    protected void setUsed(boolean val){
        if(listener == null){
            Phases p =GameService.getInstance().getPhases();
            listener = (ob,o,n)->{
                               if(p.getActualPhase().equals(PhaseTypes.MYTHOS) && p.getUpdate().getValue()){
                    setUsed(false);
                    p.getUpdate().setValue(false);
                }
            };
            p.getUpdate().addListener(listener);
        }
         used = val;
    }

}
