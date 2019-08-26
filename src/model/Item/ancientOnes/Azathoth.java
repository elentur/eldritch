package model.Item.ancientOnes;

import Service.GameService;
import enums.OldOnes;
import enums.OmenStates;
import enums.SpendType;
import gamemechanics.encounter.Encounter;
import model.Effect;
import model.Item.AncientOne;
import model.Item.Monster;
import model.effects.*;

import java.util.Collections;

public class Azathoth extends AncientOne {

    public Azathoth() {
        super(OldOnes.AZATHOTH, 3, 15);

    }

    @Override
    public Encounter getEncounter() {
        return null;
    }

    @Override
    public String getNameId() {
        return "${azathoth}";
    }

    @Override
    public String getId() {
        return "&azathoth";
    }

    @Override
    public void init() {

        Cultist cultist = new Cultist();
        for (int i = 1; i < cultist.getCount(); i++) {
            GameService.getInstance().getMonsterPool().addItem(new Cultist());
        }
        GameService.getInstance().getMonsterPool().addItem(cultist);
        GameService.getInstance().getMonsterPool().shuffle();
        GameService.getInstance().getOmenTrack().addToken(OmenStates.GREEN_COMET);
        GameService.getInstance().getOmenTrack().updateProperty().addListener((a,b,newValue) -> {
            if(newValue && GameService.getInstance().getOmenTrack().getOmen().equals(OmenStates.GREEN_COMET)) {
                for (int i = 0; i < GameService.getInstance().getOmenTrack().getToken(OmenStates.GREEN_COMET); i++) {
                    GameService.getInstance().addEffect(new AdvanceDoom(1));
                }
            }
        });

        getFirst().add(getBlue().draw());
        getFirst().add(getYellow().draw());
        getFirst().add(getYellow().draw());
        getFirst().add(getGreen().draw());
        Collections.shuffle(getFirst());

        getSecond().add(getBlue().draw());
        getSecond().add(getYellow().draw());
        getSecond().add(getYellow().draw());
        getSecond().add(getYellow().draw());
        getSecond().add(getGreen().draw());
        getSecond().add(getGreen().draw());
        Collections.shuffle(getSecond());

        getThird().add(getYellow().draw());
        getThird().add(getYellow().draw());
        getThird().add(getYellow().draw());
        getThird().add(getYellow().draw());
        getThird().add(getGreen().draw());
        getThird().add(getGreen().draw());
        Collections.shuffle(getThird());

        super.init();



    }

    @Override
    protected void startEndGame() {
        //TODO SPIEL VERLOREN
    }

    private class Cultist extends Monster {


        public Cultist() {
            super(8);
            this.setId("&cultist");
            this.setName("${cultist}");
            this.setWillTest(0);
            this.setHorror(1);
            this.setStrengthTest(0);
            this.setDamage(0);
            this.setToughness(1);
            this.setActualToughness(getToughness());
            this.setStrengthTestEffect(
                    new And(
                            new LooseOrGainHealthSanity(SpendType.HEALTH,-1,LooseOrGainHealthSanity.ENCOUNTERING_INVESTIGATOR),
                            new DiscardMonster(this)));
        }


    }
}
