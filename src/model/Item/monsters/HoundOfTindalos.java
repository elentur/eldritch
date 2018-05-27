package model.Item.monsters;

import model.Monster;


public class HoundOfTindalos extends Monster {

    public HoundOfTindalos(){
        this.setId("&houndOfTindalos");
        this.setName("${hound_of_tindalos}");
        this.setWillTest(0);
        this.setHorror(2);
        this.setStrengthTest(-1);
        this.setDamage(3);
        this.setToughness(3);
        this.setActualToughness(getToughness());
        this.setEffects(null);

    }








}
