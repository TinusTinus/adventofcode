package nl.mvdr.adventofcode.adventofcode2022.day17;

/**
 * Definition of a cycle.
 *
 * @author Martijn van de Rijdt
 */
record Cycle(HeightAndSettledRocks start, HeightAndSettledRocks end) {
    
    /**
     * @return the number of rocks that settle each cycle
     */
    int getRocksInCycle() {
        return end.settledRocksCount() - start.settledRocksCount();
    }
    
    /**
     * @return the number of units the tower grows each cycle
     */
    int getCycleHeight() {
        return end.height() - start.height();
    }
}
