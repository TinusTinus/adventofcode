package nl.mvdr.adventofcode.adventofcode2016.day10;

record Bot(int number) implements MicrochipHolder {

    static Bot parse(String stringRepresentation) {
        return (Bot) MicrochipHolder.parse(stringRepresentation);
    }
    
    @Override
    public final String toString() {
        return "bot " + number;
    }
}
