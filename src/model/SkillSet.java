package model;

import enums.TestTyp;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class SkillSet {
    private int lore;
    private int influence;
    private int observation;
    private int strength;
    private int will;
    private int loreMod;
    private int influenceMod;
    private int observationMod;
    private int strengthMod;
    private int willMod;

    public SkillSet(int lore, int influence, int observation, int strength, int will) {
        this.lore = lore;
        this.influence = influence;
        this.observation = observation;
        this.strength = strength;
        this.will = will;
    }

    public int getSkill(TestTyp typ) {
        switch (typ) {
            case LORE:
                return lore + loreMod < 1 ? 1 : lore + loreMod;
            case INFLUENCE:
                return influence + influenceMod < 1 ? 1 : influence + influenceMod;
            case OBSERVATION:
                return observation + observationMod < 1 ? 1 : observation + observationMod;
            case STRENGTH:
                return strength + strengthMod < 1 ? 1 : strength + strengthMod;
            case WILL:
                return will + willMod < 1 ? 1 : will + willMod;
            default:
                return 1;
        }
    }
}
