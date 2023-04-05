package nl.mvdr.adventofcode.adventofcode2022.day10;

/**
 * State of the central processing unit (CPU).
 *
 * @param cycleNumber current cycle number
 * @param x current value of the x register
 * 
 * @author Martijn van de Rijdt
 */
record Cpu(int cycleNumber, int x) {
    /**
     * @return current signal strength
     */
    int signalStrength() {
        return cycleNumber * x;
    }
}
