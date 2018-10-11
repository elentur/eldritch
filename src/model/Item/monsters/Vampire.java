package model.Item.monsters;


import model.Item.Monster;

public class Vampire extends Monster {

    public Vampire(){
        super(5);
        this.setId("&vampire");
        this.setName("${vampire}");
        this.setWillTest(0);
        this.setHorror(1);
        this.setStrengthTest(-2);
        this.setDamage(2);
        this.setToughness(3);
        this.setActualToughness(getToughness());

    }










}
