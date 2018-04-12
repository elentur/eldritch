package model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Monster implements IMonster {
    private String name;
    private String image;
    private int willtest;
    private int horro;
    private int strengthtest;
    private int damage;
    private int toughness;
    private List<Effect> effects;
    private List<Action> actions;
}
