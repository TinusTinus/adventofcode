package nl.mvdr.adventofcode.adventofcode2016.day10;

sealed interface MicrochipHolder permits Bot, Output {
    
    static MicrochipHolder parse(String stringRepresentation) {
        var parts = stringRepresentation.split(" ");
        var number = Integer.parseInt(parts[1]);
        return switch(parts[0]) {
            case "bot" -> new Bot(number);
            case "output" -> new Output(number);
            default -> throw new IllegalArgumentException();
        };
    }
}
