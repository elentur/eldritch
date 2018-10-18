package model.Item.epicmonster;


import Service.GameService;
import enums.OmenStates;
import gamemechanics.Mythos;
import gamemechanics.MythosBlue;
import model.Effect;
import model.Item.Investigator;
import model.Item.Monster;

public class WindWalker extends Monster {

    private final MythosBlue rumor;

    public WindWalker(MythosBlue rumor){
        super(1);
        this.setId("&windWalker");
        this.setName("${wind_walker}");
        this.setWillTest(0);
        this.setHorror(2);
        this.setStrengthTest(-2);
        this.setDamage(2);
        this.setToughness(GameService.getInstance().getInvestigators().size()+2);
        this.setActualToughness(getToughness());
        this.rumor=rumor;
    }

    @Override
    public void setWillTestEffect(Effect willTestEffect) {
        super.setWillTestEffect(willTestEffect);
    }

    @Override
    public void discard() {
        super.discard();
        rumor.discard();
    }
}
