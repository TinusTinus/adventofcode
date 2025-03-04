package nl.mvdr.adventofcode.adventofcode2016.day11;

import java.util.stream.Stream;

enum Floor {
    FIRST("The first floor", "F1"),
    SECOND("The second floor", "F2"),
    THIRD("The third floor", "F3"),
    FOURTH("The fourth floor", "F4");
    
    private final String stringRepresentation;
    private final String shortRepresentation;
    
    private Floor(String stringRepresentation, String shortRepresentation) {
        this.stringRepresentation = stringRepresentation;
        this.shortRepresentation = shortRepresentation;
    }
    
    static Floor of(String stringRepresentation) {
        return Stream.of(Floor.values())
                .filter(floor -> floor.stringRepresentation.equals(stringRepresentation))
                .findFirst()
                .orElseThrow();
    }
    
    @Override
    public String toString() {
        return shortRepresentation;
    }
}
