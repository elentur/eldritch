package model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Monster implements IMonster {
    private String name;
    private String image;
    private int willTest;
    private int horror;
    private int strengthTest;
    private int damage;
    private int toughness;
    private List<Effect> effects;
}
