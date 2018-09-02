package model;

import enums.TestType;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString(of = {"lore","influence","observation","strength","will"})
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

    public int getSkill(TestType typ) {
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

    public void improve(TestType testType, int value) {
        switch (testType){
            case STRENGTH:
                strengthMod+=value;
                if(strengthMod>2){
                    strengthMod=2;
                }else if(strengthMod <0){
                    strengthMod=0;
                }
                break;
            case LORE:
                loreMod+=value;
                if(loreMod>2){
                    loreMod=2;
                }else if(loreMod <0){
                    loreMod=0;
                }
                break;
            case WILL:
                willMod+=value;
                if(willMod>2){
                    willMod=2;
                }else if(willMod <0){
                    willMod=0;
                }
                break;
            case OBSERVATION:
                observationMod+=value;
                if(observationMod>2){
                    observationMod=2;
                }else if(observationMod <0){
                    observationMod=0;
                }
                break;
            case INFLUENCE:
                influenceMod+=value;
                if(influenceMod>2){
                    influenceMod=2;
                }else if(influenceMod <0){
                    influenceMod=0;
                }
                break;
        }
    }
}
