package model.Item.monsters;


import model.Item.Monster;

public class Shan extends Monster {

    public Shan(){
        this.setId("&shan");
        this.setName("${shan}");
        this.setWillTest(-1);
        this.setHorror(2);
        this.setStrengthTest(0);
        this.setDamage(1);
        this.setToughness(2);
        this.setActualToughness(getToughness());
        this.setEffects(null);

    }








}
