package nl.mvdr.adventofcode.adventofcode2016.day10;

record Output(int number) implements MicrochipHolder {
    @Override
    public final String toString() {
        return "output " + number;
    }
}
