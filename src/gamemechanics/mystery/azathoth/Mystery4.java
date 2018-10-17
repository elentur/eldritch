package gamemechanics.mystery.azathoth;

import Service.GameService;
import enums.OldOnes;
import enums.SpendType;
import enums.YesNo;
import gamemechanics.Mystery;
import gamemechanics.choice.YesNoChoice;
import gamemechanics.encounter.mysterieencounter.azathoth.MysteryEncounter1;
import model.Effect;
import model.Field;
import model.Item.Artifact;
import model.Item.Investigator;
import model.Item.Item;
import model.Item.token.EldritchToken;
import model.effects.And;
import model.effects.Discard;
import model.effects.SpawnEldritchToken;
import model.effects.Spend;
import oldVersion.gameBuild.Game;
import oldVersion.gui.GamesScreen;
import utils.ResourceUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class Mystery4 extends Mystery {


    public Mystery4() {
        super(4, OldOnes.AZATHOTH);
    }

    @Override
    public void init() {
    Artifact artifact = (Artifact) GameService.getInstance().getArtifacts().get("&massaDiRequiemPerShuggay");
        if(artifact!=null){
            GameService.getInstance().getArtifacts().addItem(0,artifact);
        }
    }

    @Override
    public boolean isFinished() {
        Investigator inv=null;
        Item artifact=null;
        for(Investigator investigator: GameService.getInstance().getInvestigators()){
             artifact = investigator.getInventory().getItems().get("&massaDiRequiemPerShuggay");
            if(artifact!=null){
                inv=investigator;
                break;
            }
        }
        if(inv.getClues().size()< GameService.getInstance().getInvestigators().size()){
            return false;
        }
        Effect effect1 = new Spend(SpendType.CLUE,GameService.getInstance().getInvestigators().size(),inv);
        Effect effect2 = new Discard(artifact);
        Effect effect = new And(effect1,effect2);
        YesNoChoice choice =new YesNoChoice(ResourceUtil.get("${do_you_want}","ui"),effect.getText(), Collections.singletonList(effect),null);
       GameService.getInstance().addChoice(choice );

        return choice.isAccepted();
    }
}
