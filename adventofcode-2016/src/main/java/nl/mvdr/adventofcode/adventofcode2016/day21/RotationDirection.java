package nl.mvdr.adventofcode.adventofcode2016.day21;

enum RotationDirection {
    
    LEFT,
    RIGHT;

    static RotationDirection parse(String string) {
        return switch(string) {
            case "left" -> LEFT;
            case "right" -> RIGHT;
            default -> throw new IllegalArgumentException("Unable to parse as a direction: " + string);
        };
    }
    
    RotationDirection reverse() {
        return switch(this) {
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
        };
    }
}
