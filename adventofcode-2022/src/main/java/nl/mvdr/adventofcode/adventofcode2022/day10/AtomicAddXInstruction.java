package nl.mvdr.adventofcode.adventofcode2022.day10;

/**
 * Adds a value to the x register.
 *
 * @param value the value to add
 *
 * @author Martijn van de Rijdt
 */
record AtomicAddXInstruction(int value) implements AtomicInstruction {
    @Override
    public int perform(int x) {
        return x + value;
    }
}
