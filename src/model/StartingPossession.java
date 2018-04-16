package model;

import enums.ElementTyp;
import lombok.*;
import model.Item.Item;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class StartingPossession implements Item {
    private String id;
    private int number;
    private ElementTyp typ;
}