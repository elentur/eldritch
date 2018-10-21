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

@Getter
@Log
public class GainArtifact extends Effect {
    private final ItemType itemType;
    private final Investigator investigator;
    private  Artifact artifact;

    public GainArtifact(ItemType itemType, Investigator investigator) {
        super(EffectTyps.GAIN_ARTIFACT);
        this.itemType = itemType;
        this.investigator = investigator;
        this.artifact = null;
    }

    public GainArtifact(Artifact artifact, Investigator investigator) {
        super(EffectTyps.GAIN_ARTIFACT);
        this.itemType = artifact.getItemType();
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
            switch (itemType) {
                case ANY:
                    artifact = GameService.getInstance().getArtifacts().draw();
                    break;
                default:
                    artifact = GameService.getInstance().getArtifacts().getByItemType(itemType);
                    break;
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
        if (itemType == null) {
            return ResourceUtil.get("${gain}", "effect", investigator.getName(), ResourceUtil.get("${nothing}", "effect"));
        }
        return ResourceUtil.get("${gain}", "effect", investigator.getName(), ResourceUtil.get("${random_artifact}", "effect", itemType.getText()));

    }
}
