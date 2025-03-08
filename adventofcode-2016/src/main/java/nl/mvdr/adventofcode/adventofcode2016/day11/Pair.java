package nl.mvdr.adventofcode.adventofcode2016.day11;

/// Contains the locations of a corresponding generator and microchip.
record Pair(Floor generator, Floor microchip) {

    Pair moveGenerator(Floor targetFloor) {
        return new Pair(targetFloor, microchip);
    }
    
    Pair moveMicrochip(Floor targetFloor) {
        return new Pair(generator, targetFloor);
    }
}
