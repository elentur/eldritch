package model.effects;


import Service.GameService;
import enums.EffectTyps;
import enums.ItemType;
import lombok.Getter;
import lombok.extern.java.Log;
import model.Effect;
import model.Item.Artifact;
import model.Item.Investigator;
import model.Item.Spell;
import utils.ResourceUtil;

@Getter
@Log
public class GainSpell extends Effect {
    private final ItemType itemType;
    private final Investigator investigator;
    private Spell spell;

    public GainSpell(ItemType itemType, Investigator investigator) {
        super(EffectTyps.GAIN_SPELL);
        this.itemType = itemType;
        this.investigator = investigator;
        this.spell = null;
    }

    public GainSpell(Spell spell, Investigator investigator) {
        super(EffectTyps.GAIN_SPELL);
        this.itemType = spell.getItemType();
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
            switch (itemType) {
                case ANY:
                    spell = GameService.getInstance().getSpells().draw();
                    break;
                default:
                    spell = GameService.getInstance().getSpells().getByItemType(itemType);
                    break;
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
        if (itemType == null) {
            return ResourceUtil.get("${gain}", "effect", investigator.getName(), ResourceUtil.get("${nothing}", "effect"));
        }
        return ResourceUtil.get("${gain}", "effect", investigator.getName(), ResourceUtil.get("${random_spell}", "effect", itemType.getText()));

    }
}
