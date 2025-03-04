package nl.mvdr.adventofcode.adventofcode2016.day11;

record Microchip(Radioisotope radioisotope) implements Item {
    @Override
    public final String toString() {
        return radioisotope + "M";
    }
}
