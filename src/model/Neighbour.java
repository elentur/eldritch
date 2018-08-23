package model;

import enums.PathType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class Neighbour {
    private final Field field;
    private final PathType path;
}
