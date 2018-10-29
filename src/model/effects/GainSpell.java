package model.effects;


import Service.GameService;
import enums.EffectTyps;
import enums.ItemType;
import lombok.Getter;
import lombok.extern.java.Log;
import model.Effect;
import model.Item.Investigator;
import model.Item.Spell;
import utils.ResourceUtil;

import java.util.List;

@Getter
@Log
public class GainSpell extends Effect {
    private final List<ItemType> itemType;
    private final Investigator investigator;
    private Spell spell;

    public GainSpell(List<ItemType> itemType, Investigator investigator) {
        super(EffectTyps.GAIN_SPELL);
        this.itemType = itemType;
        this.investigator = investigator;
        this.spell = null;
    }

    public GainSpell(Spell spell, Investigator investigator) {
        super(EffectTyps.GAIN_SPELL);
        this.itemType = null;
        this.spell = spell;
        this.investigator = investigator;
    }


    @Override
    public void execute() {
        if(isExecuted()){
            return;
        }
        super.execute();
        if (!isAccepted()) return;
        if (spell == null) {
            if (itemType !=null && itemType.isEmpty()) {
                spell = GameService.getInstance().getSpells().draw();
            } else {
                spell = GameService.getInstance().getSpells().getByItemType(itemType);
            }
        }
        for(Effect effect : spell.getDrawEffects()){
            GameService.getInstance().addEffect(effect);
        }
            investigator.addToInventory(spell);


        log.info(itemType.toString());
    }

    @Override
    public String getText() {
        if (itemType == null && spell == null) {
            return ResourceUtil.get("${gain}", "effect", investigator.getName(), ResourceUtil.get("${nothing}", "effect"));
        } else if (spell == null) {
            StringBuilder s = new StringBuilder(itemType.get(0).getText());

            for(int i =1; i< itemType.size();i++){
                s.append(" or " +itemType.get(i).getText());
            }

            return ResourceUtil.get("${gain}", "effect", investigator.getName(), ResourceUtil.get("${random_spell}", "effect", s.toString() ));
        } else {
            return ResourceUtil.get("${gain}", "effect", investigator.getName(), ResourceUtil.get("${random_spell}", "effect", spell.getName()));
        }
    }
}
