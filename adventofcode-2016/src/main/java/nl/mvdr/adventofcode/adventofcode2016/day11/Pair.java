package nl.mvdr.adventofcode.adventofcode2016.day11;

record Pair(Floor generator, Floor microchip) {

    Pair moveGenerator(Floor targetFloor) {
        return new Pair(targetFloor, microchip);
    }
    
    Pair moveMicrochip(Floor targetFloor) {
        return new Pair(generator, targetFloor);
    }
}
