package model.effects;


import Service.GameService;
import enums.EffectTyps;
import enums.ItemType;
import lombok.Getter;
import lombok.extern.java.Log;
import model.Effect;
import model.Item.Asset;
import model.Item.Investigator;
import utils.ResourceUtil;

import java.util.List;

@Getter
@Log
public class GainAsset extends Effect {
    private final List<ItemType> itemType;
    private final Investigator investigator;
    private Asset asset;

    public GainAsset(List<ItemType> itemType, Investigator investigator) {
        super(EffectTyps.GAIN_ASSET);
        this.itemType = itemType;
        this.investigator = investigator;
        this.asset = null;
    }

    public GainAsset(Asset asset, Investigator investigator) {
        super(EffectTyps.GAIN_ASSET);
        this.itemType = null;
        this.investigator = investigator;
        this.asset = asset;
    }

    @Override
    public void execute() {
        if (isExecuted()) {
            return;
        }
        super.execute();
        if (!isAccepted()) return;
        if (asset == null) {
            if (itemType == null) {
                asset = GameService.getInstance().getAssets().draw();
            } else {
                asset = GameService.getInstance().getAssets().getByItemType(itemType);
            }

            log.info(itemType.toString());
        }
        investigator.addToInventory(asset);
        for (Effect effect : asset.getDrawEffects()) {
            GameService.getInstance().addEffect(effect);
        }

        log.info(asset.getName());


    }

    @Override
    public String getText() {
        if (itemType == null && asset == null) {
            return ResourceUtil.get("${gain}", "effect", investigator.getName(), ResourceUtil.get("${nothing}", "effect"));
        } else if (asset == null) {
            StringBuilder s = new StringBuilder(itemType.get(0).getText());

                for(int i =1; i< itemType.size();i++){
                    s.append(" or " +itemType.get(i).getText());
                }

            return ResourceUtil.get("${gain}", "effect", investigator.getName(), ResourceUtil.get("${random_asset}", "effect", s.toString() ));
        } else {
            return ResourceUtil.get("${gain}", "effect", investigator.getName(), ResourceUtil.get("${random_asset}", "effect", asset.getName()));
        }
    }
}
