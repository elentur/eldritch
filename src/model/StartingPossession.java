package model;

import enums.ElementTyp;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class StartingPossession  {
    private String id;
    private int number;
    private ElementTyp typ;
    public String getName(){return "";}
}
