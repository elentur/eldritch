package model.effects;


import Service.GameService;
import enums.EffectTyps;
import gamemechanics.choice.Choice;
import gamemechanics.choice.InvestigatorChoice;
import gamemechanics.choice.ItemChoice;
import gamemechanics.choice.MonsterChoice;
import lombok.Getter;
import model.Effect;
import model.Item.Item;
import model.Item.token.GateToken;
import utils.ResourceUtil;

@Getter
public class Discard extends Effect {
    private Item item;

    public Discard(Item item) {
        super(EffectTyps.DISCARD);
        this.item = item;
    }

    public Discard(Choice condition) {
        super(EffectTyps.DISCARD);
        this.condition = condition;
    }


    @Override
    public void execute() {
        init();
        super.execute();
        if (!isAccepted()) return;
        item.discard();

    }

    @Override
    public String getText() {

        return ResourceUtil.get("${discard}", "effect", item.getName());
    }

    @Override
    public void init() {
        super.init();
        if (condition != null) {
            item = ((ItemChoice) condition).getChosenItems().get(0);
        }

    }
}
