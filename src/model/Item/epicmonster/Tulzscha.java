package model.Item.epicmonster;


import Service.GameService;
import enums.OmenStates;
import model.Item.Investigator;
import model.Item.Monster;

public class Tulzscha extends Monster {

    public Tulzscha(){
        super(1);
        this.setId("&tulzscha");
        this.setName("${Tulzscha}");
        this.setWillTest(-1);
        this.setHorror(3);
        this.setStrengthTest(-1);
        this.setDamage(3);
        this.setToughness(GameService.getInstance().getInvestigators().size()+2);
        this.setActualToughness(getToughness());

    }

    @Override
    public void executeReckoning(Investigator inv, boolean autoFail) {
        GameService.getInstance().getOmenTrack().addToken(OmenStates.GREEN_COMET);
    }
}
