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
    private final Artifact artifact;

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
        super.execute();
        if (!isAccepted()) return;
        if (artifact == null) {
            switch (itemType) {
                case ANY:
                    investigator.getInventory().add(GameService.getInstance().getArtifacts().draw());
                    break;
                default:
                    investigator.getInventory().add(GameService.getInstance().getArtifacts().getByItemType(itemType));
                    break;
            }
        } else {
            investigator.getInventory().add(artifact);
        }

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
