package nl.mvdr.adventofcode.adventofcode2016.day11;

import java.util.stream.Stream;

enum Floor {
    FIRST("The first floor", 1),
    SECOND("The second floor", 2),
    THIRD("The third floor", 3),
    FOURTH("The fourth floor", 4);
    
    private final String stringRepresentation;
    private final int floorNumber;
    
    private Floor(String stringRepresentation, int floorNumber) {
        this.stringRepresentation = stringRepresentation;
        this.floorNumber = floorNumber;
    }
    
    static Floor of(String stringRepresentation) {
        return Stream.of(Floor.values())
                .filter(floor -> floor.stringRepresentation.equals(stringRepresentation))
                .findFirst()
                .orElseThrow();
    }
    
    int getFloorNumber() {
        return floorNumber;
    }
    
    @Override
    public String toString() {
        return "F" + floorNumber;
    }
}
