package nl.mvdr.adventofcode.adventofcode2016.day11;

record Radioisotope(String name) {
    
    @Override
    public final String toString() {
        return name.substring(0, 1).toUpperCase();
    }
}
