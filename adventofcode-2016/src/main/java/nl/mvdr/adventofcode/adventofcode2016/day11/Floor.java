package nl.mvdr.adventofcode.adventofcode2016.day11;

import java.util.stream.Stream;

enum Floor {
    FIRST("The first floor"),
    SECOND("The second floor"),
    THIRD("The third floor"),
    FOURTH("The fourth floor");
    
    private final String stringRepresentation;
    
    private Floor(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }
    
    static Floor of(String stringRepresentation) {
        return Stream.of(Floor.values())
                .filter(floor -> floor.stringRepresentation.equals(stringRepresentation))
                .findFirst()
                .orElseThrow();
    }
}
