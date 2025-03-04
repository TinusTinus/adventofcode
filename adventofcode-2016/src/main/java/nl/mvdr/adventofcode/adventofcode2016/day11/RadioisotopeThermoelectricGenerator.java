package nl.mvdr.adventofcode.adventofcode2016.day11;

record RadioisotopeThermoelectricGenerator(Radioisotope radioisotope) implements Item {
    @Override
    public final String toString() {
        return radioisotope + "G";
    }
}
