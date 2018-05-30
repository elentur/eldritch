package model;

import enums.FieldType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import model.Item.investigators.Investigator;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Getter
public class Field {
    private final FieldType type;

    private final Set<Investigator> investigators = new HashSet<>();

}
