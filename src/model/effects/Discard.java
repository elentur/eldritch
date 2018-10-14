package model.effects;


import enums.EffectTyps;
import lombok.Getter;
import model.Effect;
import model.Item.Item;
import model.Item.token.GateToken;
import utils.ResourceUtil;

@Getter
public class Discard extends Effect {
    private final Item item;
    private GateToken token;

    public Discard(Item item) {
        super(EffectTyps.DISCARD);
        this.item=item;
    }



    @Override
    public void execute() {
        init();
        super.execute();
        if(!isAccepted()) return;
        item.discard();

    }

    @Override
    public String getText() {

        return ResourceUtil.get("${discard}","effect",item.getName() );
    }
}
