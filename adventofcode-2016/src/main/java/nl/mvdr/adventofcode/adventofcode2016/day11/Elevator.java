package nl.mvdr.adventofcode.adventofcode2016.day11;

final class Elevator implements Item {
    
    static final Elevator INSTANCE = new Elevator();
    
    private Elevator() {
        // private constructor to prevent singleton instantiation
    }
}
