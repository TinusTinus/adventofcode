package nl.mvdr.adventofcode.adventofcode2016.day10;

record Rule(MicrochipHolder lowTarget, MicrochipHolder highTarget) {
    @Override
    public final String toString() {
        return "gives low to " + lowTarget + " and high to " + highTarget;
    }
}
