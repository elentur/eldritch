package model.Item.monsters;

import model.Monster;


public class Vampire extends Monster {

    public Vampire(){
        this.setId("&vampire");
        this.setName("${vampire}");
        this.setWillTest(0);
        this.setHorror(1);
        this.setStrengthTest(-2);
        this.setDamage(2);
        this.setToughness(3);
        this.setActualToughness(getToughness());
        this.setEffects(null);

    }








}