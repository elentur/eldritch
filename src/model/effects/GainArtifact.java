package model.effects;


import Service.GameService;
import enums.EffectTyps;
import enums.ItemType;
import lombok.Getter;
import lombok.extern.java.Log;
import model.Effect;
import model.Item.Artifact;
import model.Item.Investigator;
import utils.ResourceUtil;

import java.util.List;

@Getter
@Log
public class GainArtifact extends Effect {
    private  final List<ItemType> itemType;
    private final Investigator investigator;
    private  Artifact artifact;

    public GainArtifact(List<ItemType> itemType, Investigator investigator) {
        super(EffectTyps.GAIN_ARTIFACT);
        this.itemType = itemType;
        this.investigator = investigator;
        this.artifact = null;
    }

    public GainArtifact(Artifact artifact, Investigator investigator) {
        super(EffectTyps.GAIN_ARTIFACT);
        itemType=null;
        this.artifact = artifact;
        this.investigator = investigator;
    }


    @Override
    public void execute() {
        if(isExecuted()){
            return;
        }
        super.execute();
        if (!isAccepted()) return;
        if (artifact == null) {
            if (itemType == null) {
                artifact = GameService.getInstance().getArtifacts().draw();
            } else {
                artifact = GameService.getInstance().getArtifacts().getByItemType(itemType);
            }
        }
        for(Effect effect : artifact.getDrawEffects()){
            GameService.getInstance().addEffect(effect);
        }
            investigator.addToInventory(artifact);


        log.info(itemType.toString());
    }

    @Override
    public String getText() {
        if (itemType == null && artifact == null) {
            return ResourceUtil.get("${gain}", "effect", investigator.getName(), ResourceUtil.get("${nothing}", "effect"));
        } else if (artifact == null) {
            StringBuilder s = new StringBuilder(itemType.get(0).getText());

            for(int i =1; i< itemType.size();i++){
                s.append(" or " +itemType.get(i));
            }

            return ResourceUtil.get("${gain}", "effect", investigator.getName(), ResourceUtil.get("${random_artifact}", "effect", s.toString() ));
        } else {
            return ResourceUtil.get("${gain}", "effect", investigator.getName(), ResourceUtil.get("${random_artifact}", "effect", artifact.getName()));
        }
    }
}
